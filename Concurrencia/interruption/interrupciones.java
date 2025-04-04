package interruption;

public class interrupciones
{
	public static void main(String args[])
	{
		int num_interrupciones = 13;
		int num_iteraciones = 100;

		hiloCabezon hilo = new hiloCabezon(num_iteraciones,num_interrupciones);
		hilo.start();

		for(int i=0; i < num_interrupciones; i++)
		{
			try
			{
				Thread.sleep(100);
			}
			catch(InterruptedException e)
			{
				System.out.println("Alguien me ha matado");
			}
			hilo.interrupt();
		}
	}	
}