package personajes;

import java.awt.*;
import java.util.Random;
import core.MiLogger;

public class Arquero extends Personaje implements RecibeDanho, Comparable<Arquero>  {
	
	static final Random rand = new Random(); 
	  	
	public Arquero(String nombre, int x, int y, int pvMaximos, int danho) {
		super(nombre, x, y, pvMaximos, danho);
		MiLogger.get().info("Creando un arquero");
	}	
	
	@Override
	public void ataca(Personaje personaje) {
		if ( ! (personaje instanceof RecibeDanho) ) {
			throw new IllegalArgumentException("El personaje no recibe daño");
		}
		
		int distancia = this.distancia(personaje);
		RecibeDanho recibe = (RecibeDanho) personaje;
		
		if ( distancia < Rango.calcular(TipoDeAtaque.CUERPO_A_CUERPO) ) {
			MiLogger.get().fine("Arquero ataca cuerpo a cuerpo");
			recibe.recibeDanho(danho / 2, TipoDeAtaque.A_DISTANCIA);
		} else if ( distancia < Rango.calcular(TipoDeAtaque.A_DISTANCIA)) {
			MiLogger.get().fine("Arquero ataca a distancia");
			recibe.recibeDanho(danho, TipoDeAtaque.A_DISTANCIA);			
		} else {
			MiLogger.get().fine("Arquero fuera de rango");
		}
	}
	
	@Override
	public void dibujar(Graphics g, boolean ladoCampo) {
		Image img = gui.CargarIconos.getImage(gui.CargarIconos.IconNames.ARCHER, ladoCampo);
		g.drawImage(img, loc.getX(), loc.getY(), null);
	}

	@Override
	public void recibeDanho(int danho, TipoDeAtaque tipoDeAtaque) {
		int unD20 = rand.nextInt(20) + 1; 
		MiLogger.get().finest("rand = " + unD20);
		if ( tipoDeAtaque == TipoDeAtaque.CUERPO_A_CUERPO && unD20 < 15) {
			this.pv = pv - 3 * danho / 2;
			MiLogger.get().warning("Me han hecho pupa (cc), me quedan " + pv + " pv");
		} else if ( tipoDeAtaque == TipoDeAtaque.A_DISTANCIA && unD20 < 18 ) {
			pv = pv - danho;
			MiLogger.get().warning("Me han hecho pupa (distancia), me quedan " + pv + " pv");
		}
	}

	@Override
	public int compareTo(Arquero o) {
		return this.nombre.compareToIgnoreCase(o.nombre);
	}
}
