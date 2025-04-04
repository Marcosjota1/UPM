package facturas;

import java.time.LocalDate;

public class Factura implements Comparable<Factura> {

	private LocalDate fechaEmision;
	private LocalDate fechaPago = null;
	
	private double importe;
	
	private String concepto;
	
	public Factura(int year, int month, int dayOfMonth, double importe, String concepto) {
		this.fechaEmision = LocalDate.of(year, month, dayOfMonth);
		this.importe = importe;
		this.concepto = concepto;
	}	
	
	public Factura(LocalDate fechaEmision, double importe, String concepto) {
		this.fechaEmision = fechaEmision;
		this.importe = importe;
		this.concepto = concepto;
	}

	public LocalDate getFechaEmision() {
		return fechaEmision;
	}
	
	public double getImporte() {
		return importe;
	}

	@Override
	public int compareTo(Factura aquella) {
		return this.concepto.compareToIgnoreCase(aquella.concepto);
	}
		
	public boolean estaPagada() {
		return fechaPago != null;
	}
	
	public void marcarComoPagada() {
		if ( estaPagada() ) {
			throw new RuntimeException("La factura ya está pagada");
		}
		fechaPago = LocalDate.now();
	}
	
	public String toString() {
		String c1 = String.format("Concepto: %s%nImporte: %.2f%n", concepto, getImporte());
		String fechaDePago = estaPagada() ? fechaPago.toString() : "Sin pagar";
		String c2 = String.format("Emision: %s, Pago: %s%n", getFechaEmision(), fechaDePago);
		return c1 + c2;
	}

	public static void main(String[] args) {
		
		LocalDate d1 = LocalDate.of(2020, 11, 4);
		Factura f1 = new Factura(d1, 200.345, "Ordenador");
		System.out.println(f1);
		
		f1.marcarComoPagada();
		System.out.println(f1);
		

		f1.marcarComoPagada();
		System.out.println(f1);
	}
}
