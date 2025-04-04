
public class HaciendoMasCosas {
	
	public static void main(String[] args) {
		Punto p1 = new Punto(1,2);
		Punto p2 = new Punto(0.5, 0.5);
		Punto p3 = new Punto(50.5,50.5);
		Punto q = p1;
		
		System.out.println(q);
		q = p2;
		System.out.println(q);
		
		p2.moverEnDiagonal(100);
		System.out.println(q);
		
		q.moverEnDiagonal(-50.0);
		System.out.println(p2);
		System.out.println(p3);
		
		boolean iguales = p2 == p3;
		System.out.println(iguales);
		iguales = q == p2;
		System.out.println(iguales);
	}
}
