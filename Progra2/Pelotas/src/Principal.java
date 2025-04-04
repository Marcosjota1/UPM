

import pelotas2.Campo;
import stdlib.StdDraw;

public class Principal {

	public static void main(String[] args) {
		Campo campo = new Campo();
		StdDraw.setScale(0, 100);
		// Campo.DIM = 50; NO puedes cambiar al ser final
		StdDraw.enableDoubleBuffering();

		while (true) {
			campo.tick();
			StdDraw.clear();
			campo.dibujar();
			StdDraw.show();
			StdDraw.pause(2);

		}

	}

	public static void ejemplo(String[] args) {
		StdDraw.setScale(-2, +2);
		StdDraw.enableDoubleBuffering();

		for (double t = 0.0; true; t += 0.02) {
			double x = Math.sin(t);
			double y = Math.cos(t);
			StdDraw.clear();
			StdDraw.filledCircle(x, y, 0.05);
			StdDraw.filledCircle(-x, -y, 0.05);
			StdDraw.show();
			StdDraw.pause(1);
		}
	}

}
