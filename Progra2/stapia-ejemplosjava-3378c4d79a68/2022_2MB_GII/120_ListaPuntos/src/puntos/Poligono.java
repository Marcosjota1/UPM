package puntos;

import java.util.Scanner;

import tads.ArrayList;
import tads.IList;

public class Poligono {
	
	IList<Punto3D> vertices = new ArrayList<>();
	
	public Poligono (IList<Punto3D> list, Scanner sc) {
		int numVertices = sc.nextInt();
		for ( int i = 0; i < numVertices; ++i ) {
			int indice = sc.nextInt();
			Punto3D punto = list.get(indice-1);
			vertices.add(i, punto);
		}
	}
	
	public String toString() {
		return "\nPoligono: " + vertices.toString();
	}
}
