package rebote;

import java.util.Random;

import stdlib.StdDraw;

public class ObstaculoCircular extends Obstaculo {
	
	static final Random r = new Random();
	
	public ObstaculoCircular() {
		super(r.nextDouble()*100,r.nextDouble()*100);
	}
	
	@Override
	public void dibujar() {
		StdDraw.filledCircle(x, y, alto);
	}
}