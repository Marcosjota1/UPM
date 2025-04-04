package EjerciciosClase.entregable6;

// TODO: importar la clase de los semáforos.
// 
import es.upm.babel.cclib.Semaphore;

// Almacén FIFO concurrente para N datos

class AlmacenN {
    private int capacidad = 0;
    private int[] almacenado = null;
    private int nDatos = 0;
    private int aExtraer = 0;
    private int aInsertar = 0;

    // TODO: declaración de los semáforos necesarios
    static Semaphore vaciar; //No se podra llamar a extraer si esta a cero
	static Semaphore llenar; //No se podra llamar a almacenar si esta a cero
	static Semaphore mutex; //garantizar exclusion mutua y no provoque carrera 

    public AlmacenN(int n) {
	capacidad = n;
	almacenado = new int[capacidad];
	nDatos = 0;
	aExtraer = 0;
	aInsertar = 0;

	// TODO: inicialización de los semáforos
	//       si no se ha hecho más arriba
	vaciar = new Semaphore(0); 
	llenar = new Semaphore(capacidad);
	mutex = new Semaphore(1); //Al estar a 1 solo entra 1 a la seccion critica


    }

    public void almacenar(int producto) {
	// TODO: protocolo de acceso a la sección crítica y código de
	// sincronización para poder almacenar.
	llenar.await(); //Esperar hasta que haya hueco
	mutex.await();

		{
			// Sección crítica ///////////
            almacenado[aInsertar] = producto;
            nDatos++;
            aInsertar++;
            aInsertar %= capacidad;
            // ///////////////////////////
		}
	// TODO: protocolo de salida de la sección crítica y código de
	// sincronización para poder extraer.
	vaciar.signal(); //Indicas elementos +1
	mutex.signal();
	// 
    }

    public int extraer() {
	int result;

	// TODO: protocolo de acceso a la sección crítica y código de
	// sincronización para poder extraer.
		vaciar.await(); //Esperas hasta que haya algo que sacar
		mutex.await();
	// 

	{
			// Sección crítica ///////////
		result = almacenado[aExtraer];
		nDatos--;
		aExtraer++;
		aExtraer %= capacidad;
		// ///////////////////////////


	}
	
	// y código de
	// sincronización para poder almacenar.
	llenar.signal();
	// TODO: protocolo de salida de la sección crítica
	mutex.signal(); //indicas que sales seciion critica
	return result;

    }
}