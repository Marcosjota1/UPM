
public class Lapiz {
	
	enum Color { NEGRO, ROJO, AZUL } 
	
	Color color;
	
	boolean tienePunta;
	
	public Lapiz(Color color) {
		this.color = color;
		this.tienePunta = true;
	}
	
	/*
	 * Precondicion: lapiz no es null.
	 * Lanza una NullPointerException si el lapiz es null
	 */
	public boolean equals(Lapiz lapiz) {
		// Una solucion:
		// return lapiz != null && this.tienePunta == lapiz.tienePunta && this.color.equals(lapiz.color);
		return this.tienePunta == lapiz.tienePunta && this.color.equals(lapiz.color);
	}

	public boolean equals(Object obj) {
		if ( obj instanceof Lapiz ) {
			Lapiz lapiz = (Lapiz) obj;
			return this.tienePunta == lapiz.tienePunta && this.color.equals(lapiz.color);
		} else {
			throw new IllegalArgumentException();
			// Otra solucion:
			// return false;
		}
	}
	
	public static void main(String[] args) {
		try {
			Lapiz lapiz1 = new Lapiz(Lapiz.Color.NEGRO);
			Lapiz lapiz2 = new Lapiz(Lapiz.Color.NEGRO);
			Lapiz lapiz3 = new Lapiz(Lapiz.Color.AZUL);
			Lapiz lapiz4 = null;
			
			System.out.println("lapiz1 == lapiz2: " + (lapiz1 == lapiz2));
			System.out.println("lapiz1.equals(lapiz4): " + lapiz1.equals(lapiz4));
			System.out.println("lapiz1.equals(lapiz2): " + lapiz1.equals(lapiz2));
			System.out.println("lapiz1.equals(lapiz3): " + lapiz1.equals(lapiz3));
		} catch (RuntimeException e) {
			System.err.println("Ha ocurrido un error");
			System.err.println(e.getMessage());
			//e.printStackTrace();
		}
		System.out.println("El programa continua");
	}
}
