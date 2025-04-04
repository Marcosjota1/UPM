package upm.aed.arboles;

import java.util.ArrayList;
import java.util.List;

public class DACSchema<S,P> {

	private AbstractProblem<P> theProblem;
	private AbstractSolution<S> theSolution;
	private DAC factoryDAC;
	
	DACSchema (AbstractProblem<P> prob, AbstractSolution<S> sol) {
		theSolution = sol;
		theProblem = prob;
	}
	DACSchema (DAC factoryDAC) {

		this.factoryDAC = factoryDAC;
		this.theProblem = factoryDAC.problem();
		this.theSolution = factoryDAC.solution();
	}
	
	public AbstractSolution<S> solve () {
		if (theProblem.isSmall())
			return theSolution.directSolution();
		else {
			List<AbstractProblem<P>>  subProblems = theProblem.divide ();
			List<AbstractSolution<S>> subSolutions = new ArrayList<AbstractSolution<S>>();
			for (AbstractProblem<P> p : subProblems) {
				AbstractSolution<S> s = theSolution.buildSolution(p);
			    subSolutions.add ((new DACSchema<S,P>(p,s)).solve());
			} 
			return theSolution.combine (subSolutions);
		} 
	} 
} 