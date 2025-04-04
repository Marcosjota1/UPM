package upm.aed.arboles;

import java.io.File;
import java.util.function.Function;

public class ConvierteTexto implements Function<Integer,String> 
{

	@Override
	public String apply(Integer t) {
		// TODO Auto-generated method stub
		return "TEXTO" + String.valueOf(t);
	}

}
