package gui;

import java.awt.*;
import javax.swing.*;
import sprites.Gamusino;

public class Lienzo extends JComponent {
	
	private static final long serialVersionUID = 7598875950160692824L;
	private transient Gamusino gamusino;
	
	public void setGamusino(Gamusino gamusino) {
		this.gamusino = gamusino;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		int width = 600; // getWidth();
		int height = 600; // getHeight();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		gamusino.dibujar(g);
	}

}
