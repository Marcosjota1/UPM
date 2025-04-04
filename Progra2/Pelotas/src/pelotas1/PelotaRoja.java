package pelotas1;

import stdlib.StdDraw;

public class PelotaRoja {

	private double x;
	private double y;

	public static double vx = 1;
	public static double vy = 2;

	public PelotaRoja(double x, double y) {
		this.x = x;
		this.y = y;

	}

	public void mover(double deltaTiempo) {
		x = x + vx * deltaTiempo;
		y = y + vy * deltaTiempo;
		if (x > Campo.DIM) {
			x = 0;
		}
		if (y > Campo.DIM) {
			y = 0;
		}
	}

	public void dibujar() {
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledCircle(x, y, 1);
	}

}
  

