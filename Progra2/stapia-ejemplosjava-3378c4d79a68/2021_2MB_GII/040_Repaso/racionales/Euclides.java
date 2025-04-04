package racionales;

public class Euclides {
	// Pre: a y b != 0 en ese caso se devuelve 1
	public static int mcd(int a, int b) {
		if ( a == 0 || b == 0 ) {
			return 1;
		}
		
		while ( b != 0 ) {
			int resto = a % b;
			a = b;
			b = resto;
		}
		return a;
	}
	
	public static void main(String[] args) {
		System.out.println("MCD(48,18)=" + mcd(48,18));
		System.out.println("MCD(18,48)=" + mcd(18,48));
		System.out.println("MCD(48,24)=" + mcd(48,24));
		System.out.println("MCD(48,9)=" + mcd(48,9));
		System.out.println("MCD(48,11)=" + mcd(48,11));
	}

}
