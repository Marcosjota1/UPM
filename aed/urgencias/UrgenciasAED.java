package aed.urgencias;

import es.upm.aedlib.Entry;
import es.upm.aedlib.Pair;
import es.upm.aedlib.indexedlist.ArrayIndexedList;
import es.upm.aedlib.indexedlist.IndexedList;
import es.upm.aedlib.map.HashTableMap;
import es.upm.aedlib.map.Map;
import es.upm.aedlib.priorityqueue.HeapPriorityQueue;
import es.upm.aedlib.priorityqueue.PriorityQueue;

public class UrgenciasAED implements Urgencias{
	private PriorityQueue<Paciente,Paciente> sigPaciente = new HeapPriorityQueue<>(); // Lista indica paciente a atender, implementada con cola de prioridad
	private Map<String,Entry<Paciente,Paciente>> entradas = new HashTableMap<>(); //Mapa con entradas al hospital
	private Map<String,Paciente> pacientes = new HashTableMap<>(); //Mapa con todos los pacientes y sus datos
	private int sumaTiempoEspera = 0;
	private int pacientesAtendidos = 0;

	
	//Operaciones complejidad 0(log n)	 
	@Override
	public Paciente admitirPaciente(String DNI, int prioridad, int hora) throws PacienteExisteException {
		if (pacientes.containsKey(DNI)) throw new PacienteExisteException();
		return addPaciente(DNI,prioridad,hora,hora);
	}

	@Override
	public Paciente salirPaciente(String DNI, int hora) throws PacienteNoExisteException {
		if(!pacientes.containsKey(DNI)) throw new PacienteNoExisteException();
		return removePaciente(DNI);
	}
	@Override
	public Paciente atenderPaciente(int hora) {
		if (pacientes.isEmpty()) return null;
		Paciente p = sigPaciente.dequeue().getKey();
		pacientes.remove(p.getDNI());
		entradas.remove(p.getDNI());
		sumaTiempoEspera += hora - p.getTiempoAdmision();
		pacientesAtendidos++;
		return p;
	}


	@Override
	public Paciente cambiarPrioridad(String DNI, int nuevaPrioridad, int hora) throws PacienteNoExisteException {
		if (!pacientes.containsKey(DNI)) throw new PacienteNoExisteException();
		Paciente p = pacientes.get(DNI);
		if (p.getPrioridad()!= nuevaPrioridad) {
			p.setPrioridad(nuevaPrioridad);
			p.setTiempoAdmisionEnPrioridad(hora);
			sigPaciente.replaceKey(entradas.get(DNI), p);
			sigPaciente.replaceValue(entradas.get(DNI), p);
		}
		return p;
	}
	
	//Operaciones complejidad 0(1)
	@Override
	public Paciente getPaciente(String DNI) {
		Paciente p = null;
		if(pacientes.containsKey(DNI))
			p = pacientes.get(DNI);
		return p;
	}
	
	@Override
	public Pair<Integer, Integer> informacionEspera() {
		return new Pair <>(sumaTiempoEspera,pacientesAtendidos);
	}
	
//Operaciones complejidad 0(n log n)
	@Override
	public void aumentaPrioridad(int maxTiempoEspera, int hora) {
	    for (String DNI : pacientes.keys()) {
	        Paciente curr = pacientes.get(DNI);
	        if (hora - curr.getTiempoAdmisionEnPrioridad() > maxTiempoEspera && curr.getPrioridad() > 0) {
	            int nuevaPrioridad = curr.getPrioridad() - 1;
	            int tiempoAdmision = removePaciente(DNI).getTiempoAdmision();
	            addPaciente(DNI, nuevaPrioridad, tiempoAdmision, hora);
	        }
	    }
	}


	@Override
	public Iterable<Paciente> pacientesEsperando() {
		PriorityQueue<Paciente,Paciente> aux = new HeapPriorityQueue<>(sigPaciente);
		IndexedList <Paciente> res = new ArrayIndexedList<>();
		while(!aux.isEmpty())
			res.add(res.size(), aux.dequeue().getKey());
		return (Iterable<Paciente>) res;
	}

	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////// METODOS AUXILIARES ////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private Paciente addPaciente(String DNI, int prioridad, int hora, int horaUrgencia) {
		 Paciente p = new Paciente( DNI, prioridad, hora,horaUrgencia); 
		 pacientes.put(DNI, p);
		 entradas.put(DNI, sigPaciente.enqueue(p, p)); 
		 return p;
	}
	
	private Paciente removePaciente(String DNI) {
		Paciente p = pacientes.get(DNI);
		sigPaciente.remove(entradas.get(DNI));
		pacientes.remove(DNI);
		entradas.remove(DNI);
		return p;
	}
}
