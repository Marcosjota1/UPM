package canicas;

import java.io.*;
import java.util.*;

import util.*;

public class Principal {
	
	public static void main(String[] args) {
		metodoUno();
		metodoDos();
		metodoTres();
		metodoCuatro();
	}

	private static void metodoCuatro() {
		ArrayList<ParGenerico<String, Integer>> lista = new ArrayList<>();
		InputStream datos = Principal.class.getResourceAsStream("datosEdad.txt");
		Scanner sc = new Scanner(datos);
		
		while ( sc.hasNext() ) {
			ParGenerico<String, Integer> item = new ParGenerico<>();
			item.setFirst(sc.next());
			item.setSecond(sc.nextInt());
			lista.add(item);
		}
		sc.close();
		
		System.out.println("metodoCuatro, Esto es la lista:");
		System.out.println(lista.toString());
		
		System.out.println("El size es: " + lista.size());
		ParGenerico<String, Integer> elem = lista.get(2);
		System.out.println("(" + elem.getFirst() + ", " + elem.getSecond() + ")");
		
		ParGenerico<String, Integer> nuevo = new ParGenerico<>();
		nuevo.setFirst("Santiago");
		nuevo.setSecond(100);
		lista.set(2, nuevo);

		System.out.println("metodoCuatro, Esto es la lista modificada:");
		System.out.println(lista.toString());

		lista.add(2, nuevo);
		
		System.out.println("metodoCuatro, Esto es la lista modificada otra vez:");
		System.out.println("Nº:" + lista.size() + " " + lista);
		
		nuevo.setSecond(200);
		System.out.println("metodoCuatro, modificando el nuevo:");
		System.out.println(lista.toString());
	}

	private static void metodoTres() {
		NombreEdad[] peques = new NombreEdad[4];
		InputStream datos = Principal.class.getResourceAsStream("datosCanicas.txt");
		Scanner sc = new Scanner(datos);
		
		int i = 0;
		while ( sc.hasNext() ) {
			peques[i] = new NombreEdad();
			peques[i].setFirst(sc.next());
			peques[i].setSecond(sc.nextInt());
			++i;
		}
		sc.close();
		
		System.out.println(Arrays.toString(peques));
	}

	private static void metodoDos() {
		Par[] peques = new Par[4];
		InputStream datos = Principal.class.getResourceAsStream("/canicas/datosCanicas.txt");
		Scanner sc = new Scanner(datos);
		int i = 0;
		while ( sc.hasNext() ) {
			peques[i] = new Par();
			peques[i].setPeque(sc.next());
			peques[i].setNumeroCanicas(sc.nextInt());
			++i;
		}
		
		Par peque = masCanicas(peques);
		System.out.println(String.format("El que más: %s", peque));
		sc.close();				
	}

	private static Par masCanicas(Par[] peques) {
		Par result = peques[0];
		for ( int i = 1; i < peques.length; ++i ) {
			if ( peques[i].getNumeroCanicas() > result.getNumeroCanicas() ) {
				result = peques[i];
			}
		}
		return result;		
	}

	private static void metodoUno() {
		String[] peques = new String[4];
		int[] numeroDeCanicas = new int[4];
		InputStream datos = Principal.class.getResourceAsStream("/canicas/datosCanicas.txt");
		System.out.println(datos);
		Scanner sc = new Scanner(datos);
		
		int i = 0;
		while ( sc.hasNext() ) {
			peques[i] = sc.next();
			numeroDeCanicas[i] = sc.nextInt();
			++i;
		}
		
		String peque = masCanicas(peques, numeroDeCanicas);
		System.out.println(String.format("El que más: %s", peque));
		sc.close();		
	}

	private static String masCanicas(String[] peques, int[] numeroDeCanicas) {
		String result = peques[0];
		int numeroDelQueMas = numeroDeCanicas[0];
		for ( int i = 1; i < peques.length; ++i ) {
			if ( numeroDeCanicas[i] > numeroDelQueMas ) {
				result = peques[i];
				numeroDelQueMas = numeroDeCanicas[i];
			}
		}
		return result;
	}

}
