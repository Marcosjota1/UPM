package figurasbis;

import stdlib.StdDraw;
import tads.*;

public class Figura {
	
	private LinkedList<Punto> vertices = new LinkedList<>();
	
	public Figura(Punto[] puntos) {
		for ( int i = 0; i < puntos.length; i++ ) {
			vertices.add(vertices.size(), puntos[i]);
		}
	}
	
	public void dibujar() {
		for ( int i = 0; i + 1 < vertices.size() ; i++ ) {
			Punto inicioLinea = vertices.get(i);
			Punto finalLinea = vertices.get(i+1);
			dibujarLinea(inicioLinea, finalLinea);
		}
		Punto inicioLinea = vertices.get(vertices.size()-1);
		Punto finalLinea = vertices.get(0);
		dibujarLinea(inicioLinea, finalLinea);
	}

	private void dibujarLinea(Punto inicio, Punto ultimo) {
		StdDraw.line(inicio.getX(), inicio.getY(), ultimo.getX(), ultimo.getY());		
	}
}
