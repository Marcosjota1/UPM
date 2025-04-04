package geometria;

public class UsoNull {
	
	public static void main(String[] args) {
		Punto[] puntos = new Punto[2];
		
		puntos[0] = new Punto(10,20);
		puntos[1] = new Punto(1e-3,2.8e4);
		
		for ( int i = 0; i < 3/*puntos.length*/; ++i ) {
			System.out.println(puntos[i].getX());	
		}
		
		System.out.println("End Program");
	}
}
