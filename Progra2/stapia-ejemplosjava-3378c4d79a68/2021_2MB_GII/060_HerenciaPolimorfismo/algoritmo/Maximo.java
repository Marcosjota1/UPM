package algoritmo;

public class Maximo implements ICalcularSobreVector {

	@Override
	public double aplicar(double[] v) {
		double maximo = v[0];
		for ( int i = 1; i < v.length; ++i ) {
			if ( maximo < v[i] ) {
				maximo = v[i];
			}
		}
		return maximo;		
	}

}
