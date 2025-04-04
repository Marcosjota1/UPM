package figuras;

import java.util.Random;

import tads.LinkedList;

public class Probando {
	
	public static void main(String[] args) {
		LinkedList<Figura> lista = new LinkedList<>();
		Random aleatorio = new Random(534);
		for ( int i = 0; i < 5; i++ ) {
			if ( aleatorio.nextBoolean() ) {
				lista.add(0, new Triangulo(2.0+aleatorio.nextDouble(), 1.0 + aleatorio.nextDouble()));
			} else {
				lista.add(0, new Cuadrado(2.0+aleatorio.nextDouble()));				
			}
		}
		System.out.println(lista);
		
		for ( int i = 0; i < lista.size(); ++i ) {
			Figura fig = lista.get(i);
			System.out.println("La figura: " + fig);
			double area = fig.calcularArea();
			System.out.println("Tiene area: " + area);
		}
	}

}
