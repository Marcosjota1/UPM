import java.util.*;
import vector.VectorIterable;

public class Probando {
	
	public static void main(String[] args) {
		VectorIterable<Integer> v = new VectorIterable<>(3);
		for ( int i = 0; i < 3; ++i) {
			v.set(i, new Integer(i+1));
		}

		Iterator<Integer> ite = v.iterator();
		while ( ite.hasNext() ) {
			System.out.println(ite.next());
		}

		try {
			Iterator<Integer> iteMal = v.iterator();
			for ( int i = 0; i < 4; ++i) { 
				System.out.println(iteMal.next());
			}			
		} catch (Exception e) {
			System.out.println("Se ha lanzado una excepcion");
			System.out.println(e.getMessage());
		}
	}

}
