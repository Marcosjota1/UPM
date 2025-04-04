package racionales;

import tads.*;

public class MuchasFracciones {
	
	public static Fraccion sumar(Fraccion[] fracciones) {
		Fraccion resultado = new Fraccion(0, 1);
		for ( int i = 0; i < fracciones.length; ++i ) {
			resultado = Aritmetica.add(fracciones[i], resultado);
		}
		return resultado; 
	}
	
	/**
	 * @pre. sucesion.size() >= 2 
	 * @param sucesion
	 * @throws RuntimeException cuando no se cumple la condición
	 */
	public static void hacerSucesion(IList<Fraccion> sucesion) {
		final int N = sucesion.size();
		if ( N < 2 ) { // Precondicion negada
			throw new RuntimeException("Necesito al menos 2 terminos");
		}
		Fraccion aN_1 = sucesion.get(N-1);
		Fraccion aN_2 = sucesion.get(N-2);
		// An = 2*An-1 - (5/3)*An-2
		Fraccion cincoTercios = new Fraccion(5,3);
		Fraccion aN = Aritmetica.minus(Aritmetica.dot(2, aN_1), 
				Aritmetica.dot(cincoTercios , aN_2));
		sucesion.add(N, aN);
	}

	public static void main(String[] args) {
		Fraccion[] fracciones = {
				new Fraccion(3,5),
				new Fraccion(3,-5),
				new Fraccion(-3,-5),
				new Fraccion(30,-50),
				new Fraccion(-30,-50),
				new Fraccion(6,-15),
				new Fraccion(18,-48)			
			};
		
		Fraccion laSuma = MuchasFracciones.sumar(fracciones);
		System.out.println(laSuma);
		
		ArrayList<Fraccion> miSucesion = new ArrayList<>();
		miSucesion.add(0, new Fraccion(3,4));
		miSucesion.add(1, new Fraccion(5,2));
		for ( int i = 0; i < 8; ++i ) {
			hacerSucesion(miSucesion);
		}
		System.out.println(miSucesion);
	}

}
