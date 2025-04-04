package gui;

import java.awt.*;
import javax.swing.*;
import personajes.Banda;

@SuppressWarnings("serial")
public class Lienzo extends JComponent {
	
	private transient Banda[] bandas;
	
	public void setBandas(Banda[] bandas) {
		this.bandas = bandas;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		int width = 600; 
		int height = 600; 
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		for (int i = 0; i < bandas.length; ++i) {
			bandas[i].dibujar(g);	
		}
	}

}
