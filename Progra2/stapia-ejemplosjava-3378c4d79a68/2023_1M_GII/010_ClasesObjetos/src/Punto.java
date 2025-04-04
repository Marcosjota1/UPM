
public class Punto {
	
	double x;
	double y;
	
	public Punto() {
	}
	
	public Punto(double xi, double yi) {
		x = xi;
		y = yi;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	public void moverConIncremento(double inc) {
		x += inc;
		y += inc;
	}

}
