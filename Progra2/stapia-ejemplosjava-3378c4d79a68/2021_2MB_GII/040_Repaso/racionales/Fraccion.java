package racionales;

import java.util.Arrays;

public class Fraccion {
	
	private final int numerador;
	private final int denominador;
	
	public int getNumerador() {
		return numerador;
	}

	public int getDenominador() {
		return denominador;
	}
		
	public Fraccion(int numerador, int denominador) {
		int mcd = Euclides.mcd(Math.abs(numerador), Math.abs(denominador));
		if ( denominador > 0 ) {
			this.numerador = numerador / mcd;
			this.denominador = denominador / mcd;			
		} else {
			this.numerador = -numerador / mcd;
			this.denominador = -denominador / mcd;						
		}
	}
	
	// resultado = p + q siendo resultado, p, q pertenecientes a Q
	public static Fraccion add(Fraccion p, Fraccion q) {
		int num = p.numerador*q.denominador+q.numerador*p.denominador;
		int den = p.denominador*q.denominador;
		return new Fraccion(num,den);
	}
	
	// resultado = this + q siendo resultado, this, q pertenecientes a Q
	public Fraccion addTo(Fraccion q) {
		int num = this.getNumerador()*q.getDenominador()+q.getNumerador()*this.getDenominador();
		int den = this.getDenominador()*q.getDenominador();
		return new Fraccion(num,den);		
	}
	
	@Override
	public String toString() {
		// return numerador + "/" + denominador;
		return String.format("%d/%d", getNumerador(), getDenominador());
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
		System.out.println(fracciones);
		System.out.println(Arrays.toString(fracciones));
		
		Fraccion resultado1 = Fraccion.add(fracciones[5], fracciones[6]);
		System.out.println(resultado1);
		
		Fraccion resultado2 = fracciones[5].addTo(fracciones[6]);
		System.out.println(resultado2);
	}

	public int compareTo(Fraccion element) {
		int fraccion1 = this.getNumerador() * element.getDenominador();
		int fraccion2 = element.getNumerador() * this.getDenominador();
		if ( fraccion1 < fraccion2 ) {
			return -1;
		} else if ( fraccion1 == fraccion2 ) {
			return 0;
		} else {
			return 1;
		}
	}

}
