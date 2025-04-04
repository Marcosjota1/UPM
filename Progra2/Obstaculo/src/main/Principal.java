package main;

import rebote.*;
import stdlib.StdDraw;

import stdlib.StdDraw;

public class Principal {
	
	public static void main(String[] args) {
		Campo campo = new Campo();
		StdDraw.setScale(0, Campo.DIMENSION);
		// Campo.DIMENSION = 50; No puedo
		StdDraw.enableDoubleBuffering();
		
		while (true) {
			campo.tick();
			StdDraw.clear();
			campo.dibujar();
			StdDraw.show();
			StdDraw.pause(2);
		}
	}
}