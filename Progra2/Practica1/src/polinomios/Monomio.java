package polinomios;

public class Monomio {

	public final double coeficiente; // Gracias al final los hacemos inmutables
	public final int exponente;

	public Monomio(double coeficiente, int exponente) {
		if (coeficiente == 0) {
			System.out.println("El coeficiente del monomio no puede ser cero.");
		}
		this.coeficiente = coeficiente;
		this.exponente = exponente;
	}

	public String toString() {
		return String.format("%.1f*x^%d", coeficiente, exponente);
	} // sirve para cambiar el formato y conseguir conseguir un numero en coma
		// flotante, un coeficiente con un solo
		// decimal, y un exponente entero

	public static Monomio sumar(Monomio monomio1, Monomio monomio2) {
		if (monomio1.exponente != monomio2.exponente) {
			return null;
		}
		double coeficiente_final = monomio1.coeficiente + monomio2.coeficiente;
		if (coeficiente_final == 0) {
			return null;
		}
		return new Monomio(coeficiente_final, monomio1.exponente);
	}

	public static Monomio multiplicar(Monomio monomio1, Monomio monomio2) {
		double coeficiente_final = monomio1.coeficiente * monomio2.coeficiente;
		int exponente_final = monomio1.exponente + monomio2.exponente;
		if (coeficiente_final == 0) {
			return null;
		}
		return new Monomio(coeficiente_final, exponente_final);
	}

	public int getExponente() {
		return exponente;
	}

	public double getCoeficiente() {
		return coeficiente;
	}

}
