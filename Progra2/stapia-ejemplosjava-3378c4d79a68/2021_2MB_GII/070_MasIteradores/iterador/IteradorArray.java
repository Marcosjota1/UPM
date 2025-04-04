package iterador;

public class IteradorArray {
	
	private Object[] array;
	private int index;
	
	public IteradorArray(Object[] v) {
		this.index = 0;
		this.array = v;
	}
	
	public boolean hasNext() {
		return index < array.length;
	}
	
	public Object next() {
		index = index + 1; // index++
		return array[index-1];
	}
	
	public static void main(String[] args) {
		Double[] vector = { 3.4, 5.6, -4.5 };
		IteradorArray ite = new IteradorArray(vector);
		while ( ite.hasNext() ) {
			Object obj = ite.next();
			System.out.println(obj.toString());
		}
	}
}
