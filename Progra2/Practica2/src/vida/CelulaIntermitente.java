package vida;

import stdlib.StdDraw;

public class CelulaIntermitente extends Celula implements Tick {
	private int contador;

	public CelulaIntermitente(int x, int y) {
		super(x, y);
		color = StdDraw.BLUE;
		contador = 0;

	}

	public void tick() {
		contador++;
		if (this.contador == 20) {
			if (color == StdDraw.BLUE) {
				color = StdDraw.WHITE;
				contador = 0;
			} else {
				color = StdDraw.BLUE;
				contador = 0;
			}
		}
	}

}
