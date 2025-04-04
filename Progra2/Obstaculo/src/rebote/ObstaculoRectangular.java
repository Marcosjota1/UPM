package rebote;

import java.awt.Color;

import stdlib.StdDraw;

import stdlib.StdDraw;

public class ObstaculoRectangular extends Obstaculo {
	
	private Color color;

	public ObstaculoRectangular(double x, double y) {
		super(x,y);
		// ancho = 10; // No se puede, es privado
		alto = 10; // Se puede es protected
		color = StdDraw.GREEN;
	}
	
	public void cambiarColor(Color c) {
		color = c;
	}
	
	@Override
	public void dibujar() {
		StdDraw.setPenColor(color);
		super.dibujar();
		StdDraw.setPenColor(StdDraw.BLACK);
	}

}
