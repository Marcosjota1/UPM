package upm.aed.arboles;

import java.util.function.Function;

public class Sumatorio<E> implements Monoide<E> {

	private Function<E,E> op;
	private E neutro;
	
	Sumatorio (Function<E,E> op, E neutro)
	{
		this.op = op;
		this.neutro = neutro;
	}
	@Override
	public <E> E neutro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> E op(E arg1, E arg2) {
		// TODO Auto-generated method stub
		return this.op(arg1, arg2);
	}

}
