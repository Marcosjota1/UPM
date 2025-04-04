package upm.aed.arboles;

import java.io.File;
import java.util.function.Predicate;

public class EsMultiploDe implements Predicate<Integer> {

	private int tamano;
	public EsMultiploDe (int tamano )
	{
		this.tamano = tamano;
	}
	@Override
	public boolean test(Integer t) {
		// TODO Auto-generated method stub
		return t % this.tamano  == 0;
	}

}
