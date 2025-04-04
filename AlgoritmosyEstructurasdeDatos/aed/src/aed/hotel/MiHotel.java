package aed.hotel;

import java.util.Comparator;

import es.upm.aedlib.indexedlist.*;


/**
 * Implementa el interfaz Hotel, para realisar y cancelar reservas en un hotel,
 * y para realisar preguntas sobre reservas en vigor.
 */
public class MiHotel implements Hotel {
  /**
   * Usa esta estructura para guardar las habitaciones creados.
   */
  private IndexedList<Habitacion> habitaciones;
  MiComparadorHab compHabitacion = new MiComparadorHab();
  MiComparadorPre compPrecio = new MiComparadorPre();
  MiComparadorReserva compFecha = new MiComparadorReserva();

  /**
   * Crea una instancia del hotel. 
   */
  public MiHotel() {
    // No se debe cambiar este codigo
    this.habitaciones = new ArrayIndexedList<>();
  }

@Override
public void anadirHabitacion(Habitacion habitacion) {
	int i=0;
	while(i< habitaciones.size()) {
		if(habitacion.equals(habitaciones.get(i))) {
			throw new IllegalArgumentException();
			
		}
		i++;
	}
	MiHotel.insertar(habitacion, habitaciones, compHabitacion);
	
}

@Override
public boolean reservaHabitacion(Reserva reserva) {
	if(reserva==null) {
		throw new IllegalArgumentException("HABITACIÓN NO DISPONIBLE");
	}
	boolean existe = false;
	boolean reservado = false;
	int i1= 0;
	while(i1< habitaciones.size() && !existe) {
		if(habitaciones.get(i1).getNombre().equals(reserva.getHabitacion())) {
			existe = true;
			
		}
		else{
			i1++;	
		}
	}
	if(!existe) {
		
			throw new IllegalArgumentException("HABITACIÓN NO DISPONIBLE");
	}
	else if(estaDisponible(habitaciones.get(i1), reserva.getDiaEntrada(),reserva.getDiaSalida())) {
		MiHotel.insertar(reserva, habitaciones.get(i1).getReservas(), compFecha);
		reservado=true;
	}
	return reservado;
}

@Override
public boolean cancelarReserva(Reserva reserva) {
    boolean coincide=false;
    boolean existe = false;
    int i1= 0;
    int i2 = 0;
    while(i1< habitaciones.size() && !coincide && !existe) {
        if(habitaciones.get(i1).getNombre().equals(reserva.getHabitacion())){
            existe = true;
        }
        for(i2= 0; i2 < habitaciones.get(i1).getReservas().size();i2++){
            if(habitaciones.get(i1).getReservas().get(i2).equals(reserva)) {
                habitaciones.get(i1).getReservas().removeElementAt(i2);
                coincide = true;
            }
        }
        i1++;
        i2 = 0;
    }
    if(!existe) {
        throw new IllegalArgumentException();
    }
    return coincide;
}
public IndexedList<Habitacion> disponibilidadHabitaciones(String diaEntrada, String diaSalida) {
    IndexedList<Habitacion> nuevaLista = new ArrayIndexedList<Habitacion>();
    boolean disponible = true;
    int i2= 0;
    for(int i = 0; i< habitaciones.size(); i++) {
        i2 = 0;
        while(i2<habitaciones.get(i).getReservas().size() && disponible) {
            if(!(estaDisponible(habitaciones.get(i), diaEntrada, diaSalida))) {
                disponible = false;
        }
        i2++;
        }
        if(disponible) {
            MiHotel.insertar(habitaciones.get(i), nuevaLista, compPrecio);
        }
        disponible = true;

    }

    return nuevaLista;
}
@Override
/*public IndexedList<Habitacion> disponibilidadHabitaciones(String diaEntrada, String diaSalida) {
	IndexedList<Habitacion> nuevaLista = new ArrayIndexedList<Habitacion>();
	int i2= 0;
	for(int i = 0; i< habitaciones.size(); i++) {
		i2 = 0;
		while(i2<habitaciones.get(i).getReservas().size()) {
		if(habitaciones.get(i).getReservas().get(i2).getDiaSalida().compareTo(diaEntrada) < 0 || 
		diaSalida.compareTo(habitaciones.get(i).getReservas().get(i2).getDiaEntrada()) < 0) {
			MiHotel.insertar(habitaciones.get(i), nuevaLista, compPrecio);
		}
		i2++;
		}
		
	}
	
	return nuevaLista;
}

/*private static void insertar(Habitacion habitacion, IndexedList<Habitacion> nuevaLista, MiComparadorPre compPrecio2) {
	boolean anadido = false;
	for (int i=0; i < nuevaLista.size() && !anadido; i++) {
		if (compPrecio2.compare(habitacion.getPrecio(), nuevaLista.get(i).getPrecio()) < 0){
			nuevaLista.add(i, habitacion);
			anadido = true;
		}
	}
	if(!anadido) {
		nuevaLista.add(nuevaLista.size(), habitacion);
	}
	
}
*/

public IndexedList<Reserva> reservasPorCliente(String dniPasaporte) {
	IndexedList<Reserva> nuevaLista = new ArrayIndexedList<Reserva>();
	int i2= 0;
	for(int i = 0; i< habitaciones.size(); i++) {
		i2 = 0;
		while(i2 < habitaciones.get(i).getReservas().size()) {
		if(habitaciones.get(i).getReservas().get(i2).getDniPasaporte().equals(dniPasaporte)) {
			MiHotel.insertar(habitaciones.get(i).getReservas().get(i2), nuevaLista, compFecha);
		}
		i2++;
		}
	}
	
	return nuevaLista;
}



/*private static void insertar(Reserva reserva, IndexedList<Reserva> nuevaLista, MiComparadorReserva compFecha2) {
	int i2= 0;
	boolean anadido = false;
	for (int i=0; i < nuevaLista.size() && !anadido; i++)  {
		if (compFecha2.compare(reserva, nuevaLista.get(i) < 0){
			nuevaLista.add(i, reserva);
			anadido = true;
		}
		}
	if(!anadido) {
		nuevaLista.add(nuevaLista.size(), reserva);
	}

}
 */
@Override
public IndexedList<Habitacion> habitacionesParaLimpiar(String hoyDia) {
	IndexedList<Habitacion> nuevaLista = new ArrayIndexedList<Habitacion>();
	int i2= 0;
	for(int i = 0; i< habitaciones.size(); i++) {
		i2 = 0;
		while(i2<habitaciones.get(i).getReservas().size()) {
		if(habitaciones.get(i).getReservas().get(i2).getDiaEntrada().compareTo(hoyDia) < 0 && 
		hoyDia.compareTo(habitaciones.get(i).getReservas().get(i2).getDiaSalida()) <= 0) {
			MiHotel.insertar(habitaciones.get(i), nuevaLista, compHabitacion);
		}
		i2++;
		}
		
	}
	
	return nuevaLista;
}

@Override
public Reserva reservaDeHabitacion(String nombreHabitacion, String dia) {
    int i1 = 0;
    int i2 = 0;
    boolean existeHab = false;
    boolean existeRes = false;
    Reserva nReserva = null;
    while (i1 < habitaciones.size() && !existeHab) {
        if(nombreHabitacion.equals(habitaciones.get(i1).getNombre())) {
            existeHab = true;
        }
        else {
            i1++;

        }
            while(i2 < habitaciones.get(i1).getReservas().size() && !existeRes) {
                if(dia.compareTo(habitaciones.get(i1).getReservas().get(i2).getDiaEntrada()) >= 0 && 
                dia.compareTo(habitaciones.get(i1).getReservas().get(i2).getDiaSalida()) < 0) {
                    nReserva = habitaciones.get(i1).getReservas().get(i2);
                    existeRes=true;
             
                }else {
                i2++;
                }
            }
    }
        
    if (!existeHab) {
        throw new IllegalArgumentException();
    }

    return nReserva;

}


static <E> void insertar(E e,IndexedList<E> l,Comparator<E> cmp) {
	boolean anadido = false;
	for (int i=0; i < l.size() && !anadido; i++) {
		if (cmp.compare(e, l.get(i)) < 0){
			l.add(i, e);
			anadido = true;
		}
	}
	if(!anadido) {
		l.add(l.size(), e);
	}
}
static class MiComparadorHab implements Comparator<Habitacion> {

	@Override
	public int compare(Habitacion o1, Habitacion o2) {
		return o1.compareTo(o2);
	}

}
static class MiComparadorPre implements Comparator<Habitacion> {
	@Override
	public int compare(Habitacion o1, Habitacion o2) {
		return (o1.getPrecio()- o2.getPrecio());
	}

}
static class MiComparadorReserva implements Comparator<Reserva> {
	@Override
	public int compare(Reserva o1, Reserva o2) {
		return o1.compareTo(o2);
	}
}
static boolean estaDisponible(Habitacion habitacion,String dEntrada, String dSalida){
    boolean estaDisponible = true;
    int i = 0;
    if(habitacion.getReservas().size() ==0) {
        estaDisponible = true;

    }
    while(estaDisponible && i<habitacion.getReservas().size()) {

        if(habitacion.getReservas().get(i).getDiaSalida().compareTo(dEntrada) <= 0 || 
        dSalida.compareTo(habitacion.getReservas().get(i).getDiaEntrada()) <= 0) {
            i++;
            estaDisponible = true;
        }
        else {
            estaDisponible = false;
        }
    }
    return estaDisponible;
}
}