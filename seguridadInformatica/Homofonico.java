import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;

public class Homofonico {
	public static void main(String[] args) {
		String palabra = args[0];
		String numerajos = args[1];
		String separador = args[2];
		ArrayList<Character> letras = new ArrayList<Character>();
		Hashtable<Character, ArrayList<Integer>> contenedor = new Hashtable<Character, ArrayList<Integer>>();
		char[] ordenada = palabra.replaceAll("\\s+","").toCharArray();
		String[] numerosSu = numerajos.replaceAll("\\s+","").split(separador);
		
 		//Arrays.sort(ordenada);
 		char lastChar = '?';
 		int index = 1;
 		for(char character : ordenada) {
 			char lower = Character.toLowerCase(character);
			if(contenedor.get(lower)==null) {
				letras.add(lower);
				ArrayList<Integer> nueva = new ArrayList<Integer>();
				nueva.add(index);
				contenedor.put(lower, nueva);
			}else {
				contenedor.get(lower).add(index);
			}
			index ++;
		}
 		Collections.sort(letras);
 		for(char character : letras) {
 			System.out.println(character + " -> "+contenedor.get(character).toString());
 			
 		}
 		System.out.println();
 		for(String numero : numerosSu) {
 			System.out.print(numero+"\t");
 		}
 		
 		System.out.println();
 		
 		for(String numero : numerosSu) {
 			if(Integer.parseInt(numero)-1<ordenada.length) {
 	 			Character letra = ordenada[Integer.parseInt(numero)-1];
 	 			System.out.print(letra+"\t");

 			}else {
 				System.out.print("\t");
 			}

 		}
	}
}
