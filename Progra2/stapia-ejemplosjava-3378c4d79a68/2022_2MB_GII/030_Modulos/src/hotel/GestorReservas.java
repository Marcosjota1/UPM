package hotel;

public class GestorReservas {
	
	static final int MAX_CLIENTES = 120;
	static final int MAX_HABITACIONES = 20;
	
	Cliente[] clientes = new Cliente[MAX_CLIENTES];
	public Habitacion[] habitaciones = new Habitacion[MAX_HABITACIONES];
	
	public GestorReservas() {
		for ( int i = 0; i < habitaciones.length; ++i ) {
			Habitacion h = new Habitacion(i+1);
			habitaciones[i] = h;
		}
	}
	
	public void hacerReserva(String dato) {
		Cliente c = new Cliente(dato);
		boolean notFound = true;
		for (int i = 0; i < clientes.length && notFound; ++i) {
			if ( clientes[i] == null ) {
				clientes[i] = c;
				notFound = false;
			}
		}
		notFound = true;
		for ( int i = 0; i < habitaciones.length && notFound; ++i ) {
			if ( habitaciones[i].estaLibre() ) {
				habitaciones[i].setCliente(c);
				notFound = false;
			}
		}
	}
	
	public static void main(String[] args) {
		Cliente cl1 = new Cliente("Santiago");
		// cl1.nombre = "Santiago"; No se puede ver
		cl1.setDescuento(0.2);
		System.out.println(cl1);

		Habitacion h1 = new Habitacion(34);
		System.out.println(h1.getId());
		System.out.println(h1);
	}
}
