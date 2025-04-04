package figurastris;

import stdlib.StdDraw;

public class Circulo extends Figura {

	public Circulo() {
		super(2.3, 4.5, 5); /* Tiene que ir al principio */
	}
	
	public Circulo(double x, double y, double dimension) {
		super(x, y, dimension);
	}
	
	@Override
	public void dibujar() {
		StdDraw.circle(getX(), getY(), getDimension()); 
		// super.dibujar(); /* Puede ir donde yo quiera */
	}
	
}
