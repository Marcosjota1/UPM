
public class Principal {
	
	static double distToOrigin(Punto p) {
		return Math.sqrt(p.x*p.x + p.y*p.y);
	}
	
	public static void main(String[] args) {
		
		Punto p1 = new Punto(3.4, 7.7);
		Punto p2 = new Punto(5e-1, 1e-5);
		
		double dist1 = Punto.distToOrigin(p1);
		double dist2 = Principal.distToOrigin(p1);
		double dist3 = p1.distToOrigin();
		double dist4 = p2.distToOrigin();
		
		System.out.println(dist1);
		System.out.println(dist2);
		System.out.println(dist3);
		System.out.println(dist4);
		
		p1.moverEnDiagonal(100.0);
		double dist5 = p1.distToOrigin();
		System.out.println(dist5);
	}

}
