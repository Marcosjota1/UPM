
public class Boligrafo {

	enum Color { NEGRO, ROJO, AZUL } 
	
	Color color;
	
	boolean tieneTinta;
	boolean recargable;
	
	public Boligrafo(Color color, boolean recargable) {
		this.color = color;
		this.recargable = recargable;
		this.tieneTinta = true;
	}
	
	public void escribirMucho() {
		tieneTinta = false;
	}
	
	/*
	 * Precondicion: el boligrafo tiene que ser recargable
	 * Lanza la Excepcion BoligrafoNoRegarble si no se puede recargar
	 */
	public void recargar() throws BoligrafoNoRecargable {
		if ( ! recargable ) {
			throw new BoligrafoNoRecargable();
		}
		tieneTinta = true;
	}
	
	public static void main(String[] args) {
		Boligrafo boli = new Boligrafo(Boligrafo.Color.AZUL, false);
		boli.escribirMucho();
		try {
			boli.recargar();
		} catch (BoligrafoNoRecargable e) {
			e.printStackTrace();
		}
		System.out.println("Programa continua");
		System.out.println("boli.tieneTinta: " + boli.tieneTinta);
	}
}
