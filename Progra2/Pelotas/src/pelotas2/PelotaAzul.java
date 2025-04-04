package pelotas2;

import pelotas1.Campo;
import stdlib.StdDraw;

public class PelotaAzul implements Pelota {

	private double x;
	private double y;

	public static double vx;
	public static double vy;

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
		StdDraw.filledCircle(x, y, 2);
	}
		
		
		
	public double []posicion(){
		double[] r = { x , y};
		return r;
	}
	

}
  
