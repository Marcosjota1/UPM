package modificar;

public class Punto {
	
	private double x;
	private double y;
	
	public Punto(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Punto(Punto otro) {
		this.x = otro.x;
		this.y = otro.y;
	}
	
	public void giro(double alpha) {
		double aux = x * Math.cos(alpha) - y * Math.sin(alpha);
		y = x * Math.sin(alpha) + y * Math.cos(alpha);
		x = aux;
	}
	
	@Override
	public String toString() {
		return String.format("(%.2f, %.2f)", x, y);
	}
}
