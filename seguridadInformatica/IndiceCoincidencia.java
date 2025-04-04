import java.util.Arrays;
import java.util.Hashtable;

public class IndiceCoincidencia {
	public static void main(String[] args) {
		String palabra = args[0];
		Hashtable<Character, Integer> contenedor = new Hashtable<Character, Integer>();
		char[] ordenada = palabra.replaceAll("\\s+","").toCharArray();
		System.out.println(palabra.replaceAll("\\s+",""));
		
 		Arrays.sort(ordenada);
 		char lastChar = '?';
 		for(char character : ordenada) {
 			if(character!=lastChar) {
 				System.out.print(character+"\t");
 			}
			if(contenedor.get(character)==null) {
				contenedor.put(character, 1);
			}else {
				contenedor.put(character, contenedor.get(character)+1);
			}
			lastChar = character;
		}
 		
 		System.out.println();
		 lastChar = '?';
		 double suma = 0;
  		for(char character : ordenada) {
 			if(character!=lastChar) {
 				System.out.print(contenedor.get(character)+"\t");
				suma += contenedor.get(character);
 			}
 			lastChar = character;
		}
  		System.out.print("f \t");
  		System.out.println();
  		lastChar = '?';
  		double sumaFrecuencias = 0;
 		for(char character : ordenada) {
 			if(character!=lastChar) {		
 				System.out.print(((contenedor.get(character)-1)*contenedor.get(character))+"\t");
 				sumaFrecuencias+=((contenedor.get(character)-1)*contenedor.get(character));
 			}
 			lastChar = character;
		}
 		System.out.print("f(f-1) \t");
 		System.out.println();
 		
 		System.out.println("Frecuencias totales: "+ (int)sumaFrecuencias);
 		System.out.println("Cracteres totales: "+(int)suma);

 		double sum = (suma*(suma-1));
 		double IC = (sumaFrecuencias/sum);
 		System.out.printf("IC = Sumatorio(f(f-1)) / n(n-1) = %d / %d = %.4f \n",(int)sumaFrecuencias,(int)sum,IC);
 		System.out.println("La secuencia tiene "+(int)suma+" caracteres y su IC es "+IC);		
 		if(IC < 0.05) {
 			if(suma < 30) {
 				System.out.println("La cantidad de caracteres es muy pequeña por lo tanto, la secuencia no será aleatoria");
 			}else {
 	 			System.out.println("Se trata de un IC bajo por lo tanto se puedn dar 3 casos");
 	 			System.out.println("\t 1- Se parece bastante a una secuencia aleatoria con distribución aleatoria y uniforme");
 	 			System.out.println("\t 2- Que el criptograma ha logrado esconder la estructura estadística del mensaje en cualquier idioma.");
 	 			System.out.println("\t 3- Que provenga de un cifrado polialfabético.");
 			}
 		}else if(IC < 0.06) {
 			System.out.println("lo que indica que el texto 1 2 o 3");
 			System.out.println("\t 1- Se parece bastante a una secuencia aleatoria con distribución uniforme");
 			System.out.println("\t 2- Es un criptograma correspondiente a la sustitución de uno o pocos alfabetos de un mensaje en lenguas eslavas");
 			System.out.println("\t 3- De ser un criptograma ha logrado ocultar toda la estructura estadística del mensaje en calro en cualquier otro idioma");
 		}else {
 			System.out.println(" es un IC alto, lo que indica que el texto está escrito en lenguaje natural mediante 1 o 2");
 			System.out.println("\t 1- Sustitución monoalfabética");
 			System.out.println("\t 2- Mediante transposiciones");
 			System.out.println("El cifrado no ha logrado ocultar toda la estructura del mensaje en claro original");
 		}
	}


}
