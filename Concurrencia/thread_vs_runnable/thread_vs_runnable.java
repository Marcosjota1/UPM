package thread_vs_runnable;

public class thread_vs_runnable{//thread_vs_runnable {

   static class ThreadTonto extends Thread {
      static int n = 0;
      private int id;

      public ThreadTonto (int i) {
         super();
         id = i;
      }

      public void run() {
         System.out.print("Hola, soy un thread tonto ("
                          + id + ")\n");

         n++;

         System.out.print("ya he terminado (" +
                          id + ")\n");
      }
   }

   static class ThreadBobo implements Runnable {
      static int n = 0;
      private int id;

      public ThreadBobo (int i) {
         super();
         id = i;
      }

      public void run() {
         System.out.print("Hola, soy un thread bobo ("
                          + id + ")\n");

         n++;

         System.out.print("ya he terminado (" +
                          id + ")\n");
      }
   }

   public static void main(String args[]) {
      int N = 1000;
      System.out.print("Soy el thread principal\n");
      Thread t[] = new Thread[N];
      Thread b[] = new Thread[N];
      for (int i = 0; i < N; i++) {
         t[i] = new ThreadTonto(i); //extends thread
	      b[i] = new Thread(new ThreadBobo(i));  //implements runnable
      }
      System.out.print("Pongo en marcha N threads tontos y N threads bobos \n");
      for (int i = 0; i < N; i++) {
         t[i].start();  
	      b[i].start();  
      }
      System.out.print("He puesto en marcha los N threads tontos i los N threads bobos\n");
      try {
         for (int i = 0; i < N; i++) {
            t[i].join();
	         b[i].join();
         }
      }
      catch (InterruptedException e) {
      }
      System.out.print("He esperado a que terminen todos y han terminado.\n");
      System.out.print("El valor de la variable es "
                       + ThreadTonto.n + "\n");
   }
}
