package dibujos;

public class Punto {
	
	final double x;
	final double y;
	
	/* Escribir el metodo que falta */
	public Punto(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Punto mueve(double incX, double incY) {
		/* Escribir la implementacion */
		return new Punto(x+incX, y+incY);
	}
}
