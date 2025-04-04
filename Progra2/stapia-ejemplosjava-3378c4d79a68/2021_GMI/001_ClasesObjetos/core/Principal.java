package core;

import java.util.logging.*;

import geometria.*;

public class Principal {
	
	public static final Logger LOGGER = Logger.getLogger("Geometria"); 

	public static void main(String[] args) {

		// Esta es la sentencia que faltaba... 
		Logger.getLogger("").getHandlers()[0].setLevel(Level.ALL);
		
		// Ahora cambiando el nivel se pueden poner o quitar mensajes
		LOGGER.setLevel(Level.FINER);

		LOGGER.warning("Empieza el main");
		
		System.out.println("La dimension de los puntos es: " + Punto2D.DIMENSION);
		
		// Esto est� mal porque es final
		// Punto2D.DIMENSION = 4;
		
		Punto2D p1 = new Punto2D();
		Punto2D p2 = p1;
		
		double valor = 3.6;
		
		p1.setX(valor);
		p1.setY(7);
		
		System.out.println("x: " + p2.getX() + " y: " + p2.getY());
		
		p1.mover(1.2, 5);

		System.out.println("x: " + p2.getX() + " y: " + p2.getY());
		
		System.out.println("Norm2 = " + Punto2D.norm2(p1));
		System.out.println("Norm2 = " + p1.norm2());
		
		LOGGER.warning("Empiezo con Punto");
		
		Punto punto = new Punto(4.5, 99.9);
		System.out.println("x: " + punto.getX() + " y: " + punto.getY());
		
		punto = new Punto("111.4 888.9");
		System.out.println("x: " + punto.getX() + " y: " + punto.getY());
		
		LOGGER.warning("Termina el main");
	}

}
