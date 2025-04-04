package upm.aed.arboles;

import java.util.Iterator;

import es.upm.aedlib.Position;
import es.upm.aedlib.fifo.FIFO;
import es.upm.aedlib.fifo.FIFOList;
import es.upm.aedlib.tree.Tree;

public class TreeIterator<E> implements Iterator<E> {

	private Tree<E> tree;
	private FIFO<Position<E>> fifo;
	
	TreeIterator (Tree<E> tree)
	{
		this.tree = tree;
		this.fifo = new FIFOList<Position<E>>();
		if (tree != null && !tree.isEmpty()) {
			this.fifo.enqueue(tree.root());
		}
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return !fifo.isEmpty();
	}

	@Override
	public E next() {
		// TODO Auto-generated method stub
		Position<E> v = fifo.dequeue();
		return v.element();
	}

}
