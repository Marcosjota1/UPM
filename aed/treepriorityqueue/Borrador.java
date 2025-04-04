package aed.treepriorityqueue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import es.upm.aedlib.Position;
import es.upm.aedlib.Entry;
import es.upm.aedlib.EntryImpl;
import es.upm.aedlib.tree.*;

public class Borrador<K extends Comparable<K>, V> implements PriorityQueue<K, V> {

    private BinaryTree<Entry<K, V>> t;
    private Position<Entry<K, V>> lastPos;
    private int tam;

    public Borrador() {
        t = new LinkedBinaryTree<>();
        lastPos = null;
        tam = 0;
    }

    public int size() {
        return tam;
    }

    public boolean isEmpty() {
        return tam == 0;
    }

    public Entry<K, V> first() throws EmptyPriorityQueueException {
        if (isEmpty()) {
            throw new EmptyPriorityQueueException();
        }
        return t.root().element();
    }

    public void enqueue(K k, V v) {
        Entry<K, V> newEntry = new EntryImpl<>(k, v);
        if (isEmpty()) {
            lastPos = t.addRoot(newEntry);
        } else {
            insertBelow(newEntry, lastPos);
			/*
			 * if(!t.hasRight(lastPos)) { lastPos = t.left(lastPos); } else{ lastPos =
			 * t.right(lastPos); }
			 */
            bubbleUp(lastPos);
            
        }
        tam++;
    }
    
    private void insertBelow(Entry<K, V> entry, Position<Entry<K, V>> p) {
        if (t.left(p) == null) {
           lastPos = t.insertLeft(p, entry);
        } else if (t.right(p) == null) {
            lastPos = t.insertRight(p, entry);
        } else {
            insertBelow(entry, t.left(p));
        }
    }

    private void bubbleUp(Position<Entry<K, V>> p) {
        while (!t.isRoot(p)) {
            Position<Entry<K, V>> parent = t.parent(p);
            if (compareEntries(p.element(), parent.element()) >= 0) {
                break;
            }
            swap(p, parent);
            p = parent;
        }
        lastPos = p; 
    }

    private void swap(Position<Entry<K, V>> p1, Position<Entry<K, V>> p2) {
        Entry<K, V> temp = p1.element();
        t.set(p1, p2.element());
        t.set(p2, temp);
    }

    public Entry<K, V> dequeue() throws EmptyPriorityQueueException {
        if (isEmpty()) {
            throw new EmptyPriorityQueueException();
        }       
        Entry<K, V> minEntry = t.root().element();
        if (tam == 1) {
            t.remove(t.root());
            lastPos = null;
        } else {
            swap(t.root(), lastPos);
            t.remove(lastPos);
            bubbleDown(lastPos);
        }
        tam--;
        return minEntry;
    }

    private void bubbleDown(Position<Entry<K, V>> p) {
        while (t.isInternal(p)) {
            Position<Entry<K, V>> smallerChild = smallestChild(p);
            if (smallerChild == null || compareEntries(smallerChild.element(), p.element()) >= 0) {
                break;
            }
            swap(p, smallerChild);
            p = smallerChild;
        }
    }

    private Position<Entry<K, V>> smallestChild(Position<Entry<K, V>> p) {
        Position<Entry<K, V>> smallerChild = null;
        if (t.hasLeft(p)) {
            smallerChild = t.left(p);
            if (t.hasRight(p) && compareEntries(t.right(p).element(), smallerChild.element()) < 0) {
                smallerChild = t.right(p);
            }
        }
        return smallerChild;
    }

    private int compareEntries(Entry<K, V> entry1, Entry<K, V> entry2) {
        return entry1.getKey().compareTo(entry2.getKey());
    }
    public String toString() {
		return t.toString();
	}
    @Override
    public Iterator<Entry<K, V>> iterator() {
        return t.iterator();
    }
}