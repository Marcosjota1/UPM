package vida;

import java.util.Scanner;

public class Factoria {
	public static Celula[][] crearCelulas(Scanner sc, int tama ) {
		int nEspeciales = sc.nextInt();
		Celula[][] celulas = new Celula[tama][tama];
		for (int i = 0; i < nEspeciales; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			String cadenaCelulas = sc.next();
			if (cadenaCelulas.equals("Viva")) {
				celulas[x][y] = new CelulaClasica(x, y);
				celulas[x][y].viva();
			}
			if (cadenaCelulas.equals("CelulaIntermitente")) {
				celulas[x][y] = new CelulaIntermitente(x, y);
			}
			if (cadenaCelulas.equals("SiempreIgual")) {
				boolean estado = sc.nextBoolean();
				celulas[x][y] = new SiempreIgual(x, y, estado);
			}
		}
		for (int t = 0; t < 100; t++) {
			for (int j = 0; j < 100; j++) {
				if (celulas[t][j] == null) {
					celulas[t][j] = new CelulaClasica(t, j);
				}
			}
		}
		return celulas;
	}
}