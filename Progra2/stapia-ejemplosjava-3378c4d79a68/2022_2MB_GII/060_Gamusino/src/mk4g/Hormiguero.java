package mk4g;

public class Hormiguero {
	
	private int numeroDeHormigas;
	
	public Hormiguero(int numeroInicial) {
		numeroDeHormigas = numeroInicial;
	}
	
	public void recibirVisita(FamiliaGamusinos familia) {
		Gamusino[] array = familia.get();
		for ( int i = 0; i < array.length; ++i ) {
			array[i].visita(this);
		}
	}
	
	void generar(int numero) {
		numeroDeHormigas += numero;
	}
	
	void eliminar(int numero) {
		numeroDeHormigas -= numero;
	}
	
	void multiplicar(int numero) {
		numeroDeHormigas *= numero;		
	}
	
	public String toString() {
		return "Hormiguero tiene " + numeroDeHormigas + " hormigas";
	}
}
