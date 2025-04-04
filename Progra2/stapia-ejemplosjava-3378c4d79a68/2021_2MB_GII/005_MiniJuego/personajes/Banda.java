package personajes;

import java.awt.Graphics;
import java.util.Random;

public class Banda {

	private static final int CAPACIDAD = 5;
	private static final Random generadorRand = new Random();
	private Personaje[] personajes = new Personaje[CAPACIDAD];
	private int numeroDePersonajes = 0; 
	private static final String[] nombresPersonaje = { "Juan", "Pepito", "Lucas", "Gregor", "Santiago" };

	String nombre;
	boolean ladoCampo;

	public Banda(String nombre, boolean ladoCampo) {
		this.nombre = nombre;
		this.ladoCampo = ladoCampo;
		for (int i = 0; i < CAPACIDAD; ++i) {
			String nombreP = nombresPersonaje[generadorRand.nextInt(nombresPersonaje.length)];
			// Vamos a pensar que tenemos 600x600 pixeles
			int x = ladoCampo ? 100 : 500;
			int y = 60 * i + generadorRand.nextInt(20) + (ladoCampo ? 100 : 30);
			int pv = 100;
			int danho = 40;
			if (i % 2 == 0) {
				personajes[i] = new Espadachin(nombreP, x, y, pv, danho);
			} else {
				personajes[i] = new Arquero(nombreP, x, y, pv, danho);
			}
		}
		numeroDePersonajes = 5;
	}

	public void hacerTurno(Banda enemigos) {
		mover(enemigos);
		atacar(enemigos);
		comprobar(enemigos);
	}

	private static void comprobar(Banda enemigos) {
		int personajesEliminados = 0;
		for ( int i = 0; i < enemigos.numeroDePersonajes; ++i ) {
			if ( enemigos.personajes[i].pv <= 0 ) {
				if ( i == enemigos.numeroDePersonajes - 1 - personajesEliminados) {
					enemigos.personajes[i] = null;	
				} else {
					enemigos.personajes[i] = enemigos.personajes[enemigos.numeroDePersonajes - 1 - personajesEliminados];
				}
				++personajesEliminados;
			}
		}
		enemigos.numeroDePersonajes -= personajesEliminados;
	}

	private void mover(Banda enemigos) {
		int xMasCercana = ladoCampo ? 600 : 0;
		for (int i = 0; i < enemigos.numeroDePersonajes; ++i) {
			if (ladoCampo) { // Esta banda está a la izquierda
				if (enemigos.personajes[i].loc.getX() < xMasCercana) {
					xMasCercana = enemigos.personajes[i].loc.getX();
				}
			} else { // Esta banda está a la derecha
				if (enemigos.personajes[i].loc.getX() > xMasCercana) {
					xMasCercana = enemigos.personajes[i].loc.getX();
				}
			}
		}

		if (ladoCampo) { // Esta banda está a la izquierda
			for (int i = 0; i < numeroDePersonajes; ++i) {
				if (this.personajes[i].loc.getX() < xMasCercana - 20) {
					this.personajes[i].mover(generadorRand.nextInt(20), generadorRand.nextInt(5));
				}
			}
		} else { // Esta banda está a la derecha
			for (int i = 0; i < numeroDePersonajes; ++i) {
				if (personajes[i].loc.getX() > xMasCercana + 20) {
					personajes[i].mover(-generadorRand.nextInt(20), generadorRand.nextInt(5));
				}
			}			
		}
	}

	private void atacar(Banda enemigos) {
		for (int i = 0; i < numeroDePersonajes; ++i) {
			Personaje masCerca = personajeMasCerca(personajes[i], enemigos);
			personajes[i].ataca(masCerca);
		}
	}

	private static Personaje personajeMasCerca(Personaje personaje, Banda enemigos) {
		Personaje masCerca = null;
		int minimaDistance = 100000;
		for ( int i = 0; i < enemigos.numeroDePersonajes; ++i ) {
			if ( personaje.distancia(enemigos.personajes[i]) < minimaDistance ) {
				minimaDistance = personaje.distancia(enemigos.personajes[i]);
				masCerca = enemigos.personajes[i];
			}
		}
		return masCerca;
	}

	public void dibujar(Graphics g) {
		for (int i = 0; i < numeroDePersonajes; ++i) {
			personajes[i].dibujar(g, ladoCampo);
		}
	}
}
