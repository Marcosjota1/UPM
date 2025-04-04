package mk4g;

public class GamusinoVerde extends Gamusino {
	
	private static final int VERDE_ELIMINA = 10;
	private static final int VERDE_MULTIPLICA = 2;
	
	@Override
	void visita(Hormiguero hormiguero) {
		hormiguero.eliminar(VERDE_ELIMINA);
		hormiguero.multiplicar(VERDE_MULTIPLICA);
	}
}
