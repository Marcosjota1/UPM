package sprites;

import java.awt.Graphics;

public interface Movimiento {
	public boolean haFinalizado();
	public void mueve();
	public void dibujar(Graphics g);
}
