package upm.aed.arboles;

import javax.lang.model.element.VariableElement;

import es.upm.aedlib.Position;
import es.upm.aedlib.tree.BinaryTree;
import es.upm.aedlib.tree.LinkedBinaryTree;

public class ArbolesExpresionesTODO {

	public static void main(String[] args) {
		LinkedBinaryTree<Character> tree = new LinkedBinaryTree<Character>();
		tree.addRoot('|');
		Position<Character> nl = tree.insertLeft(tree.root(),'|');
		Position<Character> nr = tree.insertRight(tree.root(),'&');
		tree.insertLeft(nl,'T');
		tree.insertRight(nl,'F');
		tree.insertLeft(nr,'T');
		tree.insertRight(nr,'F');

		System.out.println(tree);
		imprimirExpresion(tree, tree.root());

		//		System.out.println(tree);
		//		System.out.println("La evaluación de la expresion ");
		//		imprimirExpresion(tree,tree.root());
		//		System.out.println(": " + evalBoolean(tree,tree.root()));
		//
		//		System.out.println();
		//		System.out.println();
		//		
		//		LinkedBinaryTree<Character> treeArith = new LinkedBinaryTree<Character>();
		//		treeArith.addRoot('+');
		//		Position<Character> nl2 = treeArith.insertLeft(treeArith.root(),'+');
		//		Position<Character> nr2 = treeArith.insertRight(treeArith.root(),'*');
		//		treeArith.insertLeft(nl2,'2');
		//		treeArith.insertRight(nl2,'3');
		//		treeArith.insertLeft(nr2,'8');
		//		treeArith.insertRight(nr2,'5');
		//
		//		System.out.println(treeArith);
		//		System.out.println("La evaluación de la expresion ");
		//		imprimirExpresion(treeArith,treeArith.root());
		//		System.out.println(": " + evalArithmetic(treeArith,treeArith.root()));

	}


	public static <E> void imprimirExpresion(BinaryTree<E> tree, 
											 Position<E> v) {
		if (tree.isInternal(v)) System.out.print("(");
		if (tree.hasLeft(v)) {
			imprimirExpresion(tree, tree.left(v));
		}
		System.out.print(v.element());
		if (tree.hasRight(v)) {
			imprimirExpresion(tree, tree.right(v));
		}
		if (tree.isInternal(v)) System.out.print(")");
		
	}

	public static boolean evalBoolean(BinaryTree<Character> expTree, 
			Position<Character> v) {
		return true;
	}	

	public static Integer evalArithmetic(BinaryTree<Character> expTree, 
			Position<Character> v) {
		return 1;
	}

}




