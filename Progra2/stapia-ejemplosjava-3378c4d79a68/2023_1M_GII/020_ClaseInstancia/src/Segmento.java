
public class Segmento {
	
	final Punto p1;
	Punto p2;
	
	public Segmento(Punto ip1, Punto ip2) {
		p1 = ip1;
		p2 = ip2;
	}
	
	public void mover(double inc) {
		p1.mover(inc);
		p2.mover(inc);
	}
	
	public void moverA(double x, double y, double inc) {
		// p1 = new Punto(x, y); No puedo reasignar
		p1.mover(inc);
		p2 = new Punto(x,y);
	}
	
	public String toString() {
		return "Segmento: desde" + p1 + " a " + p2;
	}
}
