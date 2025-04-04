package core;

import java.util.logging.Level;

import personajes.*;

public class Principal {

	public static void main(String[] args) {
		MiLogger.get().setLevel(Level.ALL);
		MiLogger.get().info("Empezando");	
		Espadachin cirano = null; // new Espadachin("Cirano", 13, 15, 100, 30);
		Espadachin sparrow = new Espadachin("Sparrow", 13, 15, 100, 30);
		Arquero robin = new Arquero("Robin Hood", 13, 14, 101, 31);
		Arquero guillermo = new Arquero("Guillermo Tell", 11, 11, 80, 20);
		
		MiLogger.get().info("El estado de robin es: " + robin.toString());
		cirano.ataca((Personaje)robin);
		MiLogger.get().info("El estado de robin es: " + robin.toString());
		cirano.ataca(sparrow);
		robin.ataca(cirano);
		robin.ataca(guillermo);
		robin.mover(10, 4);
		MiLogger.get().info("Final");
	}

}
