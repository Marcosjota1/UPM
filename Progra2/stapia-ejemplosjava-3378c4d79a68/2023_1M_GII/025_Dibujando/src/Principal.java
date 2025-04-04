import cosas.*;
import stdlib.StdDraw;

public class Principal {
	public static void main(String[] args) {
		StdDraw.setXscale(0.0, 100.0);
		StdDraw.setYscale(0.0, 100.0);
		Punto p = new Punto(5.0, 20.0);
		p.dibujar();
		Rectangulo r = new Rectangulo(p, 20.0, 10.0);
		r.dibujar();
		StdDraw.clear();
		p.mover(20.0, 30.0);
		StdDraw.setPenColor(StdDraw.RED);
		// p.dibujar();
		r.dibujar();
		StdDraw.clear();
		StdDraw.setPenColor(StdDraw.BLACK);
		Rectangulo noSeMueve = new Rectangulo(p, new Punto(70,70));
		p.dibujar();
		noSeMueve.dibujar();
		p.mover(20, 20);
		StdDraw.setPenColor(StdDraw.RED);
		p.dibujar();
		noSeMueve.dibujar();
	}
}
