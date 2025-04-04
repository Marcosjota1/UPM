package mk4g;

public class GamusinoIndigo  extends Gamusino {
	private int indigoMultiplica;
	private int indigoElimina;
	
	GamusinoIndigo(int indigoM, int indigoE) {
		indigoElimina = indigoE;
		indigoMultiplica = indigoM;
	}

	@Override
	void visita(Hormiguero hormiguero) {		
		hormiguero.multiplicar(indigoMultiplica);
		hormiguero.eliminar(indigoElimina);
	}

}
