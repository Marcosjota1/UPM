package geometria;

public class Circulo {
	
	private Punto2D centro;
	private double radio;
	
	public Circulo(double cx, double cy, double radio) {
		this.centro = new Punto2D(cx, cy);
		this.setRadio(radio);
	}
	
	public double getRadio() {
		return radio;
	}

	public void setRadio(double radio) {
		this.radio = radio;
	}

	public void mover(double incX, double incY) {
		centro.mover(incX, incY);
	}
}
