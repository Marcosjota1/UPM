package mk4g;

public class Gamusino {

	enum Color { VERDE, VIOLETA, INDIGO }
	
	private Color variedad;
	
	private static final int VERDE_ELIMINA = 10;
	private static final int VERDE_MULTIPLICA = 2;
	
	private int violetaGenera; 
	private int indigoMultiplica;
	private int indigoElimina;
	
	Gamusino() {
		variedad = Color.VERDE;
	}
	
	Gamusino(int violetaGeneracion) {
		variedad = Color.VIOLETA;
		violetaGenera = violetaGeneracion;
	}
	
	Gamusino(int indigoM, int indigoE) {
		variedad = Color.INDIGO;
		indigoElimina = indigoE;
		indigoMultiplica = indigoM;
	}
	
	void visita(Hormiguero hormiguero) {
		if ( variedad == Color.VERDE) {
			hormiguero.eliminar(VERDE_ELIMINA);
			hormiguero.multiplicar(VERDE_MULTIPLICA);
		} else if ( variedad == Color.VIOLETA) {
			hormiguero.generar(violetaGenera);
		} else {
			hormiguero.multiplicar(indigoMultiplica);
			hormiguero.eliminar(indigoElimina);
		}
	}
}
