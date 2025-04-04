package upm.aed.arboles;

import java.util.function.Predicate;

public class IncluyeTexto implements Predicate<String> {

	private String patron;
	public IncluyeTexto (String patron)
	{
		this.patron = patron;
	}
	@Override
	public boolean test(String t) {
		// TODO Auto-generated method stub
		return t.contains(patron);
	}

}
