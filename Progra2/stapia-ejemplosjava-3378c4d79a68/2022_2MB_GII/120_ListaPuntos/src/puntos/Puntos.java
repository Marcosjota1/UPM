package puntos;

import stdlib.StdDraw;
import tads.IList;

public class Puntos {
	
	public static void dibujarPolilinea (IList<Punto3D> list, IProyeccion proy) {
		StdDraw.setPenRadius(0.003);
		StdDraw.setPenColor(StdDraw.BLACK);
		for ( int i = 0; i < list.size()-1; ++i ) {
			Punto3D p1 = list.get(i);
			Punto3D p2 = list.get(i+1);
			StdDraw.line(proy.proyectar1(p1), proy.proyectar2(p1), proy.proyectar1(p2), proy.proyectar2(p2));
		}
	}
	
	public static void dibujarPolilineaCerrada(IList<Punto3D> list, IProyeccion proy) {
		Puntos.dibujarPolilinea(list, proy);
		Punto3D p1 = list.get(0);
		Punto3D p2 = list.get(list.size()-1);
		StdDraw.line(proy.proyectar1(p1), proy.proyectar2(p1), proy.proyectar1(p2), proy.proyectar2(p2));		
	}
}
