package colecciones;

public interface ILista<E> {
	
	public int numeroDeItems();
	
	public E obtener(int index);
	
	public void insertar(int index, E e);
	
	public void quitar(int index);

}
