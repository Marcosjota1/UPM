package queues;

public class VectorFila<E> {
	
	private static final int TAM_MAX = 4;
	private Object[] fila = new Object[TAM_MAX];
	private int enUso = 0; 
	
	public boolean push(E element) {
		if (  enUso < TAM_MAX ) {
			fila[enUso] = element;
			enUso = enUso + 1;
			return true;
		} else {
			return false;
		}
	}
	
	public E pull() {
		if ( enUso == 0 ) {
			return null;
		} else {
			@SuppressWarnings("unchecked")
			E result = (E)fila[0];
			for ( int i = 0; i < enUso - 1; ++i ) {
				fila[i] = fila[i+1];
			}
			fila[enUso-1] = null;
			enUso = enUso - 1;
			return result;	
		}	
	}
	
	@Override
	public String toString() {
		String result = "";
		for ( int i = 0; i < enUso; i++ ) {
			result += fila[i].toString() + " ";
		}
		return result;
	}
	
	public static void main(String[] args) {
		VectorFila<Integer> fila = new VectorFila<>();
		
		for ( int i = 0; i < 10; ++i ) {
			boolean ok = fila.push(i);
			System.out.println("Exito: " + ok);
			System.out.println(fila);
		}
		
		for ( int i = 0; i < 10; ++i ) {
			Integer output = fila.pull();
			System.out.println("Output: " + output);
			System.out.println(fila);
		}		
	}
}
