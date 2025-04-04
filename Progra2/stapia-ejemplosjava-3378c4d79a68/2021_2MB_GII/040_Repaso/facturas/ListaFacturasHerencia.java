package facturas;

import java.util.Arrays;

import tads.*;

public class ListaFacturasHerencia extends LinkedList<Factura> {
	
	enum EstadoFactura {
		SIN_PAGAR,
		PAGADO
	}
	
	final static String[] descripcion = {
		"Importe facturas sin pagar",
		"Importe de las facturas pagadas"
	};
	
	public double sumaImporte() {
		double suma = 0;
		for ( int i = 0; i < size(); i++ ) {
			suma += get(i).getImporte();
		}
		return suma;
	}
	
	// Comprobar si funciona así:
	// 1) double sumarLosImportes(Double sinPagar)
	// 2) void sumarLosImportes(Double sinPagar, Double pagadas)
	// 3) double sumarLosImportes(double sinPagar)
	public double[] sumarLosImportes() {
		double[] suma = { 0.0, 0.0 }; // new double[2];
		// double[] suma = new double[EstadoFactura.values().length];
		for ( int i = 0; i < size(); i++ ) {
			Factura f = get(i);
			double importe = f.getImporte();
			if ( f.estaPagada() ) {
				suma[EstadoFactura.PAGADO.ordinal()] += importe;
			} else {
				suma[EstadoFactura.SIN_PAGAR.ordinal()] += importe;
			}
		}
		return suma;
	}

	public static void main(String[] args) {
		ListaFacturasHerencia lista = new ListaFacturasHerencia();
		lista.add(0, new Factura(2020, 11, 3, 100.01, "SSD"));
		lista.add(0, new Factura(2021, 2, 20, 30.51, "Tarjeta de Red"));
		Factura pagada = new Factura(2021, 1, 3,  500.01, "Tarjeta gráfica");
		pagada.marcarComoPagada();
		lista.add(0, pagada);
		System.out.println(lista);
		System.out.print("Importe Total: ");
		double importeTotal = lista.sumaImporte();
		System.out.println(importeTotal);
		
		double[] importes = lista.sumarLosImportes();
		System.out.print("Importe [ pagado, sin pagar]: ");
		System.out.println(Arrays.toString(importes));
		
		for ( int i = 0; i < importes.length; i++ ) {
			System.out.print(EstadoFactura.values()[i] + ": ");
			System.out.println(importes[i]);
		}

		for ( int i = 0; i < importes.length; i++ ) {
			System.out.print(ListaFacturasHerencia.descripcion[i] + " ");
			System.out.print(EstadoFactura.values()[i] + ": ");
			System.out.println(importes[i]);
		}
	}
}
