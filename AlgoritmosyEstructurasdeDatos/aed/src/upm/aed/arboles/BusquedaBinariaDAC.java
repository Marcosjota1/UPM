package upm.aed.arboles;

import java.util.List;

public class BusquedaBinariaDAC<E> implements DAC<E,Boolean>
{

	private List<E> lista1;
	private E element;
	
	BusquedaBinariaDAC (List<E> lista)
	{
		this.lista1 = lista;
	}
	@Override
	public AbstractProblem<E> problem() {
		// TODO Auto-generated method stub
		return new BusquedaBinariaProblema(lista1,element);
	}

	@Override
	public AbstractSolution<Boolean> solution() {
		// TODO Auto-generated method stub
		return new BusquedaBinariaSolucion(problem());
	}
	

}
