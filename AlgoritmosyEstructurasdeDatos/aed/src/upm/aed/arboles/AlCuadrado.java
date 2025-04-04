package upm.aed.arboles;

import java.io.File;
import java.util.function.Function;

public class AlCuadrado implements Function<Integer,Integer> {

	@Override
	public Integer apply(Integer t) {
		// TODO Auto-generated method stub
		return (int)Math.pow(t,2);
	}

}
