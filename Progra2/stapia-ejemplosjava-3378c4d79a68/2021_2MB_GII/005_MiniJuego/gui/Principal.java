package gui;

import java.awt.event.*;
import javax.swing.*;
import personajes.Banda;

public class Principal implements Runnable, ActionListener {

	static final int TICK_INTERVAL = 500;

	JFrame frame = new JFrame("Mini Juego");
	Lienzo lienzo = new Lienzo();

	// Other
	Timer timer = new Timer(TICK_INTERVAL, null);
	Banda[] bandas = { 
			new Banda("Redondos", false),
			new Banda("Cuadrados", true)
	};

	@Override
	public void run() {
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Center
		frame.setLayout(null);
		lienzo.setBounds(10, 10, 590, 590);
		lienzo.setBandas(bandas);
		frame.add(lienzo);

		// Frame
		frame.setSize(620, 650);
		frame.setVisible(true);

		// Timer
		timer.addActionListener(this);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Timer
		bandas[0].hacerTurno(bandas[1]);
		bandas[1].hacerTurno(bandas[0]);
		lienzo.repaint();
	}

	public static void main(String[] args) {
		Principal principal = new Principal();
		SwingUtilities.invokeLater(principal);
	}

}
