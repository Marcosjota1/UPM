package figurastris;

import stdlib.StdDraw;

// Esta es otra opción:
// public abstract class Figura {
public class Figura {
	
	private double x;
	private double y;
	
	private double dimension;
	
	/* Si pongo este ya no hace falta llamar a super 
	 * en Circulo o Cuadrado.
	public Figura() {
	}
	*/
	
	public Figura(double x, double y, double dimension) {
		this.x = x;
		this.y = y;
		this.dimension = dimension;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getDimension() {
		return dimension;
	}
	
	// Esta es otra opción: 
	// public abstract void draw();

	public void dibujar() {
		StdDraw.point(getX(), getY());
	}
	
}
