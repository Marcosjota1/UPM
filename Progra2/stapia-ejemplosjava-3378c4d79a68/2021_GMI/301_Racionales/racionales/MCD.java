package racionales;

/**
 * Una clase de utilidad para calcular el MCD
 * 
 * @author Santiago Tapia Fern�ndez
 *
 */
public class MCD {
	
	/**
	 * Calcula el MCD de dos n�meros enteros. Cuando cualquiera
	 * de los n�meros sea cero el MCD ser� 1. 
	 * @param a un n�mero entero
	 * @param b un n�mero entero
	 * @return el m�ximo com�n divisor de los n�meros a y b
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
