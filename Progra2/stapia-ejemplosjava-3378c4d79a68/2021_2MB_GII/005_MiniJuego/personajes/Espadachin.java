package personajes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import core.MiLogger;

public class Espadachin extends Personaje implements RecibeDanho {

	private static final int ESCUDO = 5;

	public Espadachin(String nombre, int x, int y, int pvMaximos, int danho) {
		super(nombre, x, y, pvMaximos, danho);
	}

	@Override
	public void ataca(Personaje personaje) {
		if ( personaje instanceof RecibeDanho ) {
			// Casting (checked)
			RecibeDanho recibeDanho = (RecibeDanho) personaje;
			int distancia = this.distancia(personaje);
			if (distancia < Rango.calcular(TipoDeAtaque.CUERPO_A_CUERPO)) {
				recibeDanho.recibeDanho(danho, TipoDeAtaque.CUERPO_A_CUERPO);
			}			
		} else {
			MiLogger.get().severe("Ojo: el personaje no recibe daño");
		}
	}
	
	@Override
	public void dibujar(Graphics g, boolean ladoCampo) {
		Image img = gui.CargarIconos.getImage(gui.CargarIconos.IconNames.ROGUE, ladoCampo);
		g.drawImage(img, loc.getX(), loc.getY(), null);
	}

	@Override
	public void recibeDanho(int danho, TipoDeAtaque tipoDeAtaque) {
		if (danho > ESCUDO) {
			pv = pv - (danho - ESCUDO);
		}
	}

}
