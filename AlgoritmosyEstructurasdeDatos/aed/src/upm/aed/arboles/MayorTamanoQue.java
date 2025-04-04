package upm.aed.arboles;

import java.io.File;
import java.util.function.Predicate;

public class MayorTamanoQue implements Predicate<File> {

	private int tamano;
	public MayorTamanoQue (int tamano )
	{
		this.tamano = tamano;
	}
	@Override
	public boolean test(File t) {
		// TODO Auto-generated method stub
		return t.length() < this.tamano;
	}

}
