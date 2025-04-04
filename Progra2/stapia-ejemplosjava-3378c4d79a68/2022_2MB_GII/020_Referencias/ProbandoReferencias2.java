public class ProbandoReferencias2 {
	public static void main(String[] args) {
		EnteroBis[] array = new EnteroBis[3];
		for (int i = 0; i < array.length; ++i) {
			array[i] = new EnteroBis((i + 1) * 3);
		}

		int suma = 0;
		for (int i = 0; i < array.length; ++i) {
			// suma += = array[i].dato;
			suma += array[i].getEntero();
		}

		array[0] = new EnteroBis(10);
		array[1] = new EnteroBis(15);
		array[2] = null;
	}
}

class EnteroBis {

	public EnteroBis(int dt) {
		dato = dt;
	}

	public int getEntero() {
		return dato;
	}

	private int dato;
}