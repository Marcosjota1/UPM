package figurastris;

import stdlib.StdDraw;

public class Cuadrado extends Figura {
	
	public Cuadrado(double x, double y) {
		super(x, y, 0.25);
	}

	@Override
	public void dibujar() {
		double halfWidth = getDimension() / 2.0;
		StdDraw.rectangle(getX(), getY(), halfWidth, halfWidth);
		super.dibujar();
	}
}
