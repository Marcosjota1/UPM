package vector;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class VectorIterador<E> implements Iterator<E> {
	
	VectorIterable<E> vector;
	int length;
	private int index;

	public VectorIterador(VectorIterable<E> vector, int length) {
		this.vector = vector;
		this.length = length;
		index = -1;
	}

	@Override
	public boolean hasNext() {
		return index + 1 < length;
	}

	@Override
	public E next() {
		if ( ! hasNext() ) {
			throw new NoSuchElementException("Te has pasado");
		}
		index++;
		return vector.get(index);
	}

}
