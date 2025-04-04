package rebote;

import stdlib.StdDraw;

public class Obstaculo {
	
	private static final double RADIO = 0.01;
	private double x;
	private double y;
	protected double ancho;
	protected double alto;
	
	public Obstaculo() {
		x = 60;
		y = 60;
		ancho = 3;
		alto = 3;
	}
	
	public Obstaculo(double ix, double iy) {
		x = ix;
		y = iy;
		ancho = 3;
		alto = 3;
	}
	
	public void dibujar() {
		StdDraw.setPenRadius(RADIO);
		StdDraw.line(x, y, x + ancho, y);
		StdDraw.line(x + ancho, y, x + ancho, y - alto);
		StdDraw.line(x + ancho, y - alto, x, y - alto);
		StdDraw.line(x, y - alto, x, y);
	}
}