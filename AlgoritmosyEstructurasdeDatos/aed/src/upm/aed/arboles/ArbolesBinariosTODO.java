package upm.aed.arboles;

import es.upm.aedlib.Position;
import es.upm.aedlib.tree.BinaryTree;
import es.upm.aedlib.tree.LinkedBinaryTree;


public class ArbolesBinariosTODO {
	public static void main(String[] args) {
		BinaryTree<Integer> tree = new LinkedBinaryTree<Integer>();
		tree.addRoot(1);
		Position<Integer> left = tree.insertLeft(tree.root(), 2); 
		Position<Integer> n4 = tree.insertLeft(left,4);
		Position<Integer> right = tree.insertRight(tree.root(), 3); 
		Position<Integer> n5 = tree.insertRight(left,5);
		Position<Integer> n6 = tree.insertLeft(right,6);

		System.out.println(tree);

		//System.out.println("****" );
		// Recorrido pre-orden
//		preorder(tree,tree.root());

		//System.out.println("****" );
		// Recorrido Post-orden
//		postorder(tree,tree.root());

		//System.out.println("****" );
//		inorder(tree,tree.root());

		//System.out.println("****" );
		//preorderIterative(tree);

		//System.out.println("****" );
		//postorderIterative(tree);
		//System.out.println(lowestCommonAncestor(tree,tree.root(),n4,n5).element());
//		System.out.println(hojaMasIzquierda(tree).element());

	}

	public static <E> void preorder(BinaryTree<E> tree) {
		
		preorder(tree,tree.root());
	}

	public static <E> void preorder(BinaryTree<E> tree, 
									Position<E> pos) {
		if (tree.hasLeft(pos)) {
			preorder(tree,tree.left(pos));
		}
		System.out.println(pos.element());
		if (tree.hasRight(pos)) {
			preorder(tree,tree.right(pos));
		}
		
	}
	
	public static <E> void postorder(BinaryTree<E> tree, Position<E> v) {
	}

	public static <E> void inorder(BinaryTree<E> tree, Position<E> v) {

	}

	public static int sumaElementos(BinaryTree<Integer> tree, 
									Position<Integer> pos) {

		int suma = pos.element(); 
		if (tree.hasLeft(pos)) {
			suma += sumaElementos(tree,tree.left(pos));
		}
		if (tree.hasRight(pos)) {
			suma += sumaElementos(tree,tree.right(pos));
		}
		return suma; 
	}	
	
	public static <E> int height(BinaryTree<E> tree, 
								 Position<E> pos){
		if (tree.isExternal(pos)) {
			return 0;
		}
		int mi = 0; 
		int md = 0; 
		if (tree.hasLeft(pos)) {
			mi = height(tree,tree.left(pos));
		}
		if (tree.hasRight(pos)) {
			md = height(tree,tree.right(pos));
		}
		return Math.max(mi, md) + 1;
	}



	public <E> boolean member (BinaryTree<E> tree, 
							   E e, 
							   Position<E> n) {
		if (e.equals(n.element())) {
			return true;
		}
		boolean found = false;
		if (tree.hasLeft(n)) {
			found = member(tree,e, tree.left(n)); 
		}
		if (!found && tree.hasRight(n)) {
			found = member(tree,e, tree.right(n)); 
		}
		
		return found;
	}
		
	
	public static boolean eqNull (Object o1, Object o2) {
		return o1 == o2 || (o1!= null && o1.equals(o2));
	}
	
}











