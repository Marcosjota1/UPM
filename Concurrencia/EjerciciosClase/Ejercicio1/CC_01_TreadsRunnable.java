package EjerciciosClase.Ejercicio1; 
 
public class CC_01_TreadsRunnable { 
 
    public static void main(String[] args) { 
        for (int i = 0; i <= 3; i++) { 
            Runnable myRunnable = new Multithreading(i); 
            Thread myThread = new Thread(myRunnable); 
            myThread.start(); 
        } 
    } 
 
    private static class Multithreading implements Runnable { 
 
        private int threadNumber; 
 
        public Multithreading(int threadNumber) { 
            this.threadNumber = threadNumber; 
        } 
 
        @Override 
        public void run() { 
            for (int i = 0; i <= 5; i++) { 
                System.out.println(i + " step from thread " + threadNumber);  //Contar hasta 5 pasos
                try { 
                    Thread.sleep(1000); 
                } catch (InterruptedException e) { 
                } 
            } 
            
        } 
    } 
}
