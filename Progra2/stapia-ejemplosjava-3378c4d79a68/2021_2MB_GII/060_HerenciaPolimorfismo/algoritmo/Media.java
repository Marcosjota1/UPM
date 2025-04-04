package algoritmo;

public class Media implements ICalcularSobreVector {

	@Override
	public double aplicar(double[] v) {
		double suma = 0;
		for ( int i = 0; i < v.length; ++i ) {
			suma += v[i];
		}
		return suma / v.length;
	}

}
