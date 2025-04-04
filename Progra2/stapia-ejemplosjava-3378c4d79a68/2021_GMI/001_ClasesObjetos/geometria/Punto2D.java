
package geometria;

import core.Principal;

public class Punto2D {

	public static final int DIMENSION = 2;

	private double x;
	private double y;

	public Punto2D() {
	}

	public Punto2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return this.x;
	}

	public void setX(double x0) {
		x = x0;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		Principal.LOGGER.fine("Me están tocado los atributos");
		this.y = y;
	}

	boolean esIgual(Punto2D other) {
		return Math.abs(this.x - other.x) < 1e-10 && 
				Math.abs(getY() - other.getY()) < 1e-10;
	}

	public void mover(double incX, double incY) {
		setX(getX() + incX);
		setY(getY() + incY);
	}

	public static double norm2(Punto2D punto) {
		return Math.sqrt(punto.getX() * punto.getX() + punto.getY() * punto.getY());
	}

	public double norm2() {
		return Math.sqrt(getX() * getX() + getY() * getY());
	}

	public static double distancia(Punto2D punto1, Punto2D punto2) {
		return Math.sqrt(Math.pow(punto1.x - punto2.x, DIMENSION) + Math.pow(punto1.getY() - punto2.getY(),DIMENSION));
	}

	public double distancia(Punto2D punto) {
		return Math.sqrt(Math.pow(punto.x - x, DIMENSION) + Math.pow(punto.getY() - this.getY(),2));
	}
	
	public static void main(String[] args) {
		Punto2D p1 = new Punto2D(2,4.3);
		Punto2D p2 = new Punto2D(5.7,-1.3);
		Punto2D p3 = new Punto2D();
		
		double d1 = Punto2D.distancia(p1, p2);
		System.out.println("d1 = " + d1);

		double d2 = p1.distancia(p2);
		System.out.println("d2 = " + d2);
		
		double d3 = p2.distancia(p3);
		System.out.println("d3 = " + d3);
	}
}
