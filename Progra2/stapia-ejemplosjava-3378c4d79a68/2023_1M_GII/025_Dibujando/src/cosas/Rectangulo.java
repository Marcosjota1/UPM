package cosas;

import stdlib.StdDraw;

public class Rectangulo {
	
	private static final double RADIO = 0.01; // radio de la pluma
	Punto arribaIzquierda;
	double ancho;
	double alto;

	public Rectangulo(Punto p, double ancho, double alto) {
		arribaIzquierda = p;
		this.ancho = ancho;
		this.alto = alto;
	}
	
	public Rectangulo(Punto p, Punto q) {
		arribaIzquierda = new Punto(p);
		this.ancho = q.x() - p.x();
		this.alto = q.getY() - p.getY();
	}

	public void dibujar() {
		StdDraw.setPenRadius(RADIO);
		
		double x = arribaIzquierda.x();
		double y = arribaIzquierda.getY();
		
		StdDraw.line(x, y, x + ancho, y);
		StdDraw.line(x + ancho, y, x + ancho, y - alto);
		StdDraw.line(x + ancho, y - alto, x, y - alto);
		StdDraw.line(x, y - alto, x, y);
		
	}

}
