package upm.aed.arboles;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

import es.upm.aedlib.Position;
import es.upm.aedlib.lifo.LIFO;
import es.upm.aedlib.lifo.LIFOList;
import es.upm.aedlib.tree.BinaryTree;
import es.upm.aedlib.tree.LinkedBinaryTree;
import es.upm.aedlib.tree.Tree;


public class ArbolesBinariosJ {
	public static void main(String[] args) {
		BinaryTree<Integer> tree = new LinkedBinaryTree<Integer>();
		tree.addRoot(1);
		Position<Integer> left = tree.insertLeft(tree.root(), 2); 
		Position<Integer> right = tree.insertRight(tree.root(), 3); 
		Position<Integer> n4 = tree.insertLeft(left,4);
		Position<Integer> n5 = tree.insertRight(left,5);
		Position<Integer> n6 = tree.insertLeft(right,6);
		Position<Integer> n7 = tree.insertRight(right,7);
		Position<Integer> n8 = tree.insertLeft(n4,8);

		System.out.println(tree);
		//		System.out.println(checkHeapOrderPropery(tree));

		//		Iterator<Integer> it = tree.iterator();
		//		while(it.hasNext()) {
		//			System.out.println(it.next());
		//		}


		System.out.println("**** pre" );
		// Recorrido pre-orden
		preorder(tree);

		System.out.println("**** post" );
		// Recorrido Post-orden
		postorder(tree,tree.root());

		System.out.println("**** in" );
		inorder(tree,tree.root());

		//		//System.out.println("****" );
		//		//preorderIterative(tree);
		//
		//		//System.out.println("****" );
		//		//postorderIterative(tree);
		//		//System.out.println(lowestCommonAncestor(tree,tree.root(),n4,n5).element());
		Position<Integer> hojaIzq = hojaMasIzquierda(tree);
		System.out.println("**** Hoja mas a la izquierda" );
		System.out.println(hojaIzq.element());
		System.out.println("**** Buscar elementos" );
		System.out.println(buscarElemento(tree,18));
		Predicate<Integer> pred = new MayorTamanoQueInteger(9);
		System.out.println("**** Cumple predicado" );
		System.out.println(cumpleElemento(tree,pred));
		System.out.println(cumpleElemento(tree, new EsMultiploDe(14)));
		System.out.println("**** Espejo" );
		Tree<Integer> mirror = espejo (tree);
		System.out.println(tree);
		System.out.println(mirror);
		System.out.println("**** mapTreeRec" );
		Function<Integer,Integer> f = new MultiplicaPor(5);
		Function<Integer,String> g = new ATexto("Celia");
		Function<String,Boolean> k = new Contiene("Cel");
		Function<Integer,String> h = f.andThen(g);
		Function<Integer, Boolean> j = h.andThen (k);
		
		System.out.println(mapTree(espejo (tree),f));
		System.out.println(mapTree(espejo (tree),j));
		
		//

	}

	public <E> int height ( BinaryTree <E> tree , Position <E> v) {
		if ( tree . isExternal (v)) return 0;

		int hi = 0, hd = 0;

		if ( tree . hasLeft (v))
			hi = height (tree , tree.left (v));
		if ( tree . hasRight (v))
			hd = height (tree , tree.right(v));
		return 1 + Math.max(hi ,hd);
	}


	public static <E> void preorder(BinaryTree<E> tree) {
		if (tree == null && tree.isEmpty())
			return;
		preorderRec (tree, tree.root());
	}


	private static <E> void preorderRec (BinaryTree<E> tree, Position<E> v) {
		System.out.println(v.element());
		if (tree.hasLeft(v))  preorderRec(tree, tree.left(v));
		if (tree.hasRight(v)) preorderRec(tree, tree.right(v));
	}

	public static <E> void postorder(BinaryTree<E> tree, Position<E> v) {
		if (tree.hasLeft(v))  postorder(tree, tree.left(v));
		if (tree.hasRight(v)) postorder(tree, tree.right(v));
		System.out.println(v.element());
	}

	public static <E> void inorder(BinaryTree<E> tree, Position<E> v) {
		if (tree.hasLeft(v))  inorder(tree, tree.left(v));
		System.out.println(v.element());
		if (tree.hasRight(v)) inorder(tree, tree.right(v));
	}

	/** HOJA MAS A LA IZQUIERDA */
	public static <E> Position<E> hojaMasIzquierda(BinaryTree<E> tree) {
		if (tree == null || tree.isEmpty()) {
			return null;
		}
		return hojaMasIzquierda(tree,tree.root());
	}

	private static <E> Position<E> hojaMasIzquierda(BinaryTree<E> tree, Position<E> p) {
		if (tree.hasLeft(p))
			return hojaMasIzquierda(tree,tree.left(p));
		else if (tree.hasRight(p))
			return hojaMasIzquierda(tree,tree.right(p));
		else
			return p;
	}

	public static <E> boolean buscarElemento (BinaryTree<E> tree, E e) {
		if (tree==null && tree.isEmpty())
			return false;
		return buscarElemento(tree,tree.root(),e);
	}



	private static <E> boolean buscarElemento (
			BinaryTree<E> tree, Position <E> n, E e) {
		if (n.element().equals(e)) return true;
		else if (tree.isExternal(n)) return false;

		boolean found = false;

		if (tree.hasLeft(n)) {
			found = buscarElemento(tree, tree.left(n),e); 
		}
		// found == true lo he encontrado ! y ya no busco por la derecha !
		//       == false lo intento por la derecha
		if (!found && tree.hasRight(n)) {
			found = buscarElemento(tree, tree.right(n),e);
		}

		return found;
	}
	public static <E> boolean cumpleElemento (
			BinaryTree<E> tree, Predicate<E> p) {
		return cumpleElemento(tree,tree.root(),p);
	}



	private static <E> boolean cumpleElemento (
			BinaryTree<E> tree, Position <E> n, Predicate<E> p) {
		if (p.test(n.element())) return true;
		if (tree.isExternal(n)) return false;

		boolean found = false;

		if (tree.hasLeft(n)) {
			found = cumpleElemento(tree, tree.left(n),p); 
		}
		if (!found && tree.hasRight(n)) {
			found = cumpleElemento(tree, tree.right(n),p);
		}

		return found;
	}


	public static int sumaElementos(BinaryTree<Integer> tree) {
		if (tree==null && tree.isEmpty()) {
			return 0; 
		}

		return sumaElementos(tree,tree.root());
	}	

	public static int sumaElementos(BinaryTree<Integer> tree, Position<Integer> v) {
		int suma = v.element();  

		if (tree.hasLeft(v)) {
			suma += sumaElementos(tree,tree.left(v));
		}
		if (tree.hasRight(v)) {
			suma += sumaElementos(tree,tree.right(v));
		}

		return suma; 
	}
	public static <E> void preorderIterative(BinaryTree<E> tree) {
		if(tree == null && tree.isEmpty()) {
			return; 
		}

		LIFO<Position<E>> stack = new LIFOList<Position<E>>();
		stack.push(tree.root());

		while(!stack.isEmpty()){
			Position<E> n = stack.top();
			stack.pop(); 
			System.out.println(n.element());

			if(tree.hasRight(n)){
				stack.push(tree.right(n));
			}
			if(tree.hasLeft(n)){
				stack.push(tree.left(n));
			}

		}
	}

	public static <E> void postorderIterative(BinaryTree<E> tree) {
		if(tree.isEmpty()) {
			return; 
		}

		LIFO<Position<E>> stack = new LIFOList<Position<E>>();
		stack.push(tree.root());
		Position<E> prev = null; 

		while (!stack.isEmpty()) {
			Position<E> curr = stack.top();
			if (prev == null || 
					(tree.hasLeft(prev) && tree.left(prev) == curr) || 
					(tree.hasRight(prev) && tree.right(prev) == curr)) {

				if (tree.hasLeft(curr))
					stack.push(tree.left(curr));
				else if (tree.hasRight(curr))
					stack.push(tree.right(curr));

			} 
			else if (tree.hasLeft(curr) && tree.left(curr) == prev) {
				if (tree.hasRight(curr))
					stack.push(tree.right(curr));
			} 
			else {
				System.out.println(curr.element());
				stack.pop();
			}
			prev = curr;
		}
	}

	public static <E> Position<E> lowestCommonAncestor(BinaryTree<E> tree, 
			Position<E> a, 
			Position<E> b) {
		if(tree.isEmpty()) {
			return null;
		}
		return lowestCommonAncestor(tree, tree.root(), a, b); 
	}

	public static <E> Position<E> lowestCommonAncestor(BinaryTree<E> tree, 
			Position<E> cursor,
			Position<E> a, 
			Position<E> b) { 

		if(cursor == a || cursor == b ) { // CASO BASICO
			return cursor; 
		}

		// CASO GENERAL 
		Position<E> left = null; 
		if (tree.hasLeft(cursor)) // SALTO DE FE SUPONGAMOS QUE YA HEMOS
			// CALCULADO EL PROBLEMA PARA left
			left = lowestCommonAncestor(tree,tree.left(cursor),a,b); 
		Position<E> right = null; 
		if (tree.hasRight(cursor)) // SALTO DE FE SUPONGAMOS QUE YA HEMOS
			// CALCULADO EL PROBLEMA PARA right
			right = lowestCommonAncestor(tree,tree.right(cursor),a,b); 

		// If we get left and right not null , it is lca for a and b 
		if (left != null && right != null) 
			// si left y rigth son ancentros SU PADRE TAMBIÉN !!!
			return cursor; 
		if (left == null) // EL IZQUIERDO NO ES ANCESTRO
			return right; // EL DERECHO
		else 
			return left;   // EL IZQUIERDO
	}

	public static <E> BinaryTree<E> espejo (BinaryTree<E> t) {
		BinaryTree<E> res = new LinkedBinaryTree<>();
		if (t==null & t.isEmpty())
			return res;
		res.addRoot(t.root().element()); 
		espejoRec (t,res,t.root(),res.root());
		return res; 
	}

	public static <E> void espejoRec (
			BinaryTree<E> t, BinaryTree<E> res, 
			Position<E> p, Position<E> pres) {
		if (t.hasRight(p)) {
			res.insertLeft(pres, t.right(p).element());
			espejoRec (t,res,t.right(p),res.left(pres));
		}

		if (t.hasLeft(p)) {
			res.insertRight(pres, t.left(p).element());
			espejoRec (t,res,t.left(p),res.right(pres));
		}
//		System.out.println(res);
	}

	public static <E,F> BinaryTree<F> mapTree (
			BinaryTree<E> tree,
			Function<E,F> f)
	{
		BinaryTree<F> res = new LinkedBinaryTree<>();
		if (tree==null & tree.isEmpty())
			return res;
		res.addRoot(f.apply(tree.root().element()));
		mapTreeRec (tree, res, tree.root(),res.root(), f);
		return res;
	}
	
	public static <E,F>  void mapTreeRec (
			BinaryTree<E> t,
			BinaryTree<F> res,
			Position<E> v,
			Position<F> w,
			Function<E,F> f)
	{
		if (t.hasRight(v)) {
			res.insertRight(w, f.apply(t.right(v).element()));
			mapTreeRec (t,res,t.right(v),res.right(w),f);
		}

		if (t.hasLeft(v)) {
			res.insertLeft(w, f.apply(t.left(v).element()));
			mapTreeRec (t,res,t.left(v),res.left(w),f);
		}
	}
	
	
	public static boolean checkHeapOrderPropery (BinaryTree<Integer> tree) {
		return checkHeapOrderPropery(tree,tree.root()); 
	}

	public static boolean checkHeapOrderPropery (BinaryTree<Integer> tree, Position<Integer> node) {
		if (tree.parent(node) != null && node.element() < tree.parent(node).element()) {
			return false; 
		}

		boolean res = true; 
		if (tree.hasLeft(node)) {
			res = checkHeapOrderPropery (tree,tree.left(node));
		}
		if (res && tree.hasRight(node)) {
			res = checkHeapOrderPropery (tree,tree.right(node));
		}
		return res; 
	}

	public static boolean heap2 (Tree<Integer> tree, Position<Integer> node) {
		if (tree.parent(node) != null && node.element() < tree.parent(node).element()) {
			return false; 
		}

		Iterator<Position<Integer>> it = tree.children(node).iterator(); 
		boolean res = true; 
		while(it.hasNext() && res) {
			res = heap2(tree,it.next()); 
		}
		return res; 
	}

}
