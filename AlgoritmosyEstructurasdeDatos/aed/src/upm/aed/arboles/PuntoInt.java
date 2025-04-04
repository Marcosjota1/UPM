package upm.aed.arboles;

public class PuntoInt {
	private int x;

	public PuntoInt() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PuntoInt(int x) {
		super();
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public int multiplica (int y)
	{
		return y*getX();
	}
}
