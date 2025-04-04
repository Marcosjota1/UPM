package facturas;

import java.util.Comparator;

public class OrdenarPorFecha implements Comparator<Factura> {

	@Override
	public int compare(Factura o1, Factura o2) {
		return o1.getFechaEmision().compareTo(o2.getFechaEmision()); 
	}

}
