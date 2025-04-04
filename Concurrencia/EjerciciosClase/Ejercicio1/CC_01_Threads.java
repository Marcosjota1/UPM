package EjerciciosClase.Ejercicio1;

public class CC_01_Threads {
    
    public static void main(String[] args) {
        int cantidadThreads = 5;  // Número de threads a ejecutar
        int tiempoDeEspera = 100;  // Tiempo de espera en milisegundos

        Thread t[] = new Thread[cantidadThreads];

        for (int i = 0; i < cantidadThreads; i++) {
            t[i] = new MiThread(i,tiempoDeEspera);
        }

        for (int i = 0; i < cantidadThreads; i++) {
            t[i].start();
        }
        try {
            for (int i = 0; i < cantidadThreads; i++) {
                t[i].join();
            }
        } catch (InterruptedException e) {
        }
        System.out.println("Todos los threads han finalizado\n");
    }


    static class MiThread extends Thread {

        private  int nombre;
        private  int tiempoDeEspera;
    
        public MiThread(int nombre, int tiempoDeEspera) {
            this.nombre = nombre;
            this.tiempoDeEspera = tiempoDeEspera;
        }
    
        @Override
        public void run() {
            System.out.println("Iniciando " + nombre);
    
            for (int i = 0; i < 5; i++) {
                System.out.println("Thrad <" + nombre + ">: Ejecutando paso " + (i + 1));
    
                try {
                    Thread.sleep(tiempoDeEspera);
                } catch (InterruptedException e) {
                }
            }
            System.out.println(nombre + " finalizado.");
        }
    }


}
    