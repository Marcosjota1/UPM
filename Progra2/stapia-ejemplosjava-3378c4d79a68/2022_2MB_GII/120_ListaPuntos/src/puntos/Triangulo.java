package puntos;

import java.util.*;

import stdlib.StdDraw;
import tads.IList;
import tads.LinkedList;

public class Triangulo {
	
	// Atributos -> 3 puntos
	IList<Punto3D> listVertices = new LinkedList<Punto3D>();
	
	public Triangulo (IList<Punto3D> list, Scanner sc) {
		for ( int i = 0; i < 3; ++i ) {
			int indice = sc.nextInt();
			Punto3D punto = list.get(indice-1);
			listVertices.add(i, punto);
		}
	}

	public void dibujarPlanta() {
		StdDraw.setPenRadius(0.003);
		StdDraw.setPenColor(StdDraw.BLACK);
		// Para dibujar en planta tengo que coger las x, y del punto, cambiar 
		// el signo de la y y restar 0.1
		Punto3D p1 = listVertices.get(0);
		Punto3D p2 = listVertices.get(1);
		Punto3D p3 = listVertices.get(2);
		StdDraw.line(p1.x, -p1.y-0.1, p2.x, -p2.y-0.1);
		StdDraw.line(p2.x, -p2.y-0.1, p3.x, -p3.y-0.1);
		StdDraw.line(p3.x, -p3.y-0.1, p1.x, -p1.y-0.1);
	}
	
	public void dibujarAlzado() {
		StdDraw.setPenRadius(0.003);
		StdDraw.setPenColor(StdDraw.BLACK);
		// Para dibujar en planta tengo que coger las x, y del punto, cambiar 
		// el signo de la y y restar 0.1
		Punto3D p1 = listVertices.get(0);
		Punto3D p2 = listVertices.get(1);
		Punto3D p3 = listVertices.get(2);
		StdDraw.line(p1.x, p1.z+0.1, p2.x, p2.z+0.1);
		StdDraw.line(p2.x, p2.z+0.1, p3.x, p3.z+0.1);
		StdDraw.line(p3.x, p3.z+0.1, p1.x, p1.z+0.1);
	}
	
	public String toString() {
		return "\n Triangulo: " + listVertices;
	}

}
