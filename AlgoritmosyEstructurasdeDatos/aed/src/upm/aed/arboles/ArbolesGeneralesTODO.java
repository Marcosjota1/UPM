package upm.aed.arboles;

import java.util.Iterator;

import es.upm.aedlib.Position;
import es.upm.aedlib.fifo.FIFO;
import es.upm.aedlib.fifo.FIFOList;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.tree.GeneralTree;
import es.upm.aedlib.tree.LinkedGeneralTree;
import es.upm.aedlib.tree.Tree;

public class ArbolesGeneralesTODO {

	public static void main(String[] args) {


		GeneralTree<Integer> tree = new LinkedGeneralTree<>();
		tree.addRoot(1);

		Position<Integer> n2 = tree.addChildLast(tree.root(), 2);
		Position<Integer> n3 = tree.addChildLast(tree.root(), 3);

		Position<Integer> n4 = tree.addChildLast(n2, 4);
		Position<Integer> n6 = tree.addChildLast(n2, 6);
		Position<Integer> n5 = tree.insertSiblingBefore(n6, 5);

		Position<Integer> n7 = tree.addChildLast(n3, 7);
		Position<Integer> n8 = tree.insertSiblingAfter(n7, 8);

		Position<Integer> n9 = tree.addChildLast(n8, 9);

		System.out.println(tree);
		imprimirTodosCaminos(tree);
		//		esAncestroRecInit(tree, n3, n9);
		//		System.out.println(sumaElementos(tree));
		//
		//		System.out.println(esAncestroIter(tree, n3, n9));  
		//		System.out.println(esDescendienteRec(tree, n9, n3));
		//		System.out.println(isSibling(tree, tree.root(), n5));
		//
		//		System.out.println("HOJAS: " + leaves(tree));
		//
		//		System.out.println(depth(tree,n3));
		//
		//System.out.println(height(tree));
		//		System.out.println("**** ");
		//		
		//		printPreorder(tree);
		//		printPostorder(tree);
		//		breadth(tree); 
		//
		//				imprimirTodosCaminos(tree);

	}

	public static <E> boolean isSibling (Tree<E> t, 
			Position<E> w, 
			Position<E> v) {
		return w!=v && t.parent(w) == t.parent(v);
	}


	public static <E> boolean esAncestroRec(Tree<E> tree, 
			Position<E> w, 
			Position<E> v) {
		return false;
	}


	public static <E> boolean esAncestroIter(Tree<E> tree, 
			Position<E> w, 
			Position<E> v) {
		if (w == v || tree.isRoot(w)) return true;

		while (!tree.isRoot(v) && v != w) {
			System.out.println("Nodo: " + v.element());
			v = tree.parent(v);
		}

		return v != null;
	}

	public static <E> boolean esDescendiente(Tree<E> tree, 
			Position<E> w, 
			Position<E> v) {
		return esAncestroIter(tree, v, w);
	}	

	public static <E> int depth(Tree<E> tree, Position<E> v) {
		if (tree.isRoot(v)) return 0; 
		return 1 + depth(tree, tree.parent(v));
	}


	public static <E> void printPreorder(Tree<E> tree) {
		if (tree == null || tree.isEmpty()) {
			return;
		}
		printPreorder(tree, tree.root());
	}

	public static <E> void printPreorder(Tree<E> tree, Position<E> nodo) {
		//		System.out.println("Empiezo: " + nodo.element());
		for (Position<E> hijo: tree.children(nodo)) {
			printPreorder(tree, hijo);
		}
		System.out.println("Termino: " + nodo.element());
	}

	public static <E> void printPostorder(Tree<E> tree) {

	}

	// RECORRIDO EN ANCHURA
	public static <E> void breadth(Tree<E> tree) {
	}

	public static int sumaElementosRec(Tree<Integer> tree) {
		if (tree == null || tree.isEmpty()) {
			return 0;
		}
		return sumaElementosRec(tree, tree.root()); 
	}

	private static int sumaElementosRec(Tree<Integer> tree, 
			Position<Integer> nodo) {
		int suma = nodo.element(); 
		for (Position<Integer> hijo: tree.children(nodo)) {
			suma += sumaElementosRec(tree, hijo);
		}
		return suma;
	}


	// Altura de un árbol
	public static <E> int height(Tree<E> tree){
		if (tree == null || tree.isEmpty()) {
			return 0;
		}
		return height(tree, tree.root()); 
	}

	private static <E> int height(Tree<E> tree, 
							  	  Position<E> nodo) {
		
		if (tree.isExternal(nodo)) {
			return 0;
		}
		int maximo = 0; 
		for (Position<E> hijo: tree.children(nodo)) {
			maximo = Math.max(maximo,height(tree, hijo));
		}
		return maximo + 1;
	}

	// Es descendiente 'nodo' de 'ancestro'?
	public static <E> boolean esDescendienteRec(Tree<E> tree, 
												Position<E> nodo, 
												Position<E> ancestro) {
		if (nodo == ancestro) {
			return true;
		}
		boolean encontrado = false;
		Iterator<Position<E>> it = tree.children(ancestro).iterator();
		while (it.hasNext() && !encontrado) {
			encontrado = esDescendienteRec(tree, nodo, it.next());
		}
	
		return encontrado;
		
	}


	public static <E> PositionList<E> leaves(Tree<E> t) {
		PositionList<E> res = new NodePositionList<>(); 
		 leaves(t,t.root(), res);
		 return res; 
	}

	public static <E> void leaves(Tree<E> t, 
			Position<E> n, 
			PositionList<E> lista) {
		if (t.isExternal(n)) {
			lista.addLast(n.element());
		}
		for (Position<E> hijo: t.children(n)) {
			leaves(t,hijo,lista);
		}
		
	}

	public static <E> void imprimirTodosCaminos(Tree<Integer> tree) {
		imprimirTodosCaminos(tree,tree.root(), new NodePositionList<>());
	}
	public static <E> void imprimirTodosCaminos(Tree<Integer> tree, 
												Position<Integer> n, 
												PositionList<Integer> camino) {
		
		camino.addLast(n.element());
		System.out.println(camino);
		for (Position<Integer> hijo: tree.children(n)) {
			imprimirTodosCaminos(tree,hijo,camino);
		}
		camino.remove(camino.last());
	}
}





















