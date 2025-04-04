package vida;

import java.awt.Color;

import stdlib.StdDraw;

public class Celula {
	protected int x;
	protected int y;
	public static double DIM = 0.6;
	protected Color color;

	public Celula(int x, int y) {
		this.x = x;
		this.y = y;
		color = StdDraw.BLACK;
	}

	public void hacerViva() {
		if (color == StdDraw.BLACK || color == StdDraw.BLUE) {
			color = StdDraw.WHITE;
		}
	}

	public boolean viva() {
		return (color == StdDraw.WHITE || color == StdDraw.RED);
	}

	public void dibujar() {
		StdDraw.setPenColor(color);
		StdDraw.filledSquare(x, y, DIM);
	}

}
