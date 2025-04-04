package upm.aed.arboles;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Sublista<E> implements BiFunction<Integer, E,List<E>>
{
	
	private List<E> lista; 
	public Sublista (List<E> lista) {
		this.lista = lista; 
	}
		
	@Override
	public List<E> apply(Integer t, E u) {
		List<E> resultado = new ArrayList<E>();
		for(int i=0; i<t; i++) {
			resultado.add(lista.get(i));
		}
		return resultado;
	}

}