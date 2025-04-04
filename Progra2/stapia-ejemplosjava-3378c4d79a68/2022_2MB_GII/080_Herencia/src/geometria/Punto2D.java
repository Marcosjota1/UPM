package geometria;

import java.util.Arrays;

public class Punto2D {
	
	private double[] coordenadas;
	
	public Punto2D(double x, double y) {
		coordenadas = new double[2];
		coordenadas[0] = x;
		coordenadas[1] = y;
	}
	
	public double[] getCoordenadas() {
		// return coordenadas; Se puede modificar 
		return Arrays.copyOf(coordenadas,2); // No se puede
	}
}
