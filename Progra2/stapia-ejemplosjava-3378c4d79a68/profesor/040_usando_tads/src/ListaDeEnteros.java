import tads.*;

public class ListaDeEnteros {
	
	public static void main(String[] args) {
		
		// LinkedList<Integer> list = ... */
		IList<Integer> list = new LinkedList<>();
		
		System.out.println("Size: " + list.size());

		for ( int i = 0; i < 10; ++i ) {
			list.add(0, i*5);
		}
		System.out.println("Size: " + list.size());

		System.out.println(list);
	}

}
