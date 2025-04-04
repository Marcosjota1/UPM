package texto;

public class DecoradorPar extends DecoradorTexto {
	
	IEscribirTexto segundo;

	public DecoradorPar(IEscribirTexto t1, IEscribirTexto t2) {
		super(t1);
		segundo = t2;
	}
	
	@Override
	public void escribir() {
		System.out.print("{ ");
		super.escribir();
		System.out.print(", ");
		segundo.escribir();
		System.out.print(" }");
	}
	
	public static void main(String[] args) {
		IEscribirTexto p = new DecoradorSaltoDeLinea(new Palabra("Numeros"));
		IEscribirTexto num = new DecoradorEtiqueta(new Numeros(7, -9), "enteros");
		IEscribirTexto par = new DecoradorPar(p, num);
		par.escribir();
	}
}

