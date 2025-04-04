package figurasbis;

import tads.*;

public class BuscandoPunto {
	
	/*
	 * Vamos a crear una lista de puntos y vamos a buscar uno
	 * de esos puntos.
	 */

	public static void main(String[] args) {
		
		//IList<Punto> lista = new ArrayList<>(3);
		IList<Punto> lista = new LinkedList<>();
			
		for ( int i = 0; i < 5; i++ ) {
			lista.add(0, new Punto(2.2*(i+1), 3.3*i));
			System.out.println("La lista en " + i + " es: " + lista);
		}
		lista.add(2, new Punto(77.7, -9.99+1e-9));
		
		System.out.println("La lista es: " + lista);
		
		Punto p = new Punto(77.7, -9.99);
		
		int index = lista.indexOf(p);
		
		System.out.println("El índices es: " + index);
	}
}
