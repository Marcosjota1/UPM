package puntos;

import java.util.*;

public class Punto3D {
	
	public double x;
	public double y;
	public double z;
	
	private Punto3D () {
	}
	
	public Punto3D (Scanner sc) {
		x = sc.nextDouble();
		y = sc.nextDouble();
		z = sc.nextDouble();		
	}
	
	public static Punto3D read (Scanner sc) {
		Punto3D punto = new Punto3D();
		punto.x = sc.nextDouble();
		punto.y = sc.nextDouble();
		punto.z = sc.nextDouble();
		return punto;
	}
	
	public void readPunto (Scanner sc) {
		x = sc.nextDouble();
		y = sc.nextDouble();
		z = sc.nextDouble();
	}

	public String toString () {
		return String.format(Locale.US, "%n [ %.2f, %.2f, %.2f ] ", this.x, y, z);
	}

}
