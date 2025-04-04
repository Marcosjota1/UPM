
public class Principal {
	public static void main(String[] args) {
		double x = 3, y = 2;
		double result = x + y;
		System.out.println(result);
		/* p es una referencia */
		Punto p = new Punto(); /* Este es el objeto o instancia */
		p.x = 1;
		p.y = p.x + 3;
		String str = p.toString();
		System.out.println(str);
		
		result = x * y;
		System.out.println(result);
		
		p = new Punto(2,3);
		System.out.println(p.toString());
		
		p.moverConIncremento(10);
		System.out.println(p);
		
		Punto q = new Punto(111,111);
		System.out.println("q: " + q);
	}
}
