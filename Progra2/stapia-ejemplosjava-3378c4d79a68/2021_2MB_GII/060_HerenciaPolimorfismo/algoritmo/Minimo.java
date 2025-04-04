package algoritmo;

public class Minimo implements ICalcularSobreVector {

	@Override
	public double aplicar(double[] v) {
		double minimo = v[0];
		for ( int i = 1; i < v.length; ++i ) {
			if ( minimo > v[i] ) {
				minimo = v[i];
			}
		}
		return minimo;
	}

}
