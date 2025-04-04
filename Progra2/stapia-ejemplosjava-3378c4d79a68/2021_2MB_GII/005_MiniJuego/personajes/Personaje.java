package personajes;

import java.awt.Graphics;

import geom.Punto;

public abstract class Personaje {
	
	Punto loc;
	final String nombre; 
	int pvMaximos;
	int pv;
	int danho;
	
	protected Personaje(String nombre, int x, int y, int pvMaximos, int danho) {
		this.nombre = nombre;
		loc = new Punto(x, y);
		this.pvMaximos = pvMaximos;
		this.pv = pvMaximos;
		this.danho = danho;
	}
	
	public void ataca(Personaje personaje) {
		throw new UnsupportedOperationException();
	}
	
	public int distancia(Personaje other) {
		return loc.distanciaUno(other.loc);
	}
	
	public void mover(int incX, int incY) {
		loc.mover(incX, incY);
	}
	
	public String toString() {
		return String.format("Nombre: %s, PV: %d (%d)", nombre, pv, pvMaximos);
	}

	public abstract void dibujar(Graphics g, boolean ladoCampo);
}
