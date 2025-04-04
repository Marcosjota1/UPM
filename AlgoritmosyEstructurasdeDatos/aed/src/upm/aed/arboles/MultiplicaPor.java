package upm.aed.arboles;

import java.util.function.Function;

public class MultiplicaPor implements Function<Integer,Integer>{
	private int n;
	
	public MultiplicaPor(int n) {
		this.n = n;
	}
	@Override
	public Integer apply(Integer t) {
		return t*n;
	}

}
