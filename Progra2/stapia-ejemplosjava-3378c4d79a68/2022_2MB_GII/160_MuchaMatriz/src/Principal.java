import matriz.*;

public class Principal {

	public static void main(String[] args) {
		IMatriz a = new Matriz();
		IMatriz b = a.set(1, 0, (short)-222);
		System.out.println(a);
		System.out.println(b);
	}
}
