package upm.aed.arboles;

import es.upm.aedlib.Position;
import es.upm.aedlib.tree.GeneralTree;
import es.upm.aedlib.tree.LinkedGeneralTree;
import es.upm.aedlib.tree.Tree;

public class ArbolLambda<E> {
	public static <E> int height(Tree<E> tree, Position<E> v) {
		if(tree.isExternal(v)) return 0;
		int h=0;
		for(Position<E> w : tree.children(v)) {
			h = Math.max(h, height(tree,w));
		}
		return h+1;
	}
	
	public static int sumNodes(Tree<Integer> tree) {
		return ArbolesGenerales.sumaElementos(tree);
	}
	
	interface OperacionArbol<E>{
		int height(Tree<E> tree, Position<E> v);
	}
	
	interface ArbolToInt<E>{
		int app(Tree<Integer> tree);
	}
	
	interface ArbolToE<E>{
		E app(Tree<E> tree);
	}

	
	interface ArbolToArbol<E>{
		Tree<E> app(Tree<E> tree);
	}
	
	interface IntegerMath{
		int operation(int a, int b);
	}
	
	public static <E>void main(String[] args) {
		GeneralTree<Integer> tree = new LinkedGeneralTree<>();
		tree.addRoot(19);

		Position<Integer> n2 = tree.addChildLast(tree.root(), 2);
		Position<Integer> n3 = tree.addChildLast(tree.root(), 3);
		Position<Integer> n10 = tree.addChildLast(tree.root(),10);
		Position<Integer> n11 = tree.insertSiblingBefore(n10, 11);
		Position<Integer> n12 = tree.insertSiblingAfter(n10, 12);
		
		Position<Integer> n4 = tree.addChildLast(n2, 4);
		Position<Integer> n6 = tree.addChildLast(n2, 6);
		Position<Integer> n5 = tree.insertSiblingBefore(n6, 5);

		Position<Integer> n7 = tree.addChildLast(n3, 7);
		Position<Integer> n8 = tree.insertSiblingAfter(n7, 8);

		Position<Integer> n9 = tree.addChildLast(n8, 9);
		Position<Integer> n13 = tree.addChildFirst(n9, 43);
		tree.set(n8, 18);
		OperacionArbol<Integer> OpTree = (treeObj, v) -> height(treeObj, v);
		ArbolToInt<Integer> sumaArbol = (treeObj) -> sumNodes(treeObj);
		ArbolToInt<Integer> sumaArbol2= (treeObj) -> ArbolesGenerales.sumaElementos2(treeObj);
		ArbolToE<Integer> root = (Tree<Integer> treeObj) -> (treeObj.root().element());
		ArbolToE<E> root2 = (Tree<E> treeObj) -> (treeObj.root().element());
		System.out.println(tree.toString());
	//	System.out.println(root2.app(tree));
		System.out.println(sumaArbol.app(tree));
	}
}
