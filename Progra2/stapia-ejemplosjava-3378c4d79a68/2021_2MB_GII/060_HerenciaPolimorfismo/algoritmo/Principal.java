package algoritmo;

import java.util.*;

public class Principal {
	
	public static void main(String[] args) {
		ICalcularSobreVector[] vectorCalculos = {
			new Media(),
			new Maximo(),
			new Minimo()
		};
		
		double v[] = new double[3];
		Random aleatorio = new Random();
		
		for ( int i = 0; i < v.length; i++ ) {
			v[i] = aleatorio.nextDouble();
		}
		System.out.println(Arrays.toString(v));
		
		for ( int i = 0; i < vectorCalculos.length; ++i ) {
			double calculo = vectorCalculos[i].aplicar(v);
			System.out.println("El resultado es: " + calculo);
		}
	}
}
