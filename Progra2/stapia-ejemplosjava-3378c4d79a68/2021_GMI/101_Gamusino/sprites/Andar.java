package sprites;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import geom.Punto;

public class Andar implements Movimiento {
	
	public Andar(Gamusino gamusino) {
		this.gamusino = gamusino;
		int deltaX = random.nextInt(DISPERSION+1) - DISPERSION / 2;
		int deltaY = random.nextInt(DISPERSION+1) - DISPERSION / 2; 
		Punto dst = gamusino.getDestino();
		destino = new Punto(dst.getX()+deltaX, dst.getY()+deltaY);
	}
	
	public boolean haFinalizado() {
		return destino.distancia(gamusino.getSituacion()) < DISTANCIA_PARA_ACABAR; 
	}

	public void mueve() {
		if ( destino.getX() > gamusino.getSituacion().getX() ) {
			gamusino.mover(STEP_SIZE, 0);	
		} else {
			gamusino.mover(-STEP_SIZE, 0);
		}
		if ( destino.getY() > gamusino.getSituacion().getY() ) {
			gamusino.mover(0, STEP_SIZE);	
		} else {
			gamusino.mover(0, -STEP_SIZE);
		}
	}

	public void dibujar(Graphics g) {
		g.setColor(Color.RED);
		int xd = destino.getX() - POINT_SIZE / 2;
		int yd = destino.getY() - POINT_SIZE / 2;
		g.fillOval(xd, yd, POINT_SIZE, POINT_SIZE);
	}

	Gamusino gamusino;
	Punto destino;
	
	private static final int POINT_SIZE = 6;
	private static final int STEP_SIZE = 6;
	private static final int DISPERSION = 200;
	private static final int DISTANCIA_PARA_ACABAR = 15; 
	
	private static final Random random = new Random(598);	
}
