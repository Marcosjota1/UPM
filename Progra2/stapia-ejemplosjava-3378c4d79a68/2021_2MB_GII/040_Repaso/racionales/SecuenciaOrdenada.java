package racionales;

public class SecuenciaOrdenada {
	
	Nodo first = null;
	
	public void insert(Fraccion element) {
		if ( first == null ) {
			first = new Nodo(element, null); 
		} else if ( element.compareTo(first.element) < 0 ) {
			first = new Nodo(element, first);
		} else {
			
			Nodo anterior = first;
			
			// Esto es: el nuevo elemento es menor
			// estrictamente que el anterior por el 
			// que me voy.
			// element.compareTo(anterior.next.element) >= 0 
			
			// Comprobar que no he llegado al final
			// anterior.next != null
			
			// Evaluacion en cortocircuito
			while ( anterior.next != null && element.compareTo(anterior.next.element) >= 0) {
				anterior = anterior.next;
			}
			
			anterior.next = new Nodo(element, anterior.next);
		}
	}
	
	@Override
	public String toString() {
		String result = "[";
		Nodo aux = first;
		while ( aux != null && aux.next != null) {
			result = result + aux.element;
			result = result + ", ";
			aux = aux.next;
		}
		if ( aux != null ) {
			result = result + aux.element;
		}
		result = result + "]";
		return result;
	}
	
	public static void main(String[] args) {
		SecuenciaOrdenada seq = new SecuenciaOrdenada();
		seq.insert(new Fraccion(4,3));
		System.out.println(seq);
		seq.insert(new Fraccion(1,2));
		System.out.println(seq);
		seq.insert(new Fraccion(17,4));
		System.out.println(seq);
		seq.insert(new Fraccion(5,3));
		System.out.println(seq);
	}
}
