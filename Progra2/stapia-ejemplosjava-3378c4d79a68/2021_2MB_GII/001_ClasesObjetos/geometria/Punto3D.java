package geometria;

import core.Principal;

public class Punto3D {
	
	public static final int DIMENSION = 3;
	
	private double x;
	private double y;
	private double z;
	
	public double getX() {
		return this.x;
	}

	public void setX(double x0) {
		Principal.LOGGER.finer("Me están tocando los atributos");
		x = x0;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public void mover(double inc) {
		setX(getX() + inc);
		setY(getY() + inc);
		setZ(getZ() + inc);
	}
	
	public double norm2() {
		return this.x*getX() + getY()*getY() + getZ()*getZ();
	}
	
	public static double distanciaAlOrigen(Punto3D p) {
		return Math.sqrt(p.getX()*p.getX() + p.getY()*p.getY() + p.getZ()*p.getZ());
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")"; 
	}

}
