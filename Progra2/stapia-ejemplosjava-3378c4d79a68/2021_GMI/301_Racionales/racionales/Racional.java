package racionales;

/**
 * Una clase para manejar fracciones.
 * 
 * @author stapia
 */
public class Racional {
	private int num;
	private int den;
	
	/**
	 * Construye un fracción
	 * 
	 * @param num el numerador que va a tener la fracción
	 * @param den el denominador que va a tener la fracción
	 */
	public Racional(int num, int den) {
		this.num = num;
		this.den = den;
		simplicar();
	}
	
	/**
	 * Calcula la suma de dos fracciones
	 * @param s1 un sumando
	 * @param s2 el otro sumando
	 * @return la suma
	 */
	public static Racional sumar(Racional s1, Racional s2) {
		return new Racional(s1.num*s2.den + s2.num * s1.den,s1.den * s2.den);
	}
	
	/**
	 * Calcula el valor en coma flotante de la fraccion
	 * @return el valor en coma flotante de num/den
	 */
	public double eval() {
		return num/(double)den;
	}

	@Override
	public String toString() {
		return String.format("%d/%d", num, den);
	}
	
	private void simplicar() {
		int mcd = MCD.calcula(num, den);
		num /= mcd;
		den /= mcd;
		if ( den < 0 ) {
			num *= -1;
			den *= -1;
		}
	}

	public int getNumerador() {
		return num;
	}

	public int getDenominador() {
		return den;
	}

	public static void main(String[] args) {
		Racional r = new Racional(3,0);
		System.out.println(r.eval());
	    r = new Racional(-3,0);
		System.out.println(r.eval());
	}
}
