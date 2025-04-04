package facturas;

import java.util.Arrays;

public class PonerEnOrden {
	
	public static void ordenarNumeros() {
		Integer[] numeros = { 3, -5 , 8, 1, 20, 19 };
		System.out.println(Arrays.toString(numeros));
		Arrays.sort(numeros);
		System.out.println(Arrays.toString(numeros));
	}
	
	public static void ordenarFacturas() {
		Factura[] facturas = {
			new Factura(2020, 11, 3, 100.01, "SSD"),
			new Factura(2021, 1, 3,  500.01, "Tarjeta gráfica"),
			new Factura(2021, 3, 5, 50.01, "Ordenador"),
			new Factura(2021, 2, 20, 30.51, "Tarjeta de Red"),
		};
		System.out.println("---Sin ordenar---");
		System.out.println(Arrays.toString(facturas));		
		Arrays.sort(facturas);
		System.out.println("--- Despues de ordenar por concepto ---");
		System.out.println(Arrays.toString(facturas));
		
		System.out.println("--- Despues de ordenar por fecha de emision ---");
		OrdenarPorFecha criterio = new OrdenarPorFecha();
		Arrays.sort(facturas, criterio);
		System.out.println(Arrays.toString(facturas));
	}

	public static void main(String[] args) {
		ordenarNumeros();
		ordenarFacturas();
	}

}
