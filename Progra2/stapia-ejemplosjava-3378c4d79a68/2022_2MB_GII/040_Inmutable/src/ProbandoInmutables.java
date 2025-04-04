

public class ProbandoInmutables {
	
	String cadena1 = new String("Hola");
	final String cadena2 = "Adios";
	
	public String toString() {
		return "Cad1 : " + cadena1 + " Cad2: " + cadena2; 
	}
	
	public static void main(String[] args) {
		ProbandoInmutables pi = new ProbandoInmutables();
		System.out.println(pi);
		// pi.cadena1 = "Distinta";
		pi.cadena1 = new String("Distinta");
		// pi.cadena2 = "Otra cosa"; MAL, es final
		System.out.println(pi);
	}

}

class Inmutable {
	private int numero;
	public Inmutable(int numero) {
		this.numero = numero;
	}
	public int get() {
		return numero;
	}
}

class Mutable {
	private int numero;
	public Mutable(int numero) {
		this.numero = numero;
	}
	public int get() {
		return numero;
	}
	public void set(int numero) {
		this.numero = numero;
	}
}