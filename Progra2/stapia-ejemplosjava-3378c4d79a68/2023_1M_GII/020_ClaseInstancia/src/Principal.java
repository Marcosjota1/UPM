
public class Principal {
	public static void main(String[] args) { 
		Principal.saludo(); // De clase
		saludo(); // Está en la misma clase
		
		Punto a = new Punto(2,3);
		a.mover(2); // de instancia
		Punto b = new Punto(4, -1);
		Segmento s1 = Punto.crearSegmento(a,b); // De clase
		System.out.println(s1);
		b.mover(1);
		Segmento s2 = a.crearSegmento(b); // De instancia
		// a va ser this y b será p2
		System.out.println(s2);
		
		
	}
	
	public static void saludo() {
		System.out.println("Hola");
	}
}
