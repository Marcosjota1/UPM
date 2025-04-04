
import puntos.Triangulo;
import stdlib.StdDraw;
import tads.IList;

public class Dibujando {
	
	public static void main(String[] args) {
		dibujarLinea();
		IList<Triangulo> list = Principal.leerTriangulo();
		for ( int i = 0; i < list.size(); ++i) {
			Triangulo t = list.get(i);
			t.dibujarAlzado();
			t.dibujarPlanta();
			//list.get(i).draw();
		}
	}
	
	public static void dibujarLinea() {
		StdDraw.setYscale(-1.2, 1.2);
		StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
		StdDraw.line(0, 0, 1.0, 0);
		StdDraw.line(0, -0.02, 0.03, -0.02);
		StdDraw.line(1-0.03, -0.02, 1.0, -0.02);
	}

	public static void dibujarPuntosEjemplo() {
		StdDraw.setPenRadius(0.02);
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.point(0.1, 0.5+0.1);
		StdDraw.point(0.1, -0.5-0.1);
		StdDraw.point(0.5, 0.0+0.1);
		StdDraw.point(0.5, -0.0-0.1);
		StdDraw.point(0.9, 1+0.1);
		StdDraw.point(0.9, -1-0.1);
	}
}
