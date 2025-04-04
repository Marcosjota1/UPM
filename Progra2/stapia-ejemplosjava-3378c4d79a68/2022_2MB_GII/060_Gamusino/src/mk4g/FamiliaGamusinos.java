package mk4g;

import java.util.Random;

public class FamiliaGamusinos {
	
	private static final int MAX_GAMUSINOS = 6;
	
	private static final int porcentajeVioletas = 20;
	private static final int porcentajeVerdes = 30;
	
	private static Random random = new Random();
	
	private Gamusino[] gamusinos = new Gamusino[MAX_GAMUSINOS];
	
	public FamiliaGamusinos() {
		for ( int i = 0; i < MAX_GAMUSINOS; ++i ) {
			int aleatorio = random.nextInt(100) + 1;
			if ( aleatorio <= porcentajeVioletas ) {
				gamusinos[i] = new Gamusino(random.nextInt(20));
			} else if ( aleatorio <= porcentajeVioletas + porcentajeVerdes) {
				gamusinos[i] = new Gamusino();
			} else {
				gamusinos[i] = new Gamusino(random.nextInt(3)+1, random.nextInt(4));
			}
		}
	}
	
	Gamusino[] get() {
		return gamusinos;
	}
}
