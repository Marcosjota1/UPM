package vector;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaIterador<E> implements Iterator<E> {
	
	Nodo<E> cursor;

	public ListaIterador(Nodo<E> primero) {
		cursor = primero;
	}

	@Override
	public boolean hasNext() {
		return cursor != null;
		// return cursor != null && cursor.sig != null; Da mas problemas (creo)
	}

	@Override
	public E next() {
		if ( ! hasNext() ) {
			throw new NoSuchElementException();
		}
		E e = cursor.dato;
		cursor = cursor.sig;
		return e;
	}

}
