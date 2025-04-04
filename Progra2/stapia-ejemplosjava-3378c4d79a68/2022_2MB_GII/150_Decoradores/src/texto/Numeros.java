package texto;

public class Numeros implements IEscribirTexto {
	
	int a;
	int b;
	
	public Numeros(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public void escribir() {
		System.out.print(String.format("%d %d", a, b));
	}
}
