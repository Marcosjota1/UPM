package figurasbis;

import stdlib.StdDraw;

public class Cuadrado extends Figura {
	
	public Cuadrado(double x, double y, double lado) {
		// Figura(generarPuntos(x,y,lado));
		super(generarPuntos(x, y, lado));
	}
	
	private static Punto[] generarPuntos(double x, double y, double lado) {
		Punto[] puntos = { 
			new Punto(x, y),
			new Punto(x+lado, y),
			new Punto(x+lado, y + lado),
			new Punto(x, y+lado)
		};
		return puntos;
	}
	
	@Override
	public void dibujar() {
		StdDraw.setPenColor(StdDraw.RED);
		super.dibujar();
	}

}
