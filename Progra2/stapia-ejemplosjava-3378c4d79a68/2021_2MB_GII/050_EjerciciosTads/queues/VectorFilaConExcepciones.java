package queues;

public class VectorFilaConExcepciones<E> {
	
	private static final int TAM_MAX = 4;
	private Object[] fila = new Object[TAM_MAX];
	private int enUso = 0; 
	
	public void push(E element) throws VectorFilaLlenaExcepcion {
		if (  enUso < TAM_MAX ) {
			fila[enUso] = element;
			enUso = enUso + 1;
		} else {
			throw new VectorFilaLlenaExcepcion();
		}
	}
	
	public E pull() throws VectorFilaVacioExcepcion {
		if ( enUso == 0 ) {
			throw new VectorFilaVacioExcepcion();
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
	
	public static void main(String[] args) throws VectorFilaVacioExcepcion {
		VectorFilaConExcepciones<Integer> fila = new VectorFilaConExcepciones<>();
		
		/* Se intenta justa la sentencia que puede tener un problema 
		for ( int i = 0; i < 10; ++i ) {
			try {
				fila.push(i);
			} catch (VectorFilaLlenaExcepcion e) {
				System.out.println("La fila está llena");
			}
			System.out.println(fila);
		} 
		*/
		try {
			for ( int i = 0; i < 10; ++i ) {
				fila.push(i);
				System.out.println(fila);
			}
		} catch (VectorFilaLlenaExcepcion e) {
			System.out.println("La fila está llena");
		} catch (Exception e) {
			System.out.println("Cualquier otra excepcion");
		}
		
		for ( int i = 0; i < 10; ++i ) {
			Integer output = fila.pull();
			System.out.println("Output: " + output);
			System.out.println(fila);
		}		
	}
}
