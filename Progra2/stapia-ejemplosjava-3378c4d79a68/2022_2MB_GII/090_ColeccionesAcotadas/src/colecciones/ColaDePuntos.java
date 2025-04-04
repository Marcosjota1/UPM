package colecciones;

import java.util.Arrays;

public class ColaDePuntos {
	
	private Punto[] cola = new Punto[6];
	private int numeroDeItems = 0;
	
	public void poner(Punto p) {
		if ( numeroDeItems == cola.length ) { // Comprobar si llena
			cola = Arrays.copyOf(cola, numeroDeItems*2);
		}
		cola[numeroDeItems] = p;
		numeroDeItems += 1;
	}
	
	public Punto quitar() {
		Punto primero = cola[0];
		for ( int i = 0; i < numeroDeItems -1; ++i ) {
			cola[i] = cola[i+1];
		}
		numeroDeItems -= 1;
		return primero;
	}
}
