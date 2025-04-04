package saliendo;

public class Yo {
	
	Llave llave;
	Cartera cartera;
	Movil movil; 
	
	public void llevar(Llave llave) {
		this.llave = llave;
	}

	public void llevar(Cartera cartera) {
		
	}

	public void llevar(Movil movil) {
		
	}
	
	public void salirDeCasa() {
		if ( llave != null && movil != null && cartera != null ) {
			System.out.println("Saliendo...");
		} else {
			System.out.println("No puedo salir");
		}
	}
}
