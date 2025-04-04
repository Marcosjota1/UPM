package pelotas1;

import pelotas2.Pelota;
import stdlib.StdDraw;

public class PelotaAzul implements Pelota  {

	private double x;
	private double y;

	public static double vx = 1;
	public static double vy = 2;

	public PelotaAzul(double x, double y) {
		this.x = x;
		this.y = y;
		vx = 2;
		vy = 1;		

	}

	public void mover(double deltaTiempo) {
		x = x + vx * deltaTiempo;
		y = y + vy * deltaTiempo;
		if (x >= Campo.DIM || x<= 0) {
			vx = -vx;
		}
		if (y >= Campo.DIM || x<= 0) {
			vy = -vy;
		}
	}

	public void dibujar() {
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledCircle(x, y, 1);
	}

	@Override
	public double[] posicion() {
		// TODO Auto-generated method stub
		return null;
	}

}
  
