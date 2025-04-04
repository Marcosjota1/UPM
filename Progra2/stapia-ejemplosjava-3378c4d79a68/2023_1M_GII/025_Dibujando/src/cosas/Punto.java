package cosas;

import stdlib.StdDraw;

public class Punto {
	
	private static final double RADIO = 0.03;
	private double x;
	private double y;

	public Punto(double i, double j) {
		x = i;
		y = j;
	}
	
	// Constructor "copia"
	public Punto(Punto p) {
		x = p.x;
		y = p.y;
	}

	public double x() {
		return x;
	}
	
	public double getY() {
		return y;
	}

	public void dibujar() {
		StdDraw.setPenRadius(RADIO);
		StdDraw.point(x, y);
	}

	public void mover(double ix, double iy) {
		x += ix;
		y += iy;
	}

}
