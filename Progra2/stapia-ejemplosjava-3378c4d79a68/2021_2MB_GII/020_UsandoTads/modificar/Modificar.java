package modificar;

import tads.*;

public class Modificar {
	
	public static IQueue<Punto> giraPuntos(IList<Punto> datos, double alpha) {
		IQueue<Punto> resultado = new Queue<>();
		for ( int i = 0; i < datos.size(); ++i ) {
			Punto p = datos.get(i);
			Punto q = new Punto(p);
			q.giro(alpha);
			resultado.push(q);
		}
		return resultado;
	}

	public static void main(String[] args) {
		Punto[] puntos = {
			new Punto(0,1),
			new Punto(1,0),
			new Punto(2,2),
		};
		IList<Punto> lista = new ArrayList<>();
		
		for ( int i = 0; i < puntos.length; ++i ) {
			lista.add(0, puntos[i]);
		}
		
		System.out.println("---- La lista es ----");
		System.out.println(lista);
		
		double alpha = Math.PI / 3;
		IQueue<Punto> fila = giraPuntos(lista, alpha);
		
		System.out.println("---- La lista es ----");
		System.out.println(lista);

		System.out.println("---- La cola es ----");
		System.out.println(fila);
	}

}
