package sublista;

import java.util.Random;

import list.*;

public class SubLista {
	
	/**
	 * Devuelve los elementos de datos entre los índices [inicio, fin)
	 * 
	 * PRECONDICIÓN: fin < datos.size() e inicio <= fin y inicio >= 0
	 * POSTCONDICIÓN: size() del resultado es fin - inicio y datos.get(inicio) == resultado.get(0)
	 * @param inicio es el primer índice incluido en el resultado
	 * @param fin es el primer índice excluido del resultado
	 * @param datos la lista original
	 * @return Una nueva lista con los elementos de la original entre [inicio, fin)
	 */
	public static IList<Integer> hacerSublista(int inicio, int fin, IList<Integer> datos) {
		IList<Integer> resultado = new ArrayList<>();
		for ( int index = inicio; index < fin; ++index ) {
			Integer numero = datos.get(index);
			resultado.add(resultado.size(), numero);
		}
		return resultado;
	}

	public static void main(String[] args) {
		Random random = new Random();
		IList<Integer> datos = new LinkedList<>();
		for ( int i = 0; i < 20; ++i) {
			datos.add(0, random.nextInt(100));
		}
		System.out.println("---- Original ----- ");
		System.out.println(datos);
		
		IList<Integer> resultado = hacerSublista(2, 60, datos);
		
		System.out.println("---- Sublista ----- ");
		System.out.println(resultado);
	}

}
