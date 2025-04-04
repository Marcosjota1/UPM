package vida;

import java.awt.Color;

import stdlib.StdDraw;

public class CelulaClasica extends Celula implements CambiarEstado {
	private Celula[] vecinas;
	private boolean nuevoEstado;
	private int contVecinas = 0;

	public CelulaClasica(int x, int y) {
		super(x, y);
		vecinas = new Celula[8];
		nuevoEstado = false;
	}

	public void indicarVecina(Celula c) {
		vecinas[contVecinas] = c;
		contVecinas++;
	}

	public void calcularNuevoEstado() {
		int num = 0;
		for (int i = 0; i < 8; i++) {
			if (vecinas[i].viva()) {
				num++;
				if (num > 3) {
					break;
				}
			}
		}

		// Aplicamos las reglas clasicas de la evolución
		nuevoEstado = (color == StdDraw.WHITE) ? (num == 2 || num == 3) : (num == 3);
	}

	public void actualizar() {
		if (nuevoEstado) {
			color = StdDraw.WHITE;
		} else {
			color = StdDraw.BLACK;
		}
	}
}
