package texto;

public class DecoradorTexto implements IEscribirTexto {
	
	private IEscribirTexto texto;
	
	public DecoradorTexto(IEscribirTexto texto) {
		this.texto = texto;
	}

	@Override
	public void escribir() {
		texto.escribir();
	}

}
