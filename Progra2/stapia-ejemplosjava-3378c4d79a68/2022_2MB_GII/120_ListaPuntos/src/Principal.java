import java.io.*;
import java.util.*;

import puntos.*;
import tads.IList;
import tads.LinkedList;

public class Principal {
	
	/*
	 * Escribir un main para que lea puntos y triangulos. 
	 */
	public static IList<Triangulo> leerTriangulo() {
		IList<Punto3D> list = leerPunto();
		IList<Triangulo> listTriangulos = new LinkedList<>();
		try ( Scanner sc = new Scanner(new File("Triangulos.txt")) ) {
			sc.useLocale(Locale.US);
			while ( sc.hasNextInt() ) {
				Triangulo triangulo = new Triangulo(list, sc);
				listTriangulos.add(0, triangulo);
			}			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e);
		}
		return listTriangulos;
		// System.out.println(listTriangulos);
	}
	
	/* Escribir un metodo que lea una lista de 
	 * puntos 3D y los guarde en una lista. 
	 * Mostrar la lista al final.
	 */
	public static IList<Punto3D> leerPunto() {
		IList<Punto3D> list = new LinkedList<>();
		// Esto es un try catch que se llama "with resources"
		try ( Scanner sc = new Scanner(new File("Puntos.txt")) ) {
			sc.useLocale(Locale.US);
			while ( sc.hasNextDouble() ) {
				list.add(list.size(), Punto3D.read(sc));
			}			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e);
		}
		// System.out.println(list);
		return list;
	}

}
