package colecciones;

public class PilaDePuntos {
	
	private Punto[] pila = new Punto[6];
	private int nivel = 0;
	
	public void poner(Punto p) {
		pila[nivel] = p;
		nivel += 1;
	}
	
	public void quitar() {
		pila[nivel-1] = null; /* No es necesario */
		nivel -= 1;
	}
	
	public Punto arriba() {
		return pila[nivel-1];
	}
}
