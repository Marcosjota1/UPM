package geom;

public class Punto {
	private int x;
	private int y;
	
	public Punto(int x2, int y2) {
		x = x2;
		y = y2;
	}
	
	public void mover(int incX, int incY) {
		x += incX;
		y += incY;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int distanciaUno(Punto other) {
		return Math.abs(x - other.x) + Math.abs(y - other.y);
	}
}
