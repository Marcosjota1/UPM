package dibujos;

import stdlib.StdDraw;

public class Circulo implements IDibujo {
	
	Punto centro;
	double radio;
	
	public Circulo(double cx, double cy, double radio) {
		/* TODO */
		centro = new Punto(cx, cy);
		this.radio = radio;
	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		StdDraw.circle(centro.x, centro.y, radio);
	}

	@Override
	public IDibujo mover(double incX, double incY) {
		// TODO Auto-generated method stub
		return new Circulo(centro.x+incX, centro.y+incY, radio);
	}

}
