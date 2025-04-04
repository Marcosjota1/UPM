package texto;

/*
 * Escribir el texto dado como IEscribirTexto 
 * poniendo antes una etiqueta <dato> y despues
 * una etiqueta </dato> */

public class DecoradorEtiqueta extends DecoradorTexto {
	
	String etiqueta;
	
	public DecoradorEtiqueta(IEscribirTexto texto, String etiqueta) {
		super(texto);
		this.etiqueta = etiqueta;
	}
	
	@Override
	public void escribir() {
		System.out.print("<" + etiqueta + ">");
		super.escribir();
		System.out.print("</" + etiqueta + ">");
	}
	
	public static void main(String[] args) {
		IEscribirTexto p = new Palabra("Hola");
		IEscribirTexto conEtiqueta = new DecoradorEtiqueta(p, "saludo");
		conEtiqueta.escribir();
	}
}
