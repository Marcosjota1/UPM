package observer;

public class Main {
	
	public static void main(String[] args) {
		double[] v = { 2.3, 5.6, -4.5, 12.2, -3.0 };
		Sujeto sujeto = new Sujeto(v);
		ObservadorMaximo max = new ObservadorMaximo(sujeto);
		System.out.println("Sujeto: " + sujeto);
		System.out.println("Observador: " + max);
		
		sujeto.set(3, 4.5);
		System.out.println("Sujeto: " + sujeto);
		System.out.println("Observador: " + max);
		
		v[2] = 16.0;
		System.out.println("Sujeto: " + sujeto);
		System.out.println("Observador: " + max);
	}
}
