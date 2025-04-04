package figuras;

public class Cuadrado extends Figura {
	
	double lado;
	
	public Cuadrado(double lado) {
		this.lado = lado;
	}

	@Override
	double calcularArea() {
		return lado*lado;
	}
	
	@Override
	public String toString() {
		String cadenaId = super.toString();
		return String.format("Cuadrado %s de lado %f ", cadenaId, lado);
	}

}
