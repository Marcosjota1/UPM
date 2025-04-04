package rebote;

import java.util.Random;

import stdlib.StdDraw;
import java.awt.Color;

public class Campo {
	
	private static final double DELTA_TIEMPO = 0.05;
	public static final Random r = new Random();
	public static final double DIMENSION = 100.0;
	
	private PelotaAzul pelota;
	
	private Obstaculo[] obstaculos;
	int contador;
	
	public Campo() {
		pelota = new PelotaAzul(50,50);
		ObstaculoRectangular re = new ObstaculoRectangular(80,30);
		re.cambiarColor(StdDraw.BLUE);
		obstaculos = new Obstaculo[6];
		obstaculos[0] = new Obstaculo(10,80);
		obstaculos[1] = re;
		obstaculos[2] = new Obstaculo(40,10);
		obstaculos[3] = new Obstaculo(15,80);
		obstaculos[4] = new ObstaculoCircular();
		obstaculos[5] = new ObstaculoCircular();
		// obstaculos[5] = pelota; NO se puede porque NO es un Obstaculo
	}
	
	public void cambiarColorRect(Color color) {
		for ( int i = 0; i < obstaculos.length; ++i ) {
			if ( obstaculos[i] instanceof ObstaculoRectangular) {
				ObstaculoRectangular re = (ObstaculoRectangular)obstaculos[i]; 
				re.cambiarColor(color);
			}
		}	
	}
	
	public void dibujar() {
		pelota.dibujar();
		for ( int i = 0; i < obstaculos.length; ++i ) {
			// obstaculos[i].cambiarColor(StdDraw.BLUE); NO puedo
			obstaculos[i].dibujar();
		}
	}

	public void tick() {
		pelota.mover(DELTA_TIEMPO);
	}
}

