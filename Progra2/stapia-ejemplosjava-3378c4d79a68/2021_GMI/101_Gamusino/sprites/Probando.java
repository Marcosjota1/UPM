package sprites;

public class Probando {
	public static void main(String[] args) {
		Gamusino g1 = new Gamusino();
		Gamusino g2 = new Gamusino();
		
		for ( int i = 0; i < 100; ++i ) {
			g1.tick();
			g2.tick();
		}
	}
}
