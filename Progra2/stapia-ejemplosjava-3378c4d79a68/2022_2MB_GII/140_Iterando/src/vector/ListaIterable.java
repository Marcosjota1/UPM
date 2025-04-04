package vector;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaIterable<E> implements Iterable<E> {
	
	Nodo<E> primero = null;
	Nodo<E> ultimo = null;
	
	public void add(E item) {
		/* Precondicion "privada": ultimo es una referencia
		 * al ultimo nodo.
		 * Postcondicion "privada": ultimo tiene que seguir 
		 * apuntando al ultimo nodo
		 */
		Nodo<E> nuevoNodo = new Nodo<>();
		nuevoNodo.dato = item;
		nuevoNodo.sig = null; // Redundante
		if ( primero != null ) {
			ultimo.sig = nuevoNodo;
			ultimo = ultimo.sig;			
		} else {
			primero = nuevoNodo;
			ultimo = nuevoNodo;
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new ListaIteradorBorra(this);
	}
	
	class ListaIteradorBorra implements Iterator<E> {
		
		boolean antesPrimero = true;
		Nodo<E> cursor;
		Nodo<E> anterior;
		ListaIterable<E> lista;
		
		public ListaIteradorBorra(ListaIterable<E> lista) {
			this.lista = lista;
		}

		@Override
		public boolean hasNext() {
			return (antesPrimero && lista.primero != null) 
					|| (!antesPrimero && cursor.sig != null);
		}

		@Override
		public E next() {
			if ( ! hasNext() ) {
				throw new NoSuchElementException();
			}
			if ( antesPrimero ) {
				anterior = null;
				cursor = primero;
				antesPrimero = false;
			} else {
				anterior = cursor;
				cursor = cursor.sig;
			}
			return cursor.dato;
		}
		
		@Override
		public void remove() {
			if ( antesPrimero ) {
				throw new IllegalStateException("didn't call next yet");
			} 
			
			if ( anterior == null ) {
				/* Remove first element from list */
				lista.primero = primero.sig;
				/* Since first is removed, iterator is now "antesPrimero" */
				antesPrimero = true;
			} else if ( anterior == cursor ) {
				throw new IllegalStateException("didn't call next again (since last call), that is, remove was called twice"); 
			} else if ( cursor == lista.ultimo ) {
				/* Remove last element */
				anterior.sig = null;
				cursor = anterior;
				ultimo = anterior;
			} else {
				/* Remove intermediate element, normal case */
				anterior.sig = cursor.sig;
				cursor = anterior;
			}
		}
	}
}


