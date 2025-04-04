package datos;

public class Datos {
	
	double a; /* No se si se modifica, se puede modificar desde el mismos paquete */
	private double b; /* No se puede modificar fuera de la clase */
	private double c; /* Se puede modificar en cualquier sitio */
	
	public String toString() {
		return "(" + a + ", " + b + ", " + c + ")";
	}
	
	private void cambiarB() {
		b = b + a;
	}
	
	public void cambiarC(double nc) {
		if ( nc > 0 ) { // Nunca va a ser negativa
			c = nc;	
		}
	}
	
	public static void main(String[] args) {
		Datos d = new Datos();
		d.b = 4;
		d.cambiarB();
		System.out.println(d);
	}
}
