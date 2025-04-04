package aed.individual5;

import java.util.Iterator;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.*;
import es.upm.aedlib.map.*;

public class Utils {
  
	public static <E> PositionList<E> deleteRepeated(PositionList<E> l) {
	    PositionList<E> res = new NodePositionList<E>();
	    Position<E> cursor = l.first();  	
	    
	    if (l.isEmpty()) {
	        return res;
	    } else {
	        while (cursor != null) {
	            E element = cursor.element();
	            boolean encontrado = false;
	            Position<E> cursorRes = res.first();
	            while (cursorRes != null && !encontrado) {
	                E elementRes = cursorRes.element();
	                if (element == null && elementRes == null || element != null && element.equals(elementRes)) {
	                    encontrado = true;
	                }
	                cursorRes = res.next(cursorRes);
	            }
	            if (!encontrado) 
	                res.addLast(element);
	            cursor = l.next(cursor);
	        }
	        return res;
	    }
	}
  
	public static <E> PositionList<E> compactar(Iterable<E> lista) {
	    if (lista == null) 
	        throw new IllegalArgumentException("La lista no puede ser nula");
	    
	    PositionList<E> res = new NodePositionList<E>();
	    Iterator<E> it = lista.iterator();

	    if (!it.hasNext()) {
	        return res; // La lista está vacía
	    }
	    E first = it.next(); //Almacenas primer elemento
	    res.addLast(first);  // Primero nunca va a estar repetido, lo añades
	    while (it.hasNext()) {
	        E second = it.next(); // elemento siguiente 
	        if (first == null && second == null) 
	            continue; // Ambos son null, por lo que no se añade
	        
	        if (first == null || !first.equals(second)) {
	            res.addLast(second);
	            first = second; // Pasas a first el valor de second(second varia con it.next), y empieza el while
	        }
	    }
	    return res;
	}

	public static Map<String, Integer> maxTemperatures(TempData[] tempData) {
        Map<String, Integer> maxTemperaturesMap = new HashTableMap<>();
        int puntero = 0; // Creamos para recorrer el array, 

        while (puntero < tempData.length) { // recorre array con las distintas horas y temperaturas
            TempData data = tempData[puntero]; // puntero en inicio
            String location = data.getLocation();     //  Nombre y temperatura primera ciudad del array
            int temperature = data.getTemperature();  //

            if (!maxTemperaturesMap.containsKey(location)//verifica que no existe ya en la HashTable la location
            	|| temperature > maxTemperaturesMap.get(location))// O si esta en la hash table, mira si la temperatura es mayor){
                maxTemperaturesMap.put(location, temperature);
            
            puntero++;
        }
        return maxTemperaturesMap;
    }  





















}


