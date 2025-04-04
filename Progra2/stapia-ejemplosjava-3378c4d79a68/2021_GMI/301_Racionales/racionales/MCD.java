package racionales;

/**
 * Una clase de utilidad para calcular el MCD
 * 
 * @author Santiago Tapia Fernández
 *
 */
public class MCD {
	
	/**
	 * Calcula el MCD de dos números enteros. Cuando cualquiera
	 * de los números sea cero el MCD será 1. 
	 * @param a un número entero
	 * @param b un número entero
	 * @return el máximo común divisor de los números a y b
	 */
	public static int calcula(int a, int b) {
		if ( a == 0 || b == 0 ) {
			return 1;
		}
		
		int resto = a % b;
		while ( resto != 0 ) {
			a = b;
			b = resto;
			resto = a % b;
		}
		return b;
	}

}
