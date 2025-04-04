package problema;

import queues.*;
import queues.exceptions.EmptyQueueException;

import java.util.Random;

import list.*;
import racionales.Racional;

public class List2Queue {
	
	IQueue<Racional> colaMenores1 = new SimpleQueue<>();
	IQueue<Racional> colaMayores1 = new SimpleQueue<>();
	
	public List2Queue(IList<Racional> origenDatos) {
		for ( int i = 0; i < origenDatos.size(); ++i ) {
			Racional elem = origenDatos.get(i);
			if ( elem.getNumerador() >= elem.getDenominador() ) {
				colaMayores1.add(elem);
			} else {
				colaMenores1.add(elem);
			}
		}
	}
	
	public String toString() {
		String c1 = colaMayores1.toString();
		String c2 = colaMenores1.toString();
		return "Mayores: \n" + c1 + "\n Menores: \n" + c2;
	}

	public static void main(String[] args) throws EmptyQueueException {
		Random random = new Random();
		IList<Racional> lista = new LinkedList<>(); 
		
		for ( int i = 0; i < 20; ++i ) {
			Racional fraccion = new Racional(random.nextInt(100), random.nextInt(100));
			lista.add(0, fraccion);
		}
		
		List2Queue queues = new List2Queue(lista);
		// Como Queue no tiene el toString hay que hacerlo desde cero:
		// System.out.println(queues);
		
		System.out.println(" ----- Mayores que 1 ----- ");
		while ( ! queues.colaMayores1.isEmpty() ) {
			System.out.println("        " + queues.colaMayores1.poll());
		}

		System.out.println(" ----- Menores que 1 ----- ");
		while ( ! queues.colaMenores1.isEmpty() ) {
			System.out.println("        " + queues.colaMenores1.poll());
		}
	}

}
