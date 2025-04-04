package upm.aed.arboles;

import java.util.ArrayList;
import java.util.List;

public class ConcreteProblemList<E> implements AbstractProblem<E> {

	private List<E> data; // <1,3,4,5,6,6,7>
	private int begin;  // 2
	private int end;    // 4
	
	
	public ConcreteProblemList(List<E> data) {
		super();
		this.data = data;
		this.begin = 1;
		this.end = data.size();
	}

	public ConcreteProblemList(List<E> data, int begin, int end) {
		super();
		this.data = data;
		this.begin = begin;
		this.end = end;
	}

	@Override
	public boolean isSmall() {
		// TODO Auto-generated method stub
		return begin == end || begin == end-1;
	}

	@Override
	public List<AbstractProblem<E>> divide() {
		// TODO Auto-generated method stub
		int medio = end-begin+1 / 2;
		AbstractProblem<E> left = new ConcreteProblemList (this.data,begin,medio);
		AbstractProblem<E> right = new ConcreteProblemList (this.data,medio+1, end);
		List<AbstractProblem<E>> subProb= new ArrayList<AbstractProblem<E>> ();
		subProb.add(left);
		subProb.add(right);
		return subProb;
	}

}
