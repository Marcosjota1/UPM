import hotel.*;

public class Main {

	public static void main(String[] args) {
		GestorReservas g = new GestorReservas();
		g.hacerReserva("Santiago");
		g.habitaciones[0].setCliente(new Cliente("Juan"));
	}

}
