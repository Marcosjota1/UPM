package upm.aed.arboles;

import java.util.ArrayList;
import java.util.List;

public class BusquedaBinaria {
	public static void main(String[] args) {
		List<Integer> lista = new ArrayList<Integer>();
		lista.add(1);
		lista.add(2);
		lista.add(3);
		lista.add(4);
		lista.add(5);
		BusquedaBinariaProblema<Integer> problem = new BusquedaBinariaProblema<Integer>(lista,3);
		AbstractSolution<Boolean> sol = new BusquedaBinariaSolucion(problem);
		DACSchema<Boolean,Integer> esc = new DACSchema<Boolean,Integer>(problem,sol);
		System.out.println(esc.solve().value());
	}
}