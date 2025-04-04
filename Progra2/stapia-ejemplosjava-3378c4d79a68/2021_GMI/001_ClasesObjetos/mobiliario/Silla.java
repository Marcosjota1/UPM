package mobiliario;

public class Silla {
	
	private int numeroPatas;
	private double peso;
	private String nombre = "silla";
	
	public Silla(int numeroPatas, double peso) {
		this.numeroPatas = numeroPatas;
		this.peso = peso;
	}
	
	@Override
	public String toString() {
		return nombre + "con patas: " + numeroPatas + ", peso = " + peso;
	}

	public static void main(String[] args) {
		
		Silla s1 = new Silla(3, 5.6);
		Silla s2 = new Silla(4, 35.6);
		Silla s3 = s1;
		
		System.out.println(s1.toString());
		System.out.println(s2);
		System.out.println(s3);

	}

}
