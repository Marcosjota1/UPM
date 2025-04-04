package hotel;

public class Habitacion {
	
	private int id;
	Cliente ocupante;
	
	public Habitacion(int id) {
		this.id = id;
	}
	
	int getId() {
		return id;
	}

	int id() {
		return id;
	}

	public boolean estaLibre() {
		return ocupante == null;
	}

	public void setCliente(Cliente c) {
		if ( estaLibre() ) {
			ocupante = c;	
		} else {
			System.err.println("La habitación está ocupada");
		}
	}
	
	public void dejarLibre() {
		ocupante = null;
	}
}
