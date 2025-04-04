package geometria;

import java.util.Locale;
import java.util.Scanner;

public class Punto {
	
	private final double x;
	private final double y;
	
	public Punto(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Punto(String linea) {
		// Se supone que la cadena es como "45.6 67.1"
		Scanner sc = new Scanner(linea);
		// Esta sentencia obliga a usar el . como separador de decimales
		sc.useLocale(Locale.US);
		
		x = sc.nextDouble();
		y = sc.nextDouble();
		sc.close();
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

}
