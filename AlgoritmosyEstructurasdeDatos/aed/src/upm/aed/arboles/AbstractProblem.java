package upm.aed.arboles;

import java.util.List;

public interface AbstractProblem<E> {
	
	public boolean isSmall();
	public List<AbstractProblem<E>> divide();

}
