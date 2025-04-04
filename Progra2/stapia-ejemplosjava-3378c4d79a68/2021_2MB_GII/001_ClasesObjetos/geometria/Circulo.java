package geometria;

public class Circulo {
	
	private double radio = 10;
	private Punto centro;
	
	public Circulo(double x, double y, double radio) {
		setCentro(new Punto(x,y));
		this.setRadio(radio);
	}

	public double getRadio() {
		return radio;
	}

	public void setRadio(double radio) {
		this.radio = radio;
	}

	public Punto getCentro() {
		return centro;
	}

	public void setCentro(Punto centro) {
		this.centro = centro;
	}
}
