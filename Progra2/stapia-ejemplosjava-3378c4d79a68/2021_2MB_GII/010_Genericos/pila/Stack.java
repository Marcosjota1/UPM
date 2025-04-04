package pila;

public class Stack<E> {

	public void apilar(E e) {
		pila[numeroDe] = e;
		numeroDe += 1;
	}
	
	public E despilar() {
		@SuppressWarnings("unchecked")
		E resultado = (E)pila[numeroDe-1];
		pila[numeroDe-1] = null;
		numeroDe--;
		return resultado;
	}
	
	public boolean estaVacia() {
		return numeroDe == 0;
	}
	
	public boolean estaLlena() {
		return numeroDe >= CAPACIDAD;
	}

	private static final int CAPACIDAD = 1024;
	private Object[] pila = new Object[CAPACIDAD];
	private int numeroDe = 0;
	
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.apilar(4);
		stack.apilar(100);
		System.out.println(stack.despilar());
	}
}
