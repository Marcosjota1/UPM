package interruption;

public class hiloCabezon extends Thread
{
	int rechazos;
	long iteraciones;

	public hiloCabezon(long iteraciones,int rechazos)
	{
		this.rechazos = rechazos;
		this.iteraciones = iteraciones;
	}

	public void run()
	{
		int i = 0;
		for(i = 0; i < iteraciones && rechazos > 0; i++)
		{
			try
			{
				Thread.sleep(10);
			}
			catch(InterruptedException e)
			{
				System.out.println("Te voy a ignorar...");
				rechazos--;
			}
		}
		if(i < iteraciones) 
			System.out.println("Me han hecho parar. Me han faltado " + (iteraciones - i) + " iteraciones.");
		else
			System.out.println("He podido acabar. Me quedaban " + rechazos + " rechazos." );
	}
}