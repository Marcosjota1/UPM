package vida;

import stdlib.StdDraw;

public class SiempreIgual extends Celula {

	public SiempreIgual(int x, int y, boolean estado) {
		super(x, y);
		if (estado) {
			color = StdDraw.RED;
		} else {
			color = StdDraw.YELLOW;
		}
	}

}
