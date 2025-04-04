package colecciones;

public class ListaDePuntos implements IListaDePuntos {
	
	private Punto[] lista = new Punto[6];
	private int numeroDeItems = 0;

	@Override
	public int numeroDeItems() {
		return numeroDeItems;
	}

	@Override
	public Punto obtener(int index) {
		return lista[index];
	}

	@Override
	public void insertar(int index, Punto p) {
		for ( int i = numeroDeItems-1; i >= index; --i ) {
			lista[i+1] = lista[i];
		}
		lista[index] = p;
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
