package geometria;

public class TestIgualdad {

	public static void main(String[] args) {
		
		int valores[] = { 1, 2, 3 }; 
		
		Punto2D[] puntos = {
			new Punto2D(3,4),
			new Punto2D(3,4),
			new Punto2D(9,3),
		};
		
		Punto2D p = puntos[1];
		
		for ( int i = 0; i < puntos.length; i++ ) {
			System.out.println("x: " + puntos[i].getX() + " y : " + puntos[i].getY());
		}
		
		System.out.println(puntos[0] == puntos[1]);
		System.out.println(puntos[1] == p);

		System.out.println(puntos[0].esIgual(puntos[1]));
		System.out.println(puntos[1].esIgual(p));
		
		// String cad1 = new String("Hola Mundo");
		String cad1 = "Hola Mundo";
		String cad2 = new String(cad1);
		String cad3 = cad1;
		
		System.out.println("con == sale: " + (cad1 == cad2));
		System.out.println("con equals sale: " + cad1.equals(cad2));
		System.out.println("con cad3 == cad1 sale: " + (cad3 == cad1));
	}

}
