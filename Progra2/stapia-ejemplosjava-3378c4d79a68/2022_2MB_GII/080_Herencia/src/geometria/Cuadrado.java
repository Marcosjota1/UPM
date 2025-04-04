package geometria;

public class Cuadrado extends ParalelogramoRecto {
	public Cuadrado(double lado) {
		super(lado,lado);
	}
	
	@Override
	public String toString() {
		String comoParalelogramo = super.toString();
		return comoParalelogramo + "\n" + String.format("lado: %f", altura);
	}
}
