package vida;

import java.util.Scanner;

import stdlib.StdDraw;

public class Principal {

	public static void main(String[] args) {
		System.out.println("Bienvenido al juego de la vida");
		Scanner sc = new Scanner(System.in);
		Mundo m = new Mundo(sc);
		StdDraw.setCanvasSize(768, 768); // Para el que lo quiera ver más grande puede aumentar aquí el tamaño
		StdDraw.setScale(-2.0, Mundo.TAM + 1.0);

		StdDraw.enableDoubleBuffering();

		while (true) {
			m.tick();
			StdDraw.clear();
			m.dibujar();
			StdDraw.show();
			StdDraw.pause(10);
		}
	}
}
