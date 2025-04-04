package upm.aed.arboles;

import java.util.function.Function;

public class Contiene implements Function<String,Boolean>{

	private String patron;
	
	Contiene (String patron) {
		this.patron = patron;
	}
	@Override
	public Boolean apply(String t) {
		return t.contains(patron);
	}

}
