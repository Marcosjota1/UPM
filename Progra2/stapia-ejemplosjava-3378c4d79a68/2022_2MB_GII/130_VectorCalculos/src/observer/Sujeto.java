package observer;

import java.util.Arrays;

public class Sujeto {
	
	private double[] vector;
	
	private Nodo primero = null;
	
	public Sujeto (double[] vector) {
		this.vector = Arrays.copyOf(vector, vector.length);
	}
	
	public void set(int i, double x) {
		vector[i] = x;
		notificar();
	}
	
	public double get(int i) {
		return vector[i];
	}
	
	public int size() {
		return vector.length;
	}
	
	public void registrar(IObservador observer) {
		Nodo nodo = new Nodo();
		nodo.observador = observer;
		nodo.siguiente = primero;
		primero = nodo;
	}
	
	public void eliminar(IObservador observer) {
		if ( primero != null && primero.observador == observer ) {
			primero = primero.siguiente;
		} else {
			Nodo aux = primero;
			while ( aux != null && aux.siguiente != null && aux.siguiente.observador != observer ) {
				aux = aux.siguiente;
			}
			if ( aux != null && aux.siguiente != null ) {
				aux.siguiente = aux.siguiente.siguiente;
			}
		}
	}
	
	protected void notificar() {
		Nodo aux = primero;
		while ( aux != null ) {
			aux.observador.actualizar();
			aux = aux.siguiente;
		}
	}
	
	public String toString() {
		return Arrays.toString(vector); 
	}
}

class Nodo {
	IObservador observador;
	Nodo siguiente;
}
