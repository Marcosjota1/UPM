package problema;

import java.util.Random;

import list.*;
import stacks.*;
import stacks.exceptions.EmptyStackException;

public class ClasificacionNumeros {
	
	//LinkedList<Integer>[] numeros;
	
	public static IList<IList<Integer>> clasificador(Stack<Integer> numeros) throws EmptyStackException {
		IList<IList<Integer>> resultado = new LinkedList<>();
		for ( int i = 0; i < 10; ++i ) {
			// IList<Integer> lista = new LinkedList<>();
			// resultado.add(size(), lista);
			resultado.add(i, new LinkedList<>());
		}
		
		while ( ! numeros.isEmpty() ) {
			Integer numero = numeros.pop();
			int unidades = numero % 10;
			resultado.get(unidades).add(0, numero);
		}
		
		return resultado;
	}

	public static void main(String[] args) throws EmptyStackException {
		Random random = new Random();
		Stack<Integer> stack = new Stack<>();
		
		for ( int i = 0; i < 30; ++i ) {
			stack.push(random.nextInt(100));
		}
		System.out.println("----- Stack -----");
		System.out.println(stack);
		
		IList<IList<Integer>> resultado = clasificador(stack);
		
		System.out.println("----- Lista de listas -----");
		System.out.println(resultado);
	}
}
