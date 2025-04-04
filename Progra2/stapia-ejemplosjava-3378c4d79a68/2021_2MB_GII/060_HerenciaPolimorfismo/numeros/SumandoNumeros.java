package numeros;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;

import tads.*;

public class SumandoNumeros {
	
	public static Object[] sumar(IList<Number> lista) {
		double sumaDouble = 0;
		int sumaInt = 0;
		
		for ( int i = 0; i < lista.size(); i++ ) {
			Number number = lista.get(i);
			
			if ( number instanceof Double ) {
				sumaDouble += (Double) number; 
			} else if ( number instanceof Integer ) {
				sumaInt += (Integer) number;	
			} else {
				System.out.println("Esto no lo he sumando: " + number);
			}
		}
		
		Object[] resultado =  { sumaDouble, sumaInt };
		return resultado;
	}
	
	public static void main(String[] args) {
		ArrayList<Number> lista = new ArrayList<>();
		Random aleatorios = new Random(56);
		
		for ( int i = 0; i < 20; i++ ) {
			double dado = aleatorios.nextDouble(); 
			if ( dado < 0.33 ) {
				lista.add(0, aleatorios.nextInt(200));
			} else if ( dado < 0.66 ){
				lista.add(0, 10.0 * aleatorios.nextDouble() - 5.0);
			} else {
				lista.add(0, new BigDecimal(3));
			}
		}
		
		System.out.println(lista);
		
		Object[] sumasTotales = sumar(lista);
		System.out.println(Arrays.toString(sumasTotales));
	}

}
