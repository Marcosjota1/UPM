package texto;

public class Palabra implements IEscribirTexto {
	
	private String palabra;
	
	public Palabra(String palabra) {
		this.palabra = palabra;
	}

	@Override
	public void escribir() {
		System.out.print(palabra);	
	}
	
	public static void main(String[] args) {
		Palabra p = new Palabra("Hola");
		p.escribir();
	}

}
