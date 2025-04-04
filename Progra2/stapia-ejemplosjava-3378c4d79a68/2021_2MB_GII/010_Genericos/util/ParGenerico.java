package util;

/**
 * Esta clase sirve para asociar un par de objetos en 
 * una misma clase que llamamos Par.
 * 
 * @author Santiago Tapia
 *
 * @param <A> Esta es la clase del primer elemento del par 
 * @param <B> Esta es la clase del segundo elemento del par
 */
public class ParGenerico<A, B> {
	
	private A first;
	private B second;
	
	/**
	 * Este metodo es un observador que devuelve el primer
	 * elemento del par de objetos
	 * @return una referencia al primer elemento del par
	 */
	public A getFirst() {
		return first;
	}

	/**
	 * Método que modifica el par cambiando el primer elemento. 
	 * @param first el numero objeto que va a ser el primer elemnto del par
	 */
	public void setFirst(A first) {
		this.first = first;
	}
	
	/**
	 * Este metodo es un observador que devuelve el segundo
	 * elemento del par de objetos
	 * @return Devuelve el segundo
	 */
	public B getSecond() {
		return second;
	}
	
	/**
	 * Método que modifica el par cambiando el segundo elemento. 
	 * @param second el numero objeto que va a ser el segundo elemnto del par
	 */
	public void setSecond(B second) {
		this.second = second;
	} 
	
	public String toString() {
		return String.format("%s: %s", first.toString(), second.toString());
	}
}
