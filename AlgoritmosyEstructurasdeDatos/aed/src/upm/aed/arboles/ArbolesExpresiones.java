package upm.aed.arboles;

import es.upm.aedlib.Position;
import es.upm.aedlib.tree.BinaryTree;
import es.upm.aedlib.tree.LinkedBinaryTree;

public class ArbolesExpresiones {
	
	public static void main(String[] args) {
		LinkedBinaryTree<Character> tree = new LinkedBinaryTree<Character>();
		tree.addRoot('&');
		Position<Character> nl = tree.insertLeft(tree.root(),'|');
		Position<Character> nr = tree.insertRight(tree.root(),'&');
		tree.insertLeft(nl,'T');
		tree.insertRight(nl,'F');
		tree.insertLeft(nr,'T');
		tree.insertRight(nr,'T');
		
		System.out.println(tree);
		System.out.println("La evaluación de la expresion ");
		imprimirExpresion(tree,tree.root());
		System.out.println(": " + eval(tree,tree.root()));

		System.out.println();
		System.out.println();
		
		LinkedBinaryTree<Character> treeArith = new LinkedBinaryTree<Character>();
		treeArith.addRoot('+');
		Position<Character> nl2 = treeArith.insertLeft(treeArith.root(),'+');
		Position<Character> nr2 = treeArith.insertRight(treeArith.root(),'*');
		treeArith.insertLeft(nl2,'2');
		treeArith.insertRight(nl2,'3');
		treeArith.insertLeft(nr2,'8');
		treeArith.insertRight(nr2,'5');

		System.out.println(treeArith);
		System.out.println("La evaluación de la expresion ");
		imprimirExpresion(treeArith,treeArith.root());
		System.out.println(": " + evalArithmetic(treeArith,treeArith.root()));

	}


	public static <E> void imprimirExpresion(BinaryTree<E> tree, Position<E> v) {
		if (!tree.isExternal(v)) System.out.print("(");
		
		if (tree.hasLeft(v))  imprimirExpresion(tree, tree.left(v));
		System.out.print(v.element());
		if (tree.hasRight(v)) imprimirExpresion(tree, tree.right(v));
		
		if (!tree.isExternal(v)) System.out.print(")");
	}

	public static boolean eval(BinaryTree<Character> expTree, Position<Character> v) {

		switch(v.element()) {
		case 'T': return true;
		case 'F': return false;
		case '|': 
			return  eval(expTree,expTree.left(v)) || eval(expTree,expTree.right(v));
		case '&': 
			return  eval(expTree,expTree.left(v)) && eval(expTree,expTree.right(v));
		default: 
			throw new IllegalArgumentException("El arbol no contiene los elementos correctos " + v.element());
		}
	}
	
	public static Integer evalArithmetic(BinaryTree<Character> expTree, Position<Character> v) {

		if (Character.isDigit(v.element())) {
			return Character.getNumericValue(v.element()); 
		}
		
		switch(v.element()) {
		case '+': 
			return  evalArithmetic(expTree,expTree.left(v)) + evalArithmetic(expTree,expTree.right(v));
		case '-': 
			return  evalArithmetic(expTree,expTree.left(v)) - evalArithmetic(expTree,expTree.right(v));
		case '*': 
			return  evalArithmetic(expTree,expTree.left(v)) * evalArithmetic(expTree,expTree.right(v));
		case '/': 
			return  evalArithmetic(expTree,expTree.left(v)) / evalArithmetic(expTree,expTree.right(v));
		default: 
			throw new IllegalArgumentException("El arbol no contiene los elementos correctos " + v.element());
		}
	}

}
