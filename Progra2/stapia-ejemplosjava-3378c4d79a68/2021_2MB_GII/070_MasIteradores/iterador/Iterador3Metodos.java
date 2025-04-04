package iterador;

public class Iterador3Metodos {
	
	private int index;
	private Object[] array;
	
	public Iterador3Metodos(Object[] array) {
		this.index = 0; 
		this.array = array;
	}
	
	public boolean hasNext() {
		return index < array.length;
	}

	public Object get() {
		return array[index];
	}
	
	public void advance() {
		++index;
	}
	
	public static void main(String[] args) {
		Double[] vector = { 2.4, 5.6 , -7.8 };
		Iterador3Metodos ite = new Iterador3Metodos(vector);
		for ( ; ite.hasNext(); ite.advance() ) {
			Object obj = ite.get();
			System.out.println(obj.toString());
		}
		
		String[] cadenas = { "hola", "mundo" };
		StringBuilder concatenar = new StringBuilder();
		ite = new Iterador3Metodos(cadenas);
		for ( ; ite.hasNext(); ite.advance() ) {
			Object obj = ite.get();
			if ( obj instanceof String) {
				String aux = (String) obj;
				concatenar.append(aux);
				concatenar.append(" ");
			}
		}
		System.out.println(concatenar.toString());
	}
	
}
