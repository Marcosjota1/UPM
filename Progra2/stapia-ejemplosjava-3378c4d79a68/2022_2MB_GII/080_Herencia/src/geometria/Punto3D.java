package geometria;

public class Punto3D extends Punto2D {
	
	private double z;
	
	public Punto3D(double x, double y, double z) {
		super(x,y);
		this.z = z;
	}
	
	public double getCoordenada(int i) {
		if ( i < 2 ) {
			return super.getCoordenadas()[i];
		} else {
			return z;
		}
	}

}
