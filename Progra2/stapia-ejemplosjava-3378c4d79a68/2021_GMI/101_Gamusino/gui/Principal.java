package gui;

import java.awt.event.*;
import javax.swing.*;

import sprites.Gamusino;

public class Principal implements Runnable, ActionListener {

	static final int TICK_INTERVAL = 200;

	JFrame frame = new JFrame("Gamusino Game");
	Lienzo lienzo = new Lienzo();

	// Other
	Timer timer = new Timer(TICK_INTERVAL, null);
	Gamusino theGamusino = new Gamusino();

	@Override
	public void run() {
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Center
		frame.setLayout(null);
		lienzo.setBounds(10, 10, 590, 590);
		lienzo.setGamusino(theGamusino);
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
		theGamusino.tick();
		lienzo.repaint();
	}

	public static void main(String[] args) {
		Principal principal = new Principal();
		SwingUtilities.invokeLater(principal);
	}

}
