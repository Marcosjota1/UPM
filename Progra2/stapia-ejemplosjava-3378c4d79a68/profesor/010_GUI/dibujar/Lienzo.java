package dibujar;

import java.awt.*;
import javax.swing.*;

import geometria.Punto;

public class Lienzo extends JComponent {
	
	private static final long serialVersionUID = 7598875950160692824L;
	private static final int POINT_SIZE = 8; 
	private transient Punto[] puntos;
	
	public void setPuntos(Punto[] puntos) {
		this.puntos = puntos;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		int width = 600; // getWidth();
		int height = 600; // getHeight();
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		
		g.setColor(Color.white);
		for ( Punto p : puntos ) {
			int x = p.getX() - POINT_SIZE / 2;
			int y = p.getY() - POINT_SIZE / 2;
			g.fillOval(x, y, POINT_SIZE, POINT_SIZE);
		}
	}

}
