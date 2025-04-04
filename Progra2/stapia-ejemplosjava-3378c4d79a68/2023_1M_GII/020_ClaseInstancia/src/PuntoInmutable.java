
public class PuntoInmutable { // Es inmutable
	
	private double x;
	private double y;
	
	public PuntoInmutable(double ix, double iy) {
		x = ix;
		y = iy;
	}
	
	public String toString() {
		return "PuntoFinal: " + x + " " + y;
	}

}
