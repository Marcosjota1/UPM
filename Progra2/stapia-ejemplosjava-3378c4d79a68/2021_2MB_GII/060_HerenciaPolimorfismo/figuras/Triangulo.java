package figuras;

public class Triangulo extends Figura {
	double base;
	double altura;
	
	public Triangulo(double base, double altura) {
		this.base = base;
		this.altura = altura;
	}

	@Override
	double calcularArea() {
		return base*altura/2.0;
	}

	@Override
	public String toString() {
		String cadenaId = super.toString();
		return String.format("Triangulo %s de base %f y altura %f", cadenaId, base, altura);
	}
	
}
