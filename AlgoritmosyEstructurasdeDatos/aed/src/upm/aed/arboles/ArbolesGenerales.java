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

public class ArbolesGenerales {

	public static void main(String[] args) {


		GeneralTree<Integer> tree = new LinkedGeneralTree<>();
		tree.addRoot(1);

		Position<Integer> n2 = tree.addChildLast(tree.root(), 2);
		Position<Integer> n3 = tree.addChildFirst(tree.root(), 3);

		Position<Integer> n4 = tree.addChildLast(n2, 4);
		Position<Integer> n6 = tree.addChildLast(n2, 6);
		Position<Integer> n5 = tree.insertSiblingBefore(n6, 5);
		Position<Integer> n31 = tree.insertSiblingBefore(n2, 31);

		Position<Integer> n7 = tree.addChildLast(n3, 7);
		Position<Integer> n8 = tree.insertSiblingAfter(n7, 8);

		Position<Integer> n9 = tree.addChildLast(n8, 9);

		System.out.println(tree);
//		imprimirTodosCaminos2(tree);
//		System.out.println(tree.size());
//		
//		System.out.println(sumaElementos(tree));

//		System.out.println(esAncestroIter(tree, n3, n9));  
//		System.out.println(esDescendienteRec(tree, n8, n2));
//		System.out.println(isSibling(tree, n6, n5));
//
		System.out.println(leaves(tree));
		breadth (tree);
		imprimirTodosCaminos(tree);
//
//		System.out.println(depth(tree,n3));
//
//		System.out.println(height(tree,n3));
//		System.out.println("**** ");
//		
//		printPreorder(tree);
//		printPostorder(tree);
//		breadth(tree); 
//
		
//		StringBuffer b = new StringBuffer("1234");
//		b.delete(1, 2);
//		System.out.println(b);
//		imprimirTodosCaminos2(tree);
		
//		System.out.println(getExternalChildren(tree));
		
	
	}
	
	

	/* Es ancestro w de v*/
	public static <E> boolean esAncestro(Tree<E> tree, 
			Position<E> w, Position<E> v) {
		if (w == v) return true; 
		if (tree.isRoot(v)) return false; 
		return esAncestro (tree,w,tree.parent(v)); 
	}

	public static <E> boolean esAncestroIter(Tree<E> tree, Position<E> w, Position<E> v) {
		if (w == v) return true;
		Position<E> node = v;
		while (!tree.isRoot(node) && node!=w) {
			node = tree.parent(node);
		}
		return node == w;  
	}

	public static <E> boolean esAncestroIter2(Tree<E> tree, Position<E> w, Position<E> v) {
		if (w == v) return true;
		Position<E> node = v;
		boolean enc = false;
		while (!tree.isRoot(node) && !enc) {
			if (node!=w)
				node = tree.parent(node);
			else
				enc = true;
		}
		return enc;  
	}

	
	public static <E> boolean esDescendiente(Tree<E> tree, 
			Position<E> w, 
			Position<E> v) {
		return esAncestro(tree,v,w); 
	}

	public static <E> boolean esDescendienteRec(Tree<E> tree, Position<E> w, Position<E> v) {
		if (w == v) {
			return true; 
		}
		if (tree.isExternal(v)) {
			return false; 
		}

		Iterator<Position<E>> it = tree.children(v).iterator();
		boolean found = false; 
		while (it.hasNext() && !found) {
	
			found = esDescendienteRec(tree, w, it.next());
		}
		return found; 
	}

	/* ES HERMANO */
	public static <E> boolean isSibling (Tree<E> t, Position<E> w, Position<E> v) {
		if (w == v || t.isRoot(w) || t.isRoot(v))
			return false;
		else
			return t.parent(w) == t.parent(v);
	}

	public static void copyFileDirectory 
				(Tree<String> t, String filePath)
	{
		
	}
//	/** HOJAS DE UN ARBOL **/
//	public static <E> PositionList<E> leaves(Tree<E> t) {
//		PositionList<E> r = new NodePositionList<E>();
//		if (t.isEmpty()) return r;
//		Iterator<Position<E>> it = t.positions().iterator();
//		while (it.hasNext()) {
//			Position<E> v = it.next();
//			if (t.isExternal(v)) {
//				r.addLast(v.element());
//			}
//		}
//		return r;
//	}

	public static <E> PositionList<E> leaves(Tree<E> t) {
		PositionList<E> res = new NodePositionList<E>();
		if (t.isEmpty()) {
			return res;
		}
		leavesR (t,t.root(),res);
		return res; 
	}

	public static <E> void leavesR(Tree<E> t, 
								  Position<E> node, 
								  PositionList<E> res) {

		if (t.isExternal(node)) {
			res.addLast(node.element());
		}

		for (Position<E> hijo: t.children(node)) {
			leavesR(t,hijo,res);
		}
	}

	/** PROFUNDIDAD */
	public static <E> int depth(Tree<E> tree, Position<E> v) {
		int c;
		for (c = 0; !tree.isRoot(v); v = tree.parent(v)) {
			c++;
		}
		return c;
	}

	public static <E> int depthRec(Tree<E> tree, Position<E> v) {
		if (tree.isRoot(v)) return 0;
		else                return 1 + depthRec(tree,tree.parent(v));
	}


	/** PREORDEN */
	public static <E> void printPreorder(Tree<E> tree) {

		if (tree.isEmpty()) {
			return; 
		}
		printPreorder(tree,tree.root());
	}

	public static <E> void printPreorder(Tree<E> tree, Position<E> v) {

		System.out.println(v.element()); // the "visit" action

		for (Position<E> w : tree.children(v)) {
			printPreorder(tree,w);
		}
	}

	public static <E> void printPostorder(Tree<E> tree) {

		if (tree.isEmpty()) {
			return; 
		}
		printPostorder(tree,tree.root());
	}

	public static <E> void printPostorder(Tree<E> tree, Position<E> v) {

		for (Position<E> w : tree.children(v)) {
			printPostorder(tree,w);
		}
		System.out.println(v.element()); // the "visit" action
	}

	/** RECORRIDO EN ANCHURA */
	public static <E> void breadth(Tree<E> tree) {
		if (tree != null && !tree.isEmpty()) {
			FIFO<Position<E>> fifo = new FIFOList<Position<E>>();
			fifo.enqueue(tree.root());

			while (!fifo.isEmpty()) {
				Position<E> v = fifo.dequeue();

				System.out.println(v.element());

				for (Position<E> w : tree.children(v)) {
					fifo.enqueue(w);
				}
			}
		}
	}


	/** ALTURA */
	public static <E> int height(Tree<E> tree, Position<E> v){

		if (tree.isExternal(v)) return 0;

		int h = 0;
		for (Position<E> w : tree.children(v)) {
			h = Math.max(h, height(tree, w));
		}	
		return h + 1;
	}

	/** SUMA DE LOS ELEMENTOS DE UN ARBOL */
	public static int sumaElementos(Tree<Integer> tree) {
		if (tree.isEmpty()) {
			return 0; 
		}
			
		return sumaElementosRec(tree,tree.root());
	}	

	public static int sumaElementosRec(Tree<Integer> tree, Position<Integer> v) {
		int suma = v.element();  
		
		for (Position<Integer> w : tree.children(v)) {
			suma += sumaElementosRec(tree,w);
		}
		return suma; 
	}

	/** OTRA OPCION UN POCO RARA PARA LA SUMA*/
	public static int sumaElementos2(Tree<Integer> tree) {
		if (tree.isEmpty()) {
			return 0; 
		}
		
		return tree.root().element() + sumaElementos2(tree,tree.root());
	}

	public static int sumaElementos2(Tree<Integer> tree, Position<Integer> v) {
		int suma = 0; 
		for (Position<Integer> w : tree.children(v)) {
			suma += w.element() + sumaElementos2(tree,w);
		}
		return suma; 
	}


	/** IMPRIMIR TODOS LOS CAMINOS */
	public static <E> void imprimirTodosCaminos(Tree<Integer> tree) {
		if (tree.isEmpty()) {
			return; 
		}
		
		imprimirTodosCaminos(tree,tree.root(),""); 
		
	}
	
	public static <E> void imprimirTodosCaminos(Tree<E> tree, 
												Position<E> v, 
												String path) { 
		path += v.element().toString()+"-";
		System.out.println(path);
		for (Position<E> w : tree.children(v)) {
			imprimirTodosCaminos(tree, w, path); 
		}
	}
	
	/** IMPRIMIR TODOS LOS CAMINOS */
	public static <E> void imprimirTodosCaminos2(Tree<Integer> tree) {
		if (tree.isEmpty()) {
			return; 
		}
		
		imprimirTodosCaminos2(tree,tree.root(),new StringBuffer()); 
		
	}
	
	public static <E> void imprimirTodosCaminos2(Tree<E> tree, Position<E> v, StringBuffer path) { 
		path.append(v.element().toString());
		System.out.println(path);
		for (Position<E> w : tree.children(v)) {
			imprimirTodosCaminos2(tree, w, path); 
		}
		path.delete(path.length()-v.element().toString().length(), path.length());
	}
		

	public static <E> PositionList<E> getExternalChildren(Tree<E> t) {
		PositionList<E> res = new NodePositionList<E>();
		if (t.isEmpty()) {
			return res;
		}
		getExternalChildren(t,t.root(),res);
		return res; 
	}

	public static <E> void getExternalChildren(Tree<E> t, 
								  Position<E> node, 
								  PositionList<E> res) {

		boolean found = false;
		Iterator<Position<E>> it = t.children(node).iterator();
		while (it.hasNext() && !found) {
			found = t.isExternal(it.next());
		}
		
		if (found) {
			res.addLast(node.element());
		}

		for (Position<E> w: t.children(node)) {
			getExternalChildren(t,w,res);
		}
	}


	static <E> void imprimirCaminosHojas(Tree<E> tree) {
		imprimirCaminosHojas(tree,tree.root(),"");
	}
	
	static <E> void imprimirCaminosHojas(Tree<E> tree, Position<E> c, String path) {
		path+=c.element();
		if (tree.isExternal(c)) {
			System.out.println(path);
		}
		for(Position<E> h: tree.children(c)) {
			imprimirCaminosHojas(tree,h,path);
		}
	}	
	
	
	
	
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	




