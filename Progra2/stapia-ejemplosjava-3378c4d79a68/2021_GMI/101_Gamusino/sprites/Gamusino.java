package sprites;

import java.awt.*;
import java.util.Random;

import geom.Punto;

public class Gamusino {
	
	private static final int MAX_MOVIMIENTOS = 7;
	private Punto situacion; 
	private Punto destino;

	private static final int POINT_SIZE = 8; 
	private static final Random random = new Random(345);
	
	private Andar[] movimientos = new Andar[MAX_MOVIMIENTOS];
	private int index = random.nextInt(MAX_MOVIMIENTOS);
	
	public Gamusino() {
		situacion = new Punto(random.nextInt(500),random.nextInt(500));
		destino = new Punto(250, 250);		
		for ( int i = 0; i < MAX_MOVIMIENTOS; ++i ) {
			movimientos[i] = new Andar(this);
		}
	}
	
	public void tick() {
		if ( Math.random() < 0.1 ) {
			int indexBuscado = -1; 
			for ( int i = 0; i < movimientos.length && indexBuscado == -1; ++i) {
				if ( movimientos[i] == null ) {
					indexBuscado = i;
				}
			}
			if ( indexBuscado != -1 ) {
				movimientos[indexBuscado] = new Andar(this);
			}
		}
		
		if ( movimientos[index] == null ) {
			index = random.nextInt(MAX_MOVIMIENTOS);
		} else {
			movimientos[index].mueve();		
			if ( movimientos[index].haFinalizado() ) {
				movimientos[index] = null;
			}
		}
	}
	
	public void mover(int incX, int incY) {
		situacion.mover(incX, incY);
	}

	public Punto getDestino() {
		return destino;
	}

	public Punto getSituacion() {
		return situacion;
	}

	public void dibujar(Graphics g) {	
		// Situacion
		g.setColor(Color.WHITE);
		int x = getSituacion().getX() - POINT_SIZE / 2;
		int y = getSituacion().getY() - POINT_SIZE / 2;
		g.fillOval(x, y, POINT_SIZE, POINT_SIZE);
		
		g.setColor(Color.BLUE);
		int xd = getDestino().getX() - POINT_SIZE / 2;
		int yd = getDestino().getY() - POINT_SIZE / 2;
		g.fillOval(xd, yd, POINT_SIZE, POINT_SIZE);
		
		for ( int i = 0; i < movimientos.length; ++i ) {
			if ( movimientos[i] != null ) {
				movimientos[i].dibujar(g);
			}
		}
	}

}
