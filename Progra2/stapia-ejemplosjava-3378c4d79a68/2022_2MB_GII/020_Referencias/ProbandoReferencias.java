public class ProbandoReferencias {
	public static void main(String[] args) {
		Entero e1 = new Entero(4);
		Entero e2 = e1;
		Entero e3 = new Entero(4);
		System.out.println(e1.getEntero());
		System.out.println(e2.getEntero());
		System.out.println(e3.getEntero());
	}
}

class Entero {

	public Entero(int dt) {
		dato = dt;
	}

	public int getEntero() {
		return dato;
	}

	int dato;
}