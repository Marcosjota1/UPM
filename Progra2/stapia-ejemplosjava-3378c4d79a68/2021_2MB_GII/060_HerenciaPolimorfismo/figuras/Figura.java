package figuras;

public abstract class Figura {
	
	private static int nextId = 0;
	private int id;
	
	Figura() {
		id = nextId;
		nextId = nextId + 1;
	}
	
	@Deprecated
	public int getId() {
		return id;
	}
	
	abstract double calcularArea();
	
	@Override
	public String toString() {
		return String.format("Id: %d", id);
	}
	
}
