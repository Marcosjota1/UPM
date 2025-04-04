package racionales;

import java.util.Arrays;

public class Aritmetica {

	public static Fraccion add(Fraccion p, Fraccion q) {
		int num = p.getNumerador() * q.getDenominador() + q.getNumerador() * p.getDenominador();
		int den = p.getDenominador() * q.getDenominador();
		return new Fraccion(num, den);
	}

	public static Fraccion minus(Fraccion p, Fraccion q) {
		int num = p.getNumerador() * q.getDenominador() - q.getNumerador() * p.getDenominador();
		int den = p.getDenominador() * q.getDenominador();
		return new Fraccion(num, den);
	}

	public static Fraccion dot(Fraccion p, Fraccion q) {
		int num = p.getNumerador() * q.getNumerador();
		int den = p.getDenominador() * q.getDenominador();
		return new Fraccion(num, den);
	}

	public static Fraccion dot(int escalar, Fraccion q) {
		int num = escalar * q.getNumerador();
		int den = q.getDenominador();
		return new Fraccion(num, den);
	}

	public static void main(String[] args) {
		Fraccion[] fracciones = { 
				new Fraccion(3, 5),
				new Fraccion(3, -5),
				new Fraccion(-3, -5),
				new Fraccion(30, -50),
				new Fraccion(-30, -50),
				new Fraccion(6, -15),
				new Fraccion(18, -48)
		};
		
		System.out.println(fracciones);
		System.out.println(Arrays.toString(fracciones));

		Fraccion resultado1 = Aritmetica.add(fracciones[5], fracciones[6]);
		System.out.println(resultado1);

		Fraccion resultado2 = Aritmetica.minus(fracciones[5], fracciones[6]);
		System.out.println(resultado2);
	}
}
