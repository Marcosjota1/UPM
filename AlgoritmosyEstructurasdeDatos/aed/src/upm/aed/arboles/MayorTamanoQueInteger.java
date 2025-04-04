package upm.aed.arboles;

import java.io.File;
import java.util.function.Predicate;

public class MayorTamanoQueInteger implements Predicate<Integer> {

	private int tamano;
	public MayorTamanoQueInteger (int tamano )
	{
		this.tamano = tamano;
	}
	@Override
	public boolean test(Integer t) {
		// TODO Auto-generated method stub
		return t > this.tamano;
	}

}
