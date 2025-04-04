package dibujar;

import javax.swing.JFrame;

import geometria.Punto;

public class Ventana {

	private JFrame frame = new JFrame();
	private Lienzo lienzo;

	private Punto[] puntos;

	public Ventana() {
		lienzo = new Lienzo();
		puntos = new Punto[5];
		for (int i = 0; i < puntos.length; ++i) {
			puntos[i] = new Punto(20 * i, 30 * i);
		}
		lienzo.setPuntos(puntos);
		lienzo.setBounds(10, 10, 590, 590);

		frame.add(lienzo);
		frame.setLayout(null);
		frame.setSize(620, 650);
		frame.setVisible(true);
	}
}
