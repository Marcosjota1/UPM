package figurastris;

import java.awt.Color;
import java.util.Random;

import stdlib.StdDraw;
import tads.*;

public class Principal {

	public static void main(String[] args) {
		IList<Figura> lista = new ArrayList<>();
		Random aleatorio = new Random();
		
		for ( int i = 0; i < 7; ++i) {
			if ( aleatorio.nextBoolean() ) {
				lista.add(i, new Cuadrado(aleatorio.nextDouble(), aleatorio.nextDouble()));
			} else {
				lista.add(i, new Circulo(0.2, 0.4, aleatorio.nextDouble()));				
			}
		}
		
		StdDraw.setPenRadius(0.01);
		for ( int i = 0; i < lista.size(); i++ ) {
			Figura fig = lista.get(i);
			fig.dibujar();
		}
		
		StdDraw.setPenRadius(0.006);
		StdDraw.setPenColor(Color.BLUE);
		for ( int i = 0; i < lista.size(); i++ ) {
			Figura fig = lista.get(i);
			if ( fig instanceof Circulo ) {
				Circulo circulo = (Circulo) fig;
				circulo.dibujar();	
			}
		}
		
		StdDraw.setPenColor(Color.RED);
		for ( int i = 0; i < lista.size(); i++ ) {
			Figura fig = lista.get(i);
			if ( fig instanceof Cuadrado ) {
				fig.dibujar();	
			}
		}
		
	}
	
}
