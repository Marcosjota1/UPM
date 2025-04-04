package listas;

public class LinkedDequeueIterator<E> {
	
	private ListNode<E> aux;
	
	public LinkedDequeueIterator(ListNode<E> primero) {
		aux = new ListNode<>();
		aux.next = primero;
	}
	
	public boolean hasNext() {
		return aux.next != null;
	}
	
	public E next() {
		aux = aux.next;
		return aux.data;
	}
	
	public static void main(String[] args) {
		LinkedDequeue<Integer> lista = new LinkedDequeue<>();

		LinkedDequeueIterator<Integer> ite1 = lista.iterator();
		
		while ( ite1.hasNext() ) {
			int numero = ite1.next();
			System.out.println(numero);
		}
		
		lista.pushBack(1);
		lista.pushBack(7);
		lista.pushBack(-8);
		
		LinkedDequeueIterator<Integer> ite2 = lista.iterator();
		
		while ( ite2.hasNext() ) {
			int numero = ite2.next();
			System.out.println(numero);
		}
	}

}
