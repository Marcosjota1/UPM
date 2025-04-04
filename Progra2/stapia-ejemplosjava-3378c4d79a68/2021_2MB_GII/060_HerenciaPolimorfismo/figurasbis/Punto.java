package figurasbis;

public class Punto {
	
	private double x;
	private double y;
	
	public Punto(double x, double y) {
		this.setX(x);
		this.setY(y);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( obj instanceof Punto ) {
			Punto other = (Punto)obj;
			// return this.x == other.x && y == other.y;
			return Math.abs(x - other.x) + Math.abs(y - other.y) < 1e-6;
		} else {
			return false;	
		}
	}
	
	@Override
	public String toString() {
		return String.format("(%.2f; %.2f)", x, y);
	}

}
