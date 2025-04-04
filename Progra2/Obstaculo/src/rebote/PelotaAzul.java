package rebote;

import stdlib.StdDraw;

public class PelotaAzul {
	
	private double x;
	private double y;
	
	private double vx;
	private double vy;
	
	public PelotaAzul(double x, double y) {
		this.x = x;
		this.y = y;
		vx = 2;
		vy = 1;
	}
	
	public void mover(double deltaTiempo) {
		x = x + vx * deltaTiempo;
		y = y + vy * deltaTiempo;
		if ( x >= Campo.DIMENSION || x <= 0) {
			vx = -vx;
		}
		if ( y > Campo.DIMENSION || y <= 0 ) {
			vy = -vy;
		}
	}
	
	public void dibujar() {
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledCircle(x, y, 1);
		StdDraw.setPenColor(StdDraw.BLACK);
	}
}
