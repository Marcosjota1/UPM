package geom;

public class Punto {
	
	private int x;
	private int y;
	
	public Punto(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void mover(int incX, int incY) {
		x = getX() + incX;
		y = getY() + incY;
	}
	
	public void setX(int x) {
		this.x = x;
	}	

	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}	

	public int getY() {
		return y;
	}

	public int distancia(Punto destino) {
		// Manhattan Distance
		return Math.abs(x - destino.x) + Math.abs(y - destino.y);
	}

}
