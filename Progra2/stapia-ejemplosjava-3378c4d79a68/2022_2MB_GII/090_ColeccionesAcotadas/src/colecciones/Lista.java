package colecciones;

public class Lista<E> implements ILista<E> {
	
	private Object[] lista = new Object[6];
	private int numeroDeItems = 0;

	@Override
	public int numeroDeItems() {
		return numeroDeItems;
	}

	@Override
	public E obtener(int index) {
		@SuppressWarnings("unchecked")
		E item = (E)lista[index];
		return item;
	}

	
	@Override
	public void insertar(int index, E item) {
		for ( int i = numeroDeItems-1; i >= index; --i ) {
			lista[i+1] = lista[i];
		}
		Object obj = item;
		lista[index] = obj;
		numeroDeItems += 1;		
	}

	@Override
	public void quitar(int index) {
		// Punto item = lista[index]; Solo si lo tengo que devolver
		for ( int i = index; i < numeroDeItems -1; ++i ) {
			lista[i] = lista[i+1];
		}
		numeroDeItems -= 1;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		for ( int i = 0; i < numeroDeItems; ++i ) {
			result.append(lista[i]);
			result.append(" ");
		}
		return result.toString();
	}

}
