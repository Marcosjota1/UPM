package modificar;

public class Punto {
	
	double x;
	double y;
	
	public Punto(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Punto(Punto p) {
		this.x = p.x;
		this.y = p.y;
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
