package modificar;

import java.util.Arrays;

import list.*;
import queues.*;
import queues.exceptions.EmptyQueueException;

public class Modificar {
	
	public static IQueue<Punto> giraPuntos(IList<Punto> datos, double alpha) {
		IQueue<Punto> resultado = new SimpleQueue<>();
		for ( int i = 0; i < datos.size(); ++i ) {
			Punto p = datos.get(i);
			Punto q = new Punto(p);
			q.giro(alpha);
			resultado.add(q);
		}
		return resultado;
	}

	public static void main(String[] args) throws EmptyQueueException {
		Punto[] puntos = {
			new Punto(1,0),
			new Punto(0,1),
			new Punto(1,1)
		};
		
		IList<Punto> datos = new LinkedList<>();
		for ( int i = 0; i < puntos.length; ++i ) {
			datos.add(datos.size(), puntos[i]);
		}
		
		IQueue<Punto> resultado = giraPuntos(datos, Math.PI/3);
		
		System.out.println("Lista:");
		System.out.println(datos);
		
		System.out.println("Cola:");		
		while ( ! resultado.isEmpty() )  {
			System.out.println(resultado.poll());
		}
		
		System.out.println("Array:");
		System.out.println(Arrays.toString(puntos));
	}

}
