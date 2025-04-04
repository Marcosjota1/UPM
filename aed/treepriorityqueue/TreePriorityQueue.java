package aed.treepriorityqueue;

import java.util.Iterator;

import es.upm.aedlib.Position;
import es.upm.aedlib.Entry;
import es.upm.aedlib.EntryImpl;
import es.upm.aedlib.tree.*;


public class TreePriorityQueue<K extends Comparable<K>,V> implements PriorityQueue<K,V> {

	public BinaryTree<Entry<K,V>> t;
	private Position<Entry<K,V>> lastPos;
	private int tam;

	public TreePriorityQueue() {
		t = new LinkedBinaryTree<>();
		lastPos = null;
		tam=0;
	}

	public int size() {
		return tam;
	}

	public boolean isEmpty() {
		return tam == 0;
	}

	public Entry<K,V> first() throws EmptyPriorityQueueException {
		if(isEmpty())
			throw new EmptyPriorityQueueException();
		return t.root().element();
	}

	public void enqueue(K k, V v) {
		Entry<K,V> newEntry = new EntryImpl<K,V>(k,v);
		if(isEmpty()) {
			lastPos = t.addRoot(newEntry);
		}
		else {
			Position<Entry<K,V>> p = dondeInsertar(lastPos); 
			if(!t.hasLeft(p)) 
				lastPos = t.insertLeft(p,newEntry);
			else		
				lastPos = t.insertRight(p, newEntry);

			bubbleup(lastPos);
		}
		tam++;
	}

	public Entry<K,V> dequeue() throws EmptyPriorityQueueException {
		if(isEmpty())
			throw new EmptyPriorityQueueException();
		
		Entry<K,V> minEntry = t.root().element();
		if(tam==1) {
			t.remove(lastPos);
			lastPos = null;
		}
		else {	
			swap(t.root(), lastPos);
			Position<Entry<K,V>> ultimo = lastPos; 
			lastPos = ultimoNodo(lastPos); 	// hay que actualizar lastPos tras borrar	
			t.remove(ultimo);
			bubbledown(t.root());
		}
		tam--;
		return minEntry;
	}

	
	public String toString() {
		return t.toString();
	}

	public Iterator<Entry<K, V>> iterator() {
		return t.iterator();
	}
	
	
	//-----------------------------------------------------------------------------------------------------------------------------//
	//---------------------------------------AUXILIARES-------------------------------------------------------------------------------//
	//-----------------------------------------------------------------------------------------------------------------------------//
	private Position<Entry<K,V>> dondeInsertar(Position<Entry<K,V>> p) {
		if(t.isRoot(p)) {
			return p; 
		}
		else {
	        Position<Entry<K, V>> parent = t.parent(p);
	        Position<Entry<K, V>> derParent = t.right(parent);

	        if (p == derParent) {
	            return t.isRoot(parent) ? nodoIzq(parent) : dondeInsertar(parent);//cuando p sea raiz n es el nodo mas a la izquierda del arbol
	        } else {
	            return (!t.hasRight(parent)) ? parent : nodoIzq(t.right(parent)); 
	        }
	    }
	}

	private Position<Entry<K,V>> nodoIzq(Position<Entry<K,V>> p) {
		while(t.hasLeft(p)) 
			p = t.left(p);
		return p;
	}
	private Position<Entry<K,V>> nodoDer(Position<Entry<K,V>> p) {
		while(t.hasRight(p)) 
			p = t.right(p);
		return p;
	}
	private void bubbleup(Position<Entry<K, V>> p) {
	    if (t.isRoot(p) || p.element().getKey().compareTo(t.parent(p).element().getKey()) >= 0) {
	        return; //No es neceasio bubbleup, ya ordenado
	    }
	    swap(p, t.parent(p));
	    bubbleup(t.parent(p));
	}
	private void bubbledown(Position<Entry<K, V>> p) {
	    while (!t.isExternal(p)) {
	        Position<Entry<K, V>> hijoIzq = t.left(p);
	        Position<Entry<K, V>> hijoDer = t.right(p);
	        Position<Entry<K, V>> menor = (hijoDer == null || hijoIzq.element().getKey().compareTo(hijoDer.element().getKey()) < 0) ? hijoIzq : hijoDer;

	        if (p.element().getKey().compareTo(menor.element().getKey()) > 0) { //Mirar si menor de los hijos es menor que padre
	            swap(menor, p);
	            p = menor; // pasas al hijo, para seguir con bucle
	        } else {
	            break; // Termina el bucle si la condición no se cumple
	        }
	    }
	}

	private void swap(Position<Entry<K,V>> p1, Position<Entry<K,V>> p2) {
		Entry<K,V> aux = p1.element(); 	
		t.set(p1, p2.element());
		t.set(p2, aux);					
	}
	private Position<Entry<K,V>> ultimoNodo(Position<Entry<K,V>> p) { //devuelve el ultimo nodo del arbol, el de menor prioridad
	    if (t.isRoot(p)) { //si p es la raiz el ultimo nodo sera el nodo mas a la derecha del arbol
	        return nodoDer(p);
	    } else {
	        Position<Entry<K,V>> parent = t.parent(p);
	        if (p == t.left(parent)) //miras si p = hijo izquierdo
	            return ultimoNodo(parent); 
	        else
	        	return nodoDer(t.left(parent)); // segun la guia "si llegamos a p desde su hijo derecho:n es el nodo más a la derecha del subárbol izquierdo de p
	    }
	}	
}