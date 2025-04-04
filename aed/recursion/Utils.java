package aed.recursion;
import java.util.Arrays;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.*;



public class Utils {

	public static int sqrtRecursivo(int n, int r) {
		if (r * r > n) { // Cuando te pases devuelves el anterior
			return r - 1;
		} else {
			return sqrtRecursivo(n, r + 1);
		}
	}

	public static int sqrt(int n) {
	
		return sqrtRecursivo(n, 1);
	}

	public static Iterable<Integer> primes(int n) {
	    PositionList<Integer> primes = new NodePositionList<>();
	    if (n < 2) return primes;
	    Boolean[] arr = new Boolean[n + 1];
	    Arrays.fill(arr, true);
	    primesRecursivo(2, n, primes, arr);
	    return primes;
	}

	private static void primesRecursivo(int current, int n, PositionList<Integer> primes, Boolean[] arr) {
	    if (current <= n) {
	        if (arr[current]) { // devuelve boolean 
	            primes.addLast(current);
	            int next = current + current;
	            marcarMultiplos(next, current, n, arr);
	        }
	        primesRecursivo(current + 1, n, primes, arr);
	    }
	}

	private static void marcarMultiplos(int next, int current, int n, Boolean[] arr) {
	    if (next <= n) {
	        arr[next] = false;
	        marcarMultiplos(next + current, current, n, arr);
	    }
	}  
  public static PositionList<Monomio> suma(PositionList<Monomio> p1, PositionList<Monomio> p2) {
	 PositionList<Monomio> resultado = new NodePositionList<>();
     sumaRecursiva(p1,p2,p1.first(), p2.first(), resultado);
     return resultado;
  }
  public static PositionList<Monomio> sumaRecursiva(PositionList<Monomio> p1, PositionList<Monomio> p2,Position<Monomio> cursor1, Position<Monomio> cursor2, PositionList<Monomio> resultado) {
	    if (cursor1 != null && cursor2 != null) {
	        Monomio monomio1 = cursor1.element();
	        Monomio monomio2 = cursor2.element();

	        int exponente1 = monomio1.getExponente();
	        int exponente2 = monomio2.getExponente();
	        int coeficiente1 = monomio1.getCoeficiente();
	        int coeficiente2 = monomio2.getCoeficiente();

	        if (exponente1 > exponente2) {
	            resultado.addLast(new Monomio(coeficiente1, exponente1));
	            sumaRecursiva(p1, p2, p1.next(cursor1), cursor2, resultado);
	        } else if (exponente1 < exponente2) {
	            resultado.addLast(new Monomio(coeficiente2, exponente2));
	            sumaRecursiva(p1,p2,cursor1, p2.next(cursor2), resultado);
	        } else {
	            int nuevoCoeficiente = coeficiente1 + coeficiente2;
	            if (nuevoCoeficiente != 0) {
	                resultado.addLast(new Monomio(nuevoCoeficiente, exponente1));
	            }
	            sumaRecursiva(p1,p2,p1.next(cursor1), p2.next(cursor2), resultado);
	        }
	    } else if (cursor1 != null) {
	        Monomio monomio1 = cursor1.element();
	        resultado.addLast(new Monomio(monomio1.getCoeficiente(), monomio1.getExponente()));
	        sumaRecursiva(p1,p2,p1.next(cursor1), null, resultado);
	    } else if (cursor2 != null) {
	        Monomio monomio2 = cursor2.element();
	        resultado.addLast(new Monomio(monomio2.getCoeficiente(), monomio2.getExponente()));
	        sumaRecursiva(p1,p2,null, p2.next(cursor2), resultado);
	    }
	    return resultado;
	}
}
