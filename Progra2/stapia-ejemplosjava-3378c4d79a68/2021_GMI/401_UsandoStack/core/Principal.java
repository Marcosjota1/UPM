package core;

import java.util.Random;

import stacks.Stack;
import stacks.exceptions.EmptyStackException;

public class Principal {

	public static void main(String[] args) throws EmptyStackException {
		System.out.println("Empezando");

		Stack<String> pila = new Stack<>();
		pila.push("Mundo");
		pila.push("Hola");
		
		System.out.println(pila.pop());
		System.out.println(pila.pop());

		Stack<Integer> pilaAleatorios = new Stack<>();
		Random random = new Random();
		
		for ( int i = 0; i < 200; ++i ) {
			int numAleatorio = random.nextInt(100); // 0 a 99
			System.out.print(String.format(" %02d", numAleatorio));
			pilaAleatorios.push(numAleatorio);
		}
		System.out.println();
		
		while ( ! pilaAleatorios.isEmpty() ) {
			System.out.print(String.format(" %02d", pilaAleatorios.pop()));
		}
		System.out.println();
		
		System.out.println("Terminando");
	}

}
