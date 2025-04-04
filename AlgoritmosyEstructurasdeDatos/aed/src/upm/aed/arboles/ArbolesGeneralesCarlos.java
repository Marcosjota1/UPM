package upm.aed.arboles;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

import es.upm.aedlib.Position;
import es.upm.aedlib.fifo.FIFO;
import es.upm.aedlib.fifo.FIFOList;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.tree.GeneralTree;
import es.upm.aedlib.tree.LinkedGeneralTree;
import es.upm.aedlib.tree.Tree;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
public class ArbolesGeneralesCarlos {

//		public static void main(String[] args) {
//	
//	
//			GeneralTree<Integer> tree = new LinkedGeneralTree<>();
//			tree.addRoot(1);
//	
//			Position<Integer> n2 = tree.addChildLast(tree.root(), 2);
//			Position<Integer> n3 = tree.addChildLast(tree.root(), 3);
//			Position<Integer> n10 = tree.addChildLast(tree.root(),10);
//			Position<Integer> n11 = tree.insertSiblingBefore(n10, 11);
//			Position<Integer> n12 = tree.insertSiblingAfter(n10, 12);
//			
//			Position<Integer> n4 = tree.addChildLast(n2, 4);
//			Position<Integer> n6 = tree.addChildLast(n2, 6);
//			Position<Integer> n5 = tree.insertSiblingBefore(n6, 5);
//	
//			Position<Integer> n7 = tree.addChildLast(n3, 7);
//			Position<Integer> n8 = tree.insertSiblingAfter(n7, 8);
//	
//			Position<Integer> n9 = tree.addChildLast(n8, 9);
//			tree.set(n8, 18);
//			
//			System.out.println(tree);
//	//		imprimirTodosCaminos2(tree);
//	//		System.out.println(tree.size());
//			
//	//		System.out.println(sumaElementos(tree));
//			
//			System.out.println(esAncestro(tree, n8, n7)); 
//			System.out.println(esAncestroIter(tree, n8, n7));  
//			System.out.println(esDescendienteRec(tree, n8, n2));
//			System.out.println(esDescendienteRec(tree, n8, tree.root()));
//			System.out.println(esDescendienteRec(tree, n9, n3));
//			System.out.println(isSibling(tree, n6, n5));
//			System.out.println(isSibling(tree, n7, n8));
//			System.out.println(isSibling(tree, tree.root(), n7));
//	
//			System.out.println(leaves(tree));
//	//
//			System.out.println(depth(tree,n3));
//			System.out.println(depth(tree,tree.root()));
//			System.out.println(depth(tree,n9));
//			
//	
//			System.out.println("---");
//			System.out.println(height(tree,n3));
//	//		System.out.println("**** ");
//			System.out.println("--- Preorder");
//			printPreorder(tree);
//			System.out.println("--- Posorder");
//			printPostorder(tree);
//			System.out.println("---");
//			breadth(tree); 
//			
//	//		StringBuffer b = new StringBuffer("1234");
//	//		b.delete(1, 2);
//	//		System.out.println(b);
//			System.out.println("---");
//			imprimirTodosCaminos2(tree);
//			System.out.println("---");
//			System.out.println(getExternalChildren(tree));
//			
//		
//		}
//


	/* Es ancestro w de v*/
	public static <E> boolean esAncestro(Tree<E> tree, Position<E> w, Position<E> v) {
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

	

	public static <E> PositionList<E> leaves(Tree<E> t) {
		PositionList<E> res = new NodePositionList<E>();
		if (t.isEmpty()) {
			return res;
		}
		leaves(t,t.root(),res);
		return res; 
	}

	public static <E> void leaves(Tree<E> t, 
			Position<E> node, 
			PositionList<E> res) {

		if (t.isExternal(node)) {
			res.addLast(node.element());
		}

		for (Position<E> w: t.children(node)) {
			leaves(t,w,res);
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

	public static int sumaElementosRec(
			Tree<Integer> tree, Position<Integer> v) {
		int suma = v.element();  

		// Si es una hoja no tiene hijo y el for no se realiza
		for (Position<Integer> w : tree.children(v)) {
			suma += sumaElementosRec(tree,w); // supongamos que
											  // que esto funciona
		}
		return suma; 
	}
	
	public static int sumaElementosRec2(
			Tree<Integer> tree, Position<Integer> v) {
		
		if (tree.isExternal(v))
		    return v.element();
		
		int suma = 0;
		for (Position<Integer> w : tree.children(v)) {
			suma += sumaElementosRec2(tree,w); // supongamos que
											   // que esto funciona
		}
		return v.element() + suma; 
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
		path += v.element().toString();
		System.out.println(path+"-");
		for (Position<E> w : tree.children(v)) {
			imprimirTodosCaminos(tree, w, path); 
		}
	}

	/** IMPRIMIR TODOS LOS CAMINOS */
	public static <E> void imprimirTodosCaminos2(Tree<Integer> tree) {
		if (tree.isEmpty()) {
			return; 
		}

		imprimirTodosCaminos2(tree,tree.root(),new String()); 

	}

	public static <E> void imprimirTodosCaminos2(Tree<E> tree, Position<E> v, String path) { 
		path += v.element().toString()+"-";
		System.out.println(path);
		for (Position<E> w : tree.children(v)) {
			imprimirTodosCaminos2(tree, w, path); 
		}
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


	public static void copyFileDirectory 
	(GeneralTree<String> t, String filePath)
	{
		File folderFile = new File(filePath);
		t.addRoot(folderFile.getName());
		Position<String> cursor = t.root();
		if (folderFile.exists()) {
			copyToTreeR(t,folderFile,cursor);
		}	
	}

	public static void copyToTreeR(
			GeneralTree<String> tree,
			File folder,
			Position<String> cursor) {
		if(!cursor.equals(tree.root())) {
			Position<String> nuevoCursor = tree.addChildLast(cursor, folder.getName());
		}
		if(!folder.isDirectory()) {
			return;
		}
		File[] files = folder.listFiles();
		for (File file : files) {
			Position<String> nuevoCursor = tree.addChildLast(cursor, file.getName());
			copyToTreeR(tree, file,nuevoCursor);
		}
	}



	public static void readFolderFiles(
			GeneralTree<File> tree, String folder) {

		File folderFile = new File(folder);

		if(folderFile.exists()) {
			tree.addRoot(folderFile);
			readFolderFilesRec(tree, tree.root());     
		}
	}

	private static void readFolderFilesRec(
			GeneralTree<File> tree, Position<File> root) {
		if(root.element().isFile())
			return;
		for(File file: root.element().listFiles()) {
			Position<File> nodo = tree.addChildFirst(root, file);
			readFolderFilesRec(tree, nodo);
		}
	}

	private static void addPrefixOnlyFiles(String prefix,GeneralTree<File>tree) {
		addPrefixOnlyFilesR(prefix,tree,tree.root());
	}

	private static void addPrefixOnlyFilesR(String prefix,GeneralTree<File> tree, Position<File> cursor) {
		File elem = cursor.element();
		if(elem.isFile()) {
			String path = tree.parent(cursor).element().getAbsolutePath();
			File newName = new File(path + "\\"+prefix + elem.getName());
			elem.renameTo(newName);
			tree.set(cursor, newName);
		}else {
			for(Position<File> child : tree.children(cursor)) {
				addPrefixOnlyFilesR(prefix,tree,child);
			}
		}
	}

	public static long calculateSizeFiles (GeneralTree<File> tree)
	{
		return calculateSizeFilesR(tree,tree.root());
	}

	public static long calculateSizeFilesR (GeneralTree<File> tree,Position<File> cursor){
		if(cursor.element().isFile()) {
			return cursor.element().getTotalSpace();
		}
		long sum = 0;
		for(Position<File> child : tree.children(cursor)) {
			sum = sum + calculateSizeFilesR(tree,child);
		}
		return sum;
	}


	public static boolean searchFile (String fileName, GeneralTree<File> tree)
	{
		return searchFileR(fileName,tree,tree.root());
	}

	public static boolean searchFileR (String fileName, GeneralTree<File> tree,Position<File> cursor)
	{
		boolean found = false;
		File  elem = cursor.element();
		if(elem.isFile()) {
			return elem.getName().equals(fileName);
		}else {
			for(Position<File> child : tree.children(cursor)) {
				found = searchFileR(fileName,tree,child);
				if(found) {
					break;
				}
			}
		}
		return found;
	}
	
	
	public static <E> boolean searchTree (
			Tree<E> tree, 
			Predicate<E> p)
	{
		if (tree == null || tree.isEmpty())
			return false;
		return searchRec (tree, tree.root(), p);
	}
	public static <E> boolean searchRec (
			Tree<E> tree, 
			Position<E> v,
			Predicate<E> p)
	{
		boolean enc = p.test(v.element());
		Iterator<Position<E>> it = tree.children(v).iterator();
		while (it.hasNext() && !enc)
		{
			enc = searchRec(tree, it.next(),p);			
		}		
		return enc;

	}
	

	public static <E> PositionList<E> searchTree2 (
			Tree<E> tree, 
			Predicate<E> p)
	{
		PositionList<E> result = new NodePositionList<E>();
		if (tree == null || tree.isEmpty())
			return result;
		
		searchRec (tree, tree.root(), result, p);
		return result;
	}
	public static <E> void searchRec (
			Tree<E> tree, 
			Position<E> v,
			PositionList<E> result,
			Predicate<E> p)
	{
		if (p.test(v.element()))
				result.addLast(v.element());
		
		for (Position<E> child : tree.children(v))
			searchRec (tree, child, result, p);

	}

	public static <E> boolean allMatchTree (
			Tree<E> tree, 
			Predicate<E> p)
	{
		if (tree == null || tree.isEmpty())
			return true;
		return allMatchRec (tree, tree.root(), p);
	}
	
	
	public static <E> boolean allMatchRec (
			Tree<E> tree, 
			Position<E> v,
			Predicate<E> p)
	{
		boolean todos = p.test(v.element());
		Iterator<Position<E>> it = tree.children(v).iterator();
		while (it.hasNext() && todos)
		{
			todos = allMatchRec(tree, it.next(),p);			
		}		
		return todos;

	}
	
	
	public static <E> GeneralTree<E> mirror (GeneralTree<E> tree)
	{
		if (tree == null || tree.isEmpty())
			return tree;
		GeneralTree<E> result = new LinkedGeneralTree<E>();
		result.addRoot((E) tree.root());
		mirrorRec (tree, result, tree.root(), result.root());
		return result;
	}
	
	public static <E> void mirrorRec (
			GeneralTree<E> tree,
			GeneralTree<E> result, 
			Position<E> v,
			Position<E> w)
	{
		if (!tree.isExternal(v))
			for(Position<E> child : tree.children(v)) {
				mirrorRec(tree, result, child, 
						result.addChildFirst(w, child.element()));
			}
	}
	
	public static long countFilesMatching (String pattern, GeneralTree<String> tree)
	{
		return 0;
	}
	
	public static <E,F> GeneralTree<F> map (GeneralTree<E> tree, Function<E,F> f)
	{
		GeneralTree<F> result = new LinkedGeneralTree<F>();
		if (tree == null || tree.isEmpty())
			return result;
		result.addRoot(f.apply(tree.root().element()));
		
		mapRec (tree, result, tree.root(), result.root(), f);
		return result;
	}
	public static <E,F> void mapRec (
			GeneralTree<E> tree,
			GeneralTree<F> result, 
			Position<E> v,
			Position<F> w,
			Function<E,F> f)
	{
		if (!tree.isExternal(v))
			for(Position<E> child : tree.children(v)) {
				mapRec(tree, result, child, 
				result.addChildFirst(w, f.apply(child.element())),f);
			}
	}

	public static void main(String args[]) {
		//		// First example (Primer ejemplo)
		//		//		GeneralTree<String> tree = new LinkedGeneralTree<String>();
		//		//		copyFileDirectory(tree,"C:\\Users\\lilfo\\OneDrive\\Escritorio\\prueba");
		//		GeneralTree<File> tree = new LinkedGeneralTree<File>();
		GeneralTree<File> treeFiles = new LinkedGeneralTree<File>();
//		readFolderFiles(treeFiles, "/Users/julio/Documents/libros");
//		System.out.println(treeFiles);
//		System.out.println(searchFile(treeFiles,new IncluyeTexto("Contrato con Dios")));
//		System.out.println(searchFile2(treeFiles,new MayorTamanoQue(5000)));
	
		
		
		//		//System.out.println(tree);
		//		addPrefixOnlyFiles("X",tree);
		//		System.out.println(calculateSizeFiles(tree));
		MultiplicaPor fun = new MultiplicaPor(7);
//		ATexto fun2 = new ATexto(2);
		GeneralTree<Integer> tree = new LinkedGeneralTree<>();
		tree.addRoot(1);

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
		tree.set(n8, 18);
		System.out.println(tree);
		
		System.out.println(mirror (tree));
		Function<Integer,Integer> cuad = new AlCuadrado();
		Predicate<Integer> mayor = new MayorTamanoQueInteger(7);
		System.out.println(searchTree2 (map(tree,cuad), mayor));
//		OperacionArbol<Integer> OpTree = (Tree<Integer> tree, Position<Integer> v) -> height(tree, v);
//		System.out.println(map(tree,new RaizCuadrada()));
//		System.out.println(map(tree,new ConvierteTexto()));
		
//		System.out.println(duplicarPorN(tree,3));
//		System.out.println(duplicarPorN(tree,fun));
//		GeneralTree<Integer> Mtree = new LinkedGeneralTree<>();
//		duplicarPorN(tree,fun,Mtree);
//		System.out.println(duplicarPorN(tree,fun,Mtree));
//		GeneralTree<String> Mtree2 = new LinkedGeneralTree<String>();
//		System.out.println(duplicarPorN(tree,fun2,Mtree2));
	}


	public static <E> GeneralTree<E> copy (GeneralTree<E> tree)
	{
		return null;
	}

	public static <E,F> GeneralTree<F> map (GeneralTree<E> tree)
	{
		return null;
	}
	public static <E> Iterator<E> copy (IterableTree<E> tree)
	{
		return null;
	}

	public static <E extends Cloneable> GeneralTree<E> clone (GeneralTree<E> tree)
	{
		return null;
	}

	public static GeneralTree<Integer> duplicarPorN
	(GeneralTree<Integer> tree,int n)
	{

		GeneralTree<Integer> t = new LinkedGeneralTree<>();
		if (tree == null || tree.isEmpty())	
			return t;

		duplicarPorNRec (tree, tree.root(),n);
		return tree;
	}

	public static void duplicarPorNRec
	(GeneralTree<Integer> tree, 
			Position<Integer> p, int n)
	{
		tree.set(p, p.element()*n);
		if (!tree.isExternal(p))
		{
			for (Position<Integer> hijo : tree.children(p))
				duplicarPorNRec (tree,hijo,n);
		}	
	}

	public static GeneralTree<Integer> duplicarPorN
	(GeneralTree<Integer> tree,Function<Integer,Integer> fun)
	{

		GeneralTree<Integer> t = new LinkedGeneralTree<>();
		if (tree == null || tree.isEmpty())	
			return t;

		duplicarPorNRec (tree, tree.root(),fun);
		return tree;
	}

	public static void duplicarPorNRec
	(GeneralTree<Integer> tree, 
			Position<Integer> p,Function<Integer,Integer> fun)
	{
		tree.set(p, fun.apply(p.element()));
		if (!tree.isExternal(p))
		{
			for (Position<Integer> hijo : tree.children(p))
				duplicarPorNRec (tree,hijo,fun);
		}	
	}



	public static <E,F> GeneralTree<F> duplicarPorN (
			GeneralTree<E> tree,
			Function<E,F> fun,
			GeneralTree<F> result)
	{
		GeneralTree<F> t = new LinkedGeneralTree<F>();
		if (tree == null || tree.isEmpty())	
			return t;

		t.addRoot(fun.apply(tree.root().element()));
		duplicarPorNRec (tree, tree.root(),fun,t,t.root());
		return t;
	}

	public static <E,F> void duplicarPorNRec(
			GeneralTree<E> tree, 
			Position<E> p,
			Function<E,F> fun,
			GeneralTree<F> result,
			Position<F> presult)
	{
		if (tree.isExternal(p)){
			return;
		}
		else
		{
			for (Position<E> hijo : tree.children(p)) {
				Position<F> hijoresult = result.addChildLast(presult,fun.apply(hijo.element()));
				duplicarPorNRec (tree,hijo,fun,result,hijoresult);
			}
		}	
	}
}
