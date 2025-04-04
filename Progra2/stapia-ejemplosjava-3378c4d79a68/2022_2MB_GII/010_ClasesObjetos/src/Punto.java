

public class Punto {
	
	double x = 0.5, y = 0.5;
	static double radius = 0.05;
	
	public Punto(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void moverEnDiagonal(double incremento) {
		x += incremento;
		this.y += incremento;
	}
	
	double distToOrigin() {
		return Math.sqrt(this.x*x + y*y);
	}
	
	static double distToOrigin(Punto p) {
		// this.x = 5; MAL!!!
		return Math.sqrt(p.x*p.x + p.y*p.y);
	}
	
	public String toString() {
		// return "x: " + x + " y: " + y;
		return String.format("x: %.2f; y: %.3f", x, y);
	}
	
}
