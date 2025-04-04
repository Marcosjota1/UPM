package mk4g;

public class GamusinoVioleta extends Gamusino {
	private int violetaGenera; 
	
	GamusinoVioleta(int violetaGeneracion) {
		violetaGenera = violetaGeneracion;
	}

	@Override
	void visita(Hormiguero hormiguero) {
		hormiguero.generar(violetaGenera);
	}
}
