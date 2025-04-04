
public class Punto {
	
	static String nombreClase = "Punto"; // De clase
	
	final double x;
	double y;
	
	public Punto(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	void mover(double inc) {
		// x += inc; No puedo asignar un nuevo valor
		// x = x + inc; porque es final.
		this.y += inc;
	}
	
	public Segmento crearSegmento(Punto p2) {
		return new Segmento(this, p2); 
	}
	
	public static Segmento crearSegmento(Punto p1, Punto p2) {
		// System.out.println(x); // No puedo x no existe, falta this
		// System.out.println(this.x); // No puedo x no existe, falta this
		System.out.println("Esto en la clase: " + nombreClase);
		return new Segmento(p1, p2);
	}
	
	public String toString() {
		return nombreClase + ": " + x + " " + y;
	}

}
