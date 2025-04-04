package geometria;

public class ParalelogramoRecto extends Object {
	
	double base;
	double altura;
	
	public ParalelogramoRecto(double base, double altura) {
		this.base = base;
		this.altura = altura;
	}
	
	public double area() {
		return base*altura;
	}
	
	public String toString() {
		return String.format("Base: %f, Altura: %f", base, altura);
	}
}
