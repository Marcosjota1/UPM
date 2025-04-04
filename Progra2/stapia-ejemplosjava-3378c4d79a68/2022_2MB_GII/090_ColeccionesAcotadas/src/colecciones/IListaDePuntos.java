package colecciones;

public interface IListaDePuntos {
	
	public int numeroDeItems();
	
	public Punto obtener(int index);
	
	public void insertar(int index, Punto p);
	
	public void quitar(int index);

}
