package geometria;

import java.util.*;

public class Punto {
	private final double x; 
	private final double y;
	
	public Punto(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Punto(String coords) {
		// Obtiene las coordenadas de "34.6 67.4" 
		Scanner sc = new Scanner(coords);
		// Para ello hay que fijar el "locale" (alias configuración regional)
		sc.useLocale(Locale.US);
		this.x = sc.nextDouble();
		this.y = sc.nextDouble();
		sc.close();
	}

	static double distancia(Punto p1, Punto p2) {
		return Math.sqrt((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y));
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")"; 
	}
}
