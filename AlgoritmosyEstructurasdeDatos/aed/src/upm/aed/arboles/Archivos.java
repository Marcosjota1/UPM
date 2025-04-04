package upm.aed.arboles;


import java.io.File;
import java.io.IOException;
import java.util.function.Function;
import java.util.function.Predicate;

import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.tree.GeneralTree;
import es.upm.aedlib.tree.Tree;
import es.upm.aedlib.tree.LinkedGeneralTree;
 
/**
 * Utility class, where we will create methods for training in the use of files,
 * ultimately, Java I / O Basic. 
 * Clase de utilidades, donde crearemos m�todos
 * para el aprendizaje del manejo de ficheros, en definitiva, Java I/O B�sico.
 *
 * @author xulescode You can follow me on my website codigoxules.org/en
 * Puedes seguirme en mi web codigoxules.org).
 */
public class Archivos {
 
    /**
     * Explanation of the method by which we read the folder we pass as
     * parameter if exists, returning true in this case and false if not.
     * Explicaci�n del m�todo con el que leemos la carpeta que pasamos como
     * par�metro si existe, devolviendo true si existe y false si no existe.
     * <h3>Example (Ejemplo)</h3>
     * <pre>
     *      JavaIOUtils javaIOUtils = new JavaIOUtils();
     *      javaIOUtils.readFolderFiles("/home/xules/codigoxules");
     * </pre>
     *
     * @param folder <code>String</code> ruta donde vamos a crear el fichero.
     * @return <code>boolean</code> devolvemos true si la carpeta que se pasa
     * para leer .
     */
    
	public boolean readFolderFiles(String folder) {
        boolean resultado = false;
        File folderFile = new File(folder);
        GeneralTree<File> tree = new LinkedGeneralTree<File>();
        
        if(folderFile.exists()) {
            tree.addRoot(folderFile);
            resultado = true;
            readFolderFilesRec(tree, tree.root());     
        }
        System.out.println(tree);
        return resultado;
    }
	
	public static void readFolderFiles(
			GeneralTree<File> tree, String folder) {
        
		File folderFile = new File(folder);
        
        if(folderFile != null && folderFile.exists()) {
            tree.addRoot(folderFile);
            readFolderFilesRec(tree, tree.root());     
        }
	}
    
    private static void readFolderFilesRec(
    		GeneralTree<File> tree, 
    		Position<File> root) {
    	
		if(root.element().isFile())  // Es una hoja o external en el sentido de arbol
			return;
		for(File file: root.element().listFiles()) {
    		Position<File> nodo = tree.addChildLast(root, file);
    		readFolderFilesRec(tree, nodo);
		}
	}
    
    public static void writeFolderFiles(
			GeneralTree<File> tree, String folder) {

	}
    
    private static void writeFolderFilesRec(GeneralTree<File> tree, Position<File> root) {
		if(root.element().isFile())
			// copiar 
		
		// Crear el directorio 
		for(File file: root.element().listFiles()) {
    		Position<File> nodo = tree.addChildFirst(root, file);
    		writeFolderFilesRec(tree, nodo);
		}
	}

    private static void addPrefix (File f, String prefix)
    {
    	File f2 = new File (prefix + f.getName());
    	f.renameTo(f2);
    }
    
    public static void addPrefixOnlyFiles (String prefix, GeneralTree<File> tree)
    {
    	if (tree == null || tree.isEmpty())
    		return;
    	addPrefixOnlyFiles (prefix, tree, tree.root());
    }
    
    public static void addPrefixOnlyFiles (
    		String prefix, 
    		GeneralTree<File> tree,
    		Position<File> p)
    {
    	
    }
    
    
    public static void applyFunctionFiles (
    		Function<File,Integer> f, 
    		GeneralTree<File> tree,
    		GeneralTree<Integer> treeSize)
    {
    }
    
    
    public static void filterFiles (
    		Predicate<File> f, 
    		GeneralTree<File> tree,
    		GeneralTree<File> treeFiltered)
    {
    }
 
    
    public static boolean searchFile (String fileName, GeneralTree<File> tree)
    {
    	return true;
    }
    
    public static boolean searchFile (GeneralTree<File> tree, Predicate<File> p)
    {
    	return true;
    }
    
    public static void searchFile (
    		PositionList<File> result, 
    		GeneralTree<File> tree, 
    		Predicate<File> p)
    {
   
    }
    public static long countFilesMatching (Predicate<File> p, GeneralTree<String> tree)
    {
    	return 0;
    }
    
    
    /**
     * Main method for the tests for the created method, pruebas de <strong>Java
     * read folder</strong>. M�todo main para las pruebas para el m�todo creado,
     * pruebas de <strong>Java leer carpeta</strong>.
     *
     * @param args
     */
    public static void main(String args[]) {
        // First example (Primer ejemplo)
    	GeneralTree<File> tree = new LinkedGeneralTree<File>();
        readFolderFiles(tree, "/Users/julio/pruebagrafo");
        writeFolderFiles (tree, "/Users/julio/pruebagrafo2");
        System.out.println(tree);
        File f1 = new File("/Users/julio/pruebagrafo/inicio1.html");
        File f2 = new File("/Users/julio/pruebagrafo/inicio2.html");
        
        boolean b = f1. renameTo(f1);
//        
//        System.out.println(tree);
//        System.out.println(f1.getName());
    }
}