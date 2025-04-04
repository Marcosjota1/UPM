package EjerciciosClase.entregable2;

import java.util.LinkedList;

public class CC_02_Carrera {

    private static final int NDATOS = 1000;
    private static LinkedList<Integer> colaCompartida = new LinkedList<>();

    private static class Productor extends Thread {
        private int inicial;

        public Productor(int inicial) {
            this.inicial = inicial;
        }

        public void run() {
            int dato = inicial;
            for (int j = 0; j < NDATOS; j++) {
                colaCompartida.add(dato);
                dato += 2;
            }
        }
    }

    private static class Consumidor extends Thread {
        public void run() {
            for (int i = 0; i < NDATOS; i++) {
                int dato = colaCompartida.remove(i);
                System.out.println("El dato extraido es: " + dato);
            }
        }
    }

    public static void main(String[] args)
      throws InterruptedException {
      
      Thread par = new Productor(0);
      Thread impar = new Productor(1);
      Thread c = new Consumidor();
      
      par.start();
      impar.start();
      c.start();
      
      par.join();
      impar.join();
      c.join();
      
      System.out.println("El numero de elementos en la cola es:" + colaCompartida.size());
    }
  }