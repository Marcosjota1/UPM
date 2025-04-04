package vector;

import java.util.Iterator;

public class VectorIterable<E> implements Iterable<E> {
	
	private Object array[];
	
	public VectorIterable(int size) {
		array = new Object[size];
	}
	
	public void set(int index, E item) {
		array[index] = item;
	}
	
	public E get(int index) {
		@SuppressWarnings("unchecked")
		E item = (E) array[index]; 
		return item;			
	}

	@Override
	public Iterator<E> iterator() {
		return new VectorIterador<E>(this,array.length);
	}
}
