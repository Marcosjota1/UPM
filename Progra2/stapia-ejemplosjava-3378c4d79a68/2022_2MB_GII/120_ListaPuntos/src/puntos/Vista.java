package puntos;

import tads.IList;

public class Vista {
	
	Pieza pieza;
	IProyeccion proyeccion;
	
	public Vista(Pieza pieza, IProyeccion proyeccion) {
		this.pieza = pieza;
		this.proyeccion = proyeccion;
	}
	
	public void pintar() {
		IList<Poligono> listPol = pieza.caras;
		for (int i = 0; i < listPol.size(); ++i ) {
			Poligono poligono = listPol.get(i);
			IList<Punto3D> listPuntos = poligono.vertices;
			Puntos.dibujarPolilineaCerrada(listPuntos, proyeccion);
		}
	}
}
