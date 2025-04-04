package hotel;

public class Cliente {
	private final String nombre;
	private double descuento;
	
	public Cliente(String nombre) {
		this.nombre = nombre;
	}
	
	/*
	void setNombre(String nombre) {
		this.nombre = nombre; No se puede porque es final
	}
	*/
	
	void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	
	public String toString() {
		return "Nombre: " + nombre + " Descuento: " + descuento; 
	}
}
