package aed.individual5;

import es.upm.aedlib.Position;
import es.upm.aedlib.map.HashTableMap;
import es.upm.aedlib.map.Map;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;

public class UTILS2 {

	public static <E> PositionList<E> deleteRepeated(PositionList<E> l) {
		PositionList <E> res = new NodePositionList<E>(); 
		Position <E> cursor = l.first();
		if (l.isEmpty())
			return res;
		else {
			while(cursor!=null) {
				E element = cursor.element();
				boolean encontrado=false;
				Position<E> cursorRes = res.first();
				while(cursorRes!=null && !encontrado) {
					E elementRes = cursorRes.element();
					if(element==null && elementRes==null || element!=null && elementRes.equals(element)){
						encontrado=true;
					}
					cursorRes = res.next(cursorRes);
				}
				if (!encontrado) 
	                res.addLast(element);
	            cursor = l.next(cursor);
			}
		}
		return res;
	}
	
	
	public static Map<String, Integer> maxTemperatures(TempData[] tempData) {
		Map<String, Integer> res = new HashTableMap<String, Integer>();
		int i=0;
		while (i<tempData.length) {
			TempData datos = tempData[i];
			String Location = datos.getLocation();
			int Temperatura = datos.getTemperature();
			if(!res.containsKey(Location)|| Temperatura > res.get(Location))
				res.put(Location, Temperatura);
			i++;
		}
	return res;
	}
	
	
}
