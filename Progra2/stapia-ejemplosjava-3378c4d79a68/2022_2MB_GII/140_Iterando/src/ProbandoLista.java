import java.util.*;
import vector.*;

public class ProbandoLista {
	
	public static void mostrar(ListaIterable<Integer> v) {
		for ( Integer i : v ) {
			System.out.println("Dato: " + i);
		}		
	}

	public static void mostrar(Iterator<Integer> ite) {
		while ( ite.hasNext() ) {
			Integer i = ite.next();
			System.out.println(i);
		}
	}
	
	public static void mostrarMal(Iterator<Integer> ite) {
		try {
			for ( int i = 0; i < 11; ++i) { 
				System.out.println(ite.next());
			}			
		} catch (Exception e) {
			System.out.println("Se ha lanzado una excepcion");
			System.out.println(e.getMessage());
		}
	}
	
	public static void borrarImpares(Iterator<Integer> ite) {
		while ( ite.hasNext() ) {
			Integer i = ite.next();
			if ( i % 2 == 1 ) {
				ite.remove();
			}
		}		
	}

	public static void borrarInt(Iterator<Integer> ite, int num) {
		while ( ite.hasNext() ) {
			Integer i = ite.next();
			if ( i == num ) {
				ite.remove();
			}
		}		
	}
	
	public static void borrarTodos(Iterator<Integer> ite) {
		while ( ite.hasNext() ) {
			ite.next();
			ite.remove();
		}		
	}
	
	public static void main(String[] args) {
		ListaIterable<Integer> v = new ListaIterable<>();	
		for ( int i = 0; i < 10; ++i) {
			v.add(new Integer(i+1));
		}

		mostrar(v);
		
		mostrarMal(v.iterator());
		
		borrarImpares(v.iterator());
		
		System.out.println("Despues de borrar impares: ");
		mostrar(v.iterator());
		
		borrarInt(v.iterator(),10);
		v.add(22);
		System.out.println("Despues de borrar el ultimo e insertar 22: ");
		mostrar(v.iterator());
		
		borrarTodos(v.iterator());

		System.out.println("Despues de borrar todos: ");
		mostrar(v.iterator());
		System.out.println("No tiene que haber nada");

		v.add(33);
		System.out.println("Despues de insertar 33:");
		mostrar(v.iterator());
	}

}
