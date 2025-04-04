package upm.aed.arboles;

import java.io.File;
import java.util.function.Function;

public class AlCuadradoR implements Function<Integer,Double> {

	@Override
	public Double apply(Integer t) {
		// TODO Auto-generated method stub
		return Math.pow(t,2);
	}

}
