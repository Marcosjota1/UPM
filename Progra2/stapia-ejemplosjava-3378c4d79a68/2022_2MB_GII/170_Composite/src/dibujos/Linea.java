package dibujos;

import stdlib.StdDraw;

public class Linea extends Punto implements IDibujo {
	
	Punto extra;
	
	public Linea(double x1, double y1, double x2, double y2) {
		/* TODO */
		super(x1, y1);
		extra = new Punto(x2, y2);
	}
	
	@Override
	public void dibujar() {
		StdDraw.line(x, y, extra.x, extra.y);
	}
	
	@Override 
	public IDibujo mover(double incX, double incY) {
		return new Linea(x + incX, y + incY, extra.x + incX, extra.y + incY);
	}

}
