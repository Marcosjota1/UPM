package puntos;

import java.io.File;
import java.util.Locale;
import java.util.Scanner;

import tads.IList;
import tads.LinkedList;

public class Pieza {
	
	IList<Poligono> caras;
	
	public Pieza(String puntosFilename, String carasFilename) {
		IList<Punto3D> list = leerPunto(puntosFilename);
		caras = leerCaras(carasFilename, list);
	}
	
	private static IList<Punto3D> leerPunto(String filename) {
		IList<Punto3D> list = new LinkedList<>();
		// Esto es un try catch que se llama "with resources"
		try ( Scanner sc = new Scanner(new File(filename)) ) {
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

	private static IList<Poligono> leerCaras(String filename, IList<Punto3D> list) {
		IList<Poligono> listP = new LinkedList<>();
		try ( Scanner sc = new Scanner(new File(filename)) ) {
			sc.useLocale(Locale.US);
			while ( sc.hasNextInt() ) {
				Poligono poligono = new Poligono(list, sc);
				listP.add(0, poligono);
			}			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e);
		}
		return listP;
	}
	
	public static void main(String[] args) {
		Pieza p = new Pieza("PuntosCaras.txt", "Caras.txt");
		System.out.println(p.caras);
	}
}
