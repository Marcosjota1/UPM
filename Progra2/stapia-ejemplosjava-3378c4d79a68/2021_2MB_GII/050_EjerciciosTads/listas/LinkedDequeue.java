package listas;

public class LinkedDequeue<E> {
	
	private ListNode<E> primero = null;
	private ListNode<E> ultimo = null;
	
	public void pushFront(E element) {
		ListNode<E> nuevo = new ListNode<>();
		nuevo.data = element;  
		nuevo.next = primero;
		if ( primero == null ) {
			ultimo = nuevo;
		}
		primero = nuevo;
	}
	
	public E popFront() {
		ListNode<E> quitar = primero;
		primero = primero.next;
		return quitar.data;
	}

	public void pushBack(E element) {
		ListNode<E> nuevo = new ListNode<>();
		nuevo.data = element;  
		nuevo.next = null;
		if ( primero == null ) {
			primero = nuevo;
			ultimo = nuevo;
		} else {
			ultimo.next = nuevo;
			ultimo = nuevo;			
		}
	}
	
	public E popBack() {
		if ( primero.next == null ) {
			return popFront();
		} else {
			ListNode<E> penultimo = primero;
			while ( penultimo.next != ultimo ) {
				penultimo = penultimo.next;
			}
			penultimo.next = null;
			E resultado = ultimo.data;
			ultimo = penultimo;
			return resultado;			
		}
	}
	
	public LinkedDequeueIterator<E> iterator() {
		return new LinkedDequeueIterator<E>(primero);
	}	
	
	@Override
	public String toString() {
		ListNode<E> aux = primero;
		String result = "";
		while ( aux != null ) {
			result += aux.data.toString();
			result += " ";
			aux = aux.next;
		}
		return result;
	}
	
	public static void main(String[] args) {
		
		LinkedDequeue<Integer> lista = new LinkedDequeue<>();
		
		System.out.println("Comprobando lista vacia");
		// Comprobar lista vacia
		assert lista.toString().equalsIgnoreCase("") : "La lista recien creada tiene que estar vacia";
		
		System.out.println("Comprobando pushBack");
		lista.pushBack(6);
		
		// Comprobar lista que la lista tiene un 6
		assert lista.toString().equalsIgnoreCase("6 ") : "La lista tiene que tener un 6";
		
		lista.pushBack(-4);
		assert lista.toString().equalsIgnoreCase("6 -4 ") : "La lista tiene que tener un 6 y un -4";
		
		lista.pushFront(1);
		assert lista.toString().equalsIgnoreCase("1 6 -4 ") : "La lista tiene que tener un 6, un -4 y 1 delante";
		
		int ultimo = lista.popBack();
		assert lista.toString().equalsIgnoreCase("1 6 ") : "La lista tiene que tener un 1 y 6";
		assert ultimo == -4 : "El ultimo era -4";
		
		ultimo = lista.popBack();
		assert lista.toString().equalsIgnoreCase("1 ") : "La lista tiene que tener 1";
		assert ultimo == 6 : "El ultimo era 6";

		ultimo = lista.popBack();
		assert lista.toString().equalsIgnoreCase("") : "La lista tiene que tener que estar vacia";
		assert ultimo == 1 : "El ultimo era 1";
		
		System.out.println("Comprobando pushFront");
		lista.pushFront(6);
		
		// Comprobar lista que la lista tiene un 6
		assert lista.toString().equalsIgnoreCase("6 ") : "La lista tiene que tener un 6";
		
		lista.pushFront(-4);
		assert lista.toString().equalsIgnoreCase("-4 6 ") : "La lista tiene que tener un -4 6";
		
		lista.pushBack(1);
		assert lista.toString().equalsIgnoreCase("-4 6 1 ") : "La lista tiene que tener un -4 6 1";

		int primero = lista.popFront();
		assert lista.toString().equalsIgnoreCase("6 1 ") : "La lista tiene que tener 1";
		assert primero == -4 : "El primero era -4";

		primero = lista.popFront();
		assert lista.toString().equalsIgnoreCase("1 ") : "La lista tiene que tener 1";
		assert primero == 6 : "El primero era 6";

		primero = lista.popFront();
		assert lista.toString().equalsIgnoreCase("") : "La lista tiene que tener 1";
		assert primero == 1 : "El primero era -4";
		
		lista.pushBack(6);
		assert lista.toString().equalsIgnoreCase("6 ") : "La lista tiene que tener un 6";
		
		assert false : "Las pruebas han terminado";
		
		System.out.println("He terminado");
	}


}
