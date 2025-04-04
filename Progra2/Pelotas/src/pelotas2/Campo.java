package pelotas2;

import java.util.Random;

import pelotas1.PelotaAzul;

public class Campo {
	private final static double DELTA_TIEMPO = 0.01;
	public final static double DIM = 100.0;

	private Pelota[] pelotas;
	public static final Random r = new Random();
	int contador = 0;

	public Campo() {
		pelotas = new Pelota[6];
		pelotas[0] = new PelotaRoja(10, 80);
		pelotas[1] = new PelotaRoja(50, 50);
		pelotas[2] = new PelotaRoja(40, 10);
		pelotas[3] = new PelotaAzul(15, 80);
		pelotas[4] = new PelotaAzul(55, 50);
		pelotas[5] = new PelotaAzul(45, 10);
		contador = 0;

	}

	public void tick() {
		for (int i = 0; i < pelotas.length; i++) {
			pelotas[i].mover(DELTA_TIEMPO);
			
			if (contador % 500 == 1) {
				PelotaRoja.vx = 1 + r.nextInt(10);
				PelotaRoja.vy = 1 + r.nextInt(10);
			}

		}
		
		++contador;
		

	}

	public void dibujar() {
		for (int i = 0; i < pelotas.length; i++) {
			pelotas[i].dibujar();
		}
		}
		
}
