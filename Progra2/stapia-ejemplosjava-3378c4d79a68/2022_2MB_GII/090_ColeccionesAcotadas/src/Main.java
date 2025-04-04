import colecciones.*;

public class Main {
	
	public static void main(String[] args) {
		
		PilaDePuntos pila = new PilaDePuntos();
		Punto p;
		
		for ( int i = 0; i < 5; ++i ) {
			p = new Punto();
			p.x = i;
			p.y = i*i;
			pila.poner(p);
		}
		
		System.out.println("Pila:");
		p = pila.arriba();
		System.out.println(p.x + " " + p.y);
		pila.quitar();
		p = pila.arriba();
		System.out.println(p.x + " " + p.y);
		

		System.out.println("Cola:");
		ColaDePuntos cola = new ColaDePuntos();
		
		for ( int i = 0; i < 20; ++i ) {
			p = new Punto();
			p.x = i;
			p.y = i*i;
			cola.poner(p);			
		}
		
		for ( int i = 0; i < 20; ++i ) {
			p = cola.quitar();
			System.out.println(p.x + " " + p.y);
		}		
	}

}
