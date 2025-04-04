package facturas;

import tads.*;

public class ListaFacturasComposicion {
	
	private LinkedList<Factura> laLista = new LinkedList<>();

	private void add(int i, Factura factura) {
		laLista.add(i, factura);
	}

	public double sumaImporte() {
		double suma = 0;
		for ( int i = 0; i < laLista.size(); i++ ) {
			suma += laLista.get(i).getImporte();
		}
		return suma;
	}
	
	public static void main(String[] args) {
		ListaFacturasComposicion lista = new ListaFacturasComposicion();
		lista.add(0, new Factura(2020, 11, 3, 100.01, "SSD"));
		lista.add(0, new Factura(2021, 2, 20, 30.51, "Tarjeta de Red"));
		System.out.println(lista);
		System.out.print("Importe Total: ");
		double importeTotal = lista.sumaImporte();
		System.out.print(importeTotal);
	}
	
}
