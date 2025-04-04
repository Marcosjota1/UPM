package upm.aed.arboles;

import java.util.List;

public class BusquedaBinariaSolucion<E> implements AbstractSolution<Boolean> {
	Boolean solution = false;
	AbstractProblem<E> problema;

	public BusquedaBinariaSolucion(AbstractProblem<E> problema) {
		super();
		solution = false;
		this.problema = problema;
	}

	public Boolean getSolution() {
		return solution;
	}

	@Override
	public Boolean value() {
		// TODO Auto-generated method stub
		return solution;
	}

	@Override
	public AbstractSolution<Boolean> combine(List<AbstractSolution<Boolean>> subSols) {
		// TODO Auto-generated method stub
		
		for(AbstractSolution<Boolean> s : subSols) {
				solution = solution || s.directSolution().value();
			}
		return this;
	}

	@Override
	public AbstractSolution<Boolean> directSolution() {
		BusquedaBinariaProblema<E> p = (BusquedaBinariaProblema<E>)problem();
		solution = p.getBeginElem().equals(p.getElem());  
		return this;
	}

	@Override
	public AbstractProblem<E> problem() {
		return this.problema;
	}


	@Override
	public <P> AbstractSolution<Boolean> buildSolution(AbstractProblem<P> problem) {
		// TODO Auto-generated method stub
		return new BusquedaBinariaSolucion (problem);
	}
}
	
