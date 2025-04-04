package EjerciciosClase.entregable5;


import es.upm.babel.cclib.Semaphore;

// TODO: importar la clase de los semáforos.
// 

// Almacen concurrente para un dato
class Almacen1 {
    // Producto a almacenar
    private int almacenado;
    static Semaphore empty = new Semaphore(1); //Indica que el semaforo empieza vacio
    static Semaphore full = new Semaphore(0); //Indica que el semaforo NO empieza lleno
    
    // TODO: declaración e inicialización de los semáforos
    // necesarios

    public Almacen1() {
    }
    
    public void almacenar(int producto) {
	// TODO: protocolo de acceso a la sección crítica y código de
	// sincronización para poder almacenar.
	//
	empty.await(); //Cuando no esta vacio no continuamos
	{// Sección crítica
	almacenado = producto;
    }
    full.signal(); //Como lo llenamos damos permiso al extractor

	// TODO: protocolo de salida de la sección crítica y código de
	// sincronización para poder extraer.
	//
    }

    public int extraer() {
	int result;

	// TODO: protocolo de acceso a la sección crítica y código de
	// sincronización para poder extraer.
    full.await(); //Si no esta lleno no seguimos
    {
        // Sección crítica
	    result = almacenado;
        almacenado = 0; //Producto extraido a cero
    }
    empty.signal(); //Indicamos que esta vacio

	// TODO: protocolo de salida de la sección crítica y código de
	// sincronización para poder almacenar.
	// 
	
	return result;
    }
}