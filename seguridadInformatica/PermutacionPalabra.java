import java.util.Arrays;
import java.util.Hashtable;

public class PermutacionPalabra {

	public static void main(String[] args) {
		String palabra = args[0];
		int offset = 0;
		Hashtable<Character, Integer> contenedor = new Hashtable<Character, Integer>();
		char[] ordenada = palabra.toCharArray();
		
 		Arrays.sort(ordenada);
 		String filaArriba = "";
 		String filaAbajo = "";
 		int lololo = 0;
 		for(char character : ordenada) {
			System.out.print(character+"\t");
		}
 		System.out.println();
		for(char character : palabra.toCharArray()) {
			lololo++;
			System.out.print(lololo+"\t");
		}
		System.out.println();
		System.out.println();
		for(char character : palabra.toCharArray()) {
			Boolean encontrado = false;
			for(int x = 0; x < ordenada.length && !encontrado;x++) {
				if(ordenada[x]==character) {
					if(contenedor.get(character)==null) {
						contenedor.put(character, 0);
						encontrado = true; 
					}else {
						offset++;
						encontrado = true; 
					}
					filaArriba += character +"\t";
					filaAbajo += (x+offset+1) +"\t";
				}		

			}
		}
		
		System.out.println(filaArriba);
		System.out.println(filaAbajo);
	}
	
	public static int[] permutacionVal(String palabra) {
		int offset = 0;
		Hashtable<Character, Integer> contenedor = new Hashtable<Character, Integer>();
		char[] ordenada = palabra.toCharArray();
		
 		Arrays.sort(ordenada);
		int[] fila = new int[palabra.length()];
 		int lololo = 0;
		for(char character : palabra.toCharArray()) {
			Boolean encontrado = false;
			for(int x = 0; x < ordenada.length && !encontrado;x++) {
				if(ordenada[x]==character) {
					if(contenedor.get(character)==null) {
						contenedor.put(character, 0);
						encontrado = true; 
					}else {
						offset++;
						encontrado = true; 
					}
					fila[lololo] = (x+offset+1);
				}		

			}
			lololo++;
		}
		return fila;
	}

}
