package core;

import java.util.logging.*;

import geometria.*;

public class Principal {

	public static final Logger LOGGER = Logger.getLogger("Geometria");

	public static void main(String[] args) {
		
		// Esta es la sentencia que faltaba... 
		Logger.getLogger("").getHandlers()[0].setLevel(Level.ALL);
		
		// Ahora cambiando el nivel se pueden poner o quitar mensajes
		LOGGER.setLevel(Level.OFF);

		LOGGER.info("Empezando el main");
		
		if ( Punto3D.DIMENSION >= 3 ) {
			LOGGER.warning("Dimension: " + Punto3D.DIMENSION);	
		}

		// Esto es un error:
		// Punto3D.DIMENSION = 4;

		double valor = 4.2;

		Punto3D p3d = new Punto3D();
		Punto3D otro = p3d;

		p3d.setX(valor);
		p3d.setY(valor + 1);
		p3d.setZ(valor - 100.9);

		System.out.println("(con toString) punto3d = " + p3d);

		otro.setX(222.2);

		System.out.println("punto3d = (" + p3d.getX() + ", " + p3d.getY() + ", " + p3d.getZ() + ")");

		otro.mover(50);

		System.out.println("punto3d = (" + p3d.getX() + ", " + p3d.getY() + ", " + p3d.getZ() + ")");

		double norma = otro.norm2();
		System.out.println("La norma es: " + norma);

		double dist = Punto3D.distanciaAlOrigen(p3d);
		System.out.println("La dist es: " + dist);
		LOGGER.info("Trabajo con Punto");
		
		Punto p = new Punto(55.5, 0.2);
		System.out.println("punto3d = (" + p.getX() + ", " + p.getY() + ")");
		
		p = new Punto("1.34e-4 200.4");
		System.out.println("punto3d = (" + p.getX() + ", " + p.getY() + ")");

		LOGGER.info("Empezando el main");
	}

}
