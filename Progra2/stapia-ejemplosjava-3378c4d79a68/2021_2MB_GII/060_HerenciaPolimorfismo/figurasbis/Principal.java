package figurasbis;

import stdlib.StdDraw;

public class Principal {
	
	public static void main(String[] args) {
		StdDraw.setPenRadius(0.05);
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.point(0.5, 0.5);
		
		StdDraw.setPenColor(StdDraw.MAGENTA);
		
		Punto[] puntos = {
			new Punto(0.25, 0.25),
			new Punto(0.75, 0.25),
			new Punto(0.75, 0.75),
			new Punto(0.25, 0.75),
		};
		Figura fig = new Figura(puntos);
		fig.dibujar();
		
		StdDraw.setPenRadius(0.01);
		StdDraw.setPenColor(StdDraw.BLUE);

		puntos[0].setX(0.1);
		
		fig.dibujar();
		
		Figura cuadrado = new Cuadrado(0.8, 0.8, 0.15);
		cuadrado.dibujar();
	}

}
