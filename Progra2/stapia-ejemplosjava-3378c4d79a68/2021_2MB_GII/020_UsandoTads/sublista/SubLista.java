package sublista;

import java.util.Random;

import tads.*;

public class SubLista {
	
	/*
	 * ( 1, 2, 3)
	 * 
	 *  add(1, 77)
	 *  
	 *  (1, 77, 2, 3) 
	 *  
	 *  () 
	 *  add(0, 4)
	 *  ( 4 )
	 *  add(0, 3)
	 *  ( 3, 4) 
	 */
	
	/**
	 * Devuelve una sublista conteniendo los elementos de la dada (datos) entre
	 * los índices inicio y fin - 1 en orden inverso.
	 * 
	 * PRECONDICION: fin > inicio y (fin - inicio) <= datos.size() y inicio >= 0
	 * POSTCONDICION: resultado.size() = fin - inicio 
	 *              y resultado.get(0) == datos.get(fin-1)
	 * @param datos La lista de donde proceden los números
	 * @param inicio El índice del primer elemento a insertar en el resultado
	 * @param fin El índice del primer elemento excluido del resultado
	 * @return La lista resultado
	 */
	public static IList<Integer> hacerSublista(IList<Integer> datos, int inicio, int fin) {
		IList<Integer> resultado = new LinkedList<>();
		for ( int index = inicio; index < fin; ++index ) {
			Integer numero = datos.get(index);
			resultado.add(0, numero);
		}
		return resultado;
	}

	public static void main(String[] args) {
		Random random = new Random();
		IList<Integer> datos = new ArrayList<>();
		for ( int i = 0; i < 20; ++i ) {
			datos.add(datos.size(), random.nextInt(100));
		}
		System.out.println("Lista original:");
		System.out.println(datos);
		
		IList<Integer> resultado = hacerSublista(datos, 4, 7);
		System.out.println("Sublista:");
		System.out.println(resultado);
	}

}
