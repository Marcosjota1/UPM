package upm.aed.arboles;

import java.util.Iterator;

import es.upm.aedlib.Position;
import es.upm.aedlib.tree.GeneralTree;
import es.upm.aedlib.tree.LinkedGeneralTree;
import es.upm.aedlib.tree.NonEmptyTreeException;


public class IterableLinkedGeneralTree<E> 
		extends LinkedGeneralTree<E> implements IterableGeneralTree<E>
{
	private LinkedGeneralTree<E> tree;
	
	public Iterator<E> iterator()
	{
		return new TreeIterator(tree);
	}
	
}
