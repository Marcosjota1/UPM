package pelotas1;

import java.util.Random;

public class Campo {
	private final static double DELTA_TIEMPO = 0.01;
	public final static double DIM = 100.0;

	private PelotaAzul[] pelotasAzules;
	private PelotaRoja[] pelotasRojas;
	public static final Random r = new Random();
	int contador = 0;

	public Campo() {
		pelotasRojas = new PelotaRoja[3];
		pelotasRojas[0] = new PelotaRoja(10, 80);
		pelotasRojas[1] = new PelotaRoja(50, 50);
		pelotasRojas[2] = new PelotaRoja(40, 10);
		pelotasAzules = new PelotaAzul[3];
		pelotasAzules[0] = new PelotaAzul(15, 80);
		pelotasAzules[1] = new PelotaAzul(55, 50);
		pelotasAzules[2] = new PelotaAzul(45, 10);
		contador = 0;

	}

	public void tick() {
		for (int i = 0; i < pelotasRojas.length; i++) {
			pelotasRojas[i].mover(DELTA_TIEMPO);

		}
		for (int i = 0; i < pelotasAzules.length; i++) {
			pelotasAzules[i].mover(DELTA_TIEMPO);

		}
		if (contador % 500 == 1) {
			PelotaAzul.vx = 1 + r.nextInt(10);
			PelotaAzul.vy = 1 + r.nextInt(10);
		}

	}

	public void dibujar() {
		for (int i = 0; i < pelotasRojas.length; i++) {
			pelotasRojas[i].dibujar();
		}
		for (int i = 0; i < pelotasAzules.length; i++) {
			pelotasAzules[i].dibujar();
		}
	}
}
