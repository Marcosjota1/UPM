package upm.aed.arboles;

import java.util.function.Function;

public class ATexto implements Function<Integer,String>{
	private String valor;
	
	public ATexto(String valor) {
		this.valor = valor;
	}
	@Override
	public String apply(Integer t) {
		if (t%2 == 0)
			return String.valueOf(valor + t);
		else
			return (t.toString());
	}

}
