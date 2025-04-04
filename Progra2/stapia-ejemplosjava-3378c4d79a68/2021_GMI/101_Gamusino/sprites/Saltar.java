package sprites;

import java.awt.Graphics;
import java.util.Random;

public class Saltar implements Movimiento {
	
	public Saltar(Gamusino gamusino) {
		this.gamusino = gamusino;
	}
	
	public boolean haFinalizado() {
		return numeroSaltos  == 2;
	}

	public void mueve() {
		gamusino.mover(random.nextInt(STEP_SIZE), random.nextInt(STEP_SIZE));
		++numeroSaltos;
	}

	public void dibujar(Graphics g) {
	}

	private static final int STEP_SIZE = 30;
	private static final Random random = new Random(598);
	private Gamusino gamusino;
	private int numeroSaltos = 0;	
}
