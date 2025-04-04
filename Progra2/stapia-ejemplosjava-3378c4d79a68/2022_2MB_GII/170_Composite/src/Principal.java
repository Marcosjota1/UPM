
import dibujos.*;
import stdlib.StdDraw;

public class Principal {
	
	public static void main(String[] args) {
		StdDraw.setXscale(0, 10.0);
		StdDraw.setYscale(0, 10.0);
		StdDraw.setPenRadius(0.02);
		
		Circulo c1 = new Circulo(2, 3, 1.5);
		Linea s1 = new Linea(2,3, 4, 3);
		
		Composite cm1 = new Composite();
		cm1.add(c1);
		cm1.add(s1);
		//cm1.dibujar();
		
		Composite cm2 = new Composite();
		cm2.add(cm1.mover(3, 3));
		cm2.add(new Circulo(0,0, 1));
		cm2.dibujar();
		
		IDibujo cm3 = cm2.mover(2, 1);
		cm3.dibujar();
		if ( cm3 instanceof Composite ) {
			System.out.println("Si es composite");
		}
	}

}
