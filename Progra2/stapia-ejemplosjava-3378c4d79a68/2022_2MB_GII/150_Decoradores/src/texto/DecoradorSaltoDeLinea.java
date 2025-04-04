package texto;

public class DecoradorSaltoDeLinea extends DecoradorTexto {

	public DecoradorSaltoDeLinea(IEscribirTexto texto) {
		super(texto);
	}
	
	@Override
	public void escribir() {
		super.escribir();
		System.out.println();
	}
	
	public static void main(String[] args) {
		IEscribirTexto t1 = new Palabra("Hola");
		IEscribirTexto t2 = new DecoradorSaltoDeLinea(t1);
		IEscribirTexto t3 = new DecoradorEtiqueta(t1, "saludo");
		IEscribirTexto t4 = new DecoradorSaltoDeLinea(t3);
		t2.escribir();
		t4.escribir();
		t2.escribir();
		t3.escribir();
		t2.escribir();
	}
}
