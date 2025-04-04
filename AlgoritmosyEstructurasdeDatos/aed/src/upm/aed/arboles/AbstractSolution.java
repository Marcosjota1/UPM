package upm.aed.arboles;

import java.util.List;

public interface AbstractSolution<E> {
	
	public <P> AbstractSolution<E> buildSolution(AbstractProblem<P> problem);
	public AbstractSolution<E> directSolution();
	public AbstractSolution<E> combine (List<AbstractSolution<E>> subSols);
	public E value();
	public <P> AbstractProblem<P> problem();


}
