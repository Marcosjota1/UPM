package aed.individual2;

import es.upm.aedlib.indexedlist.*;

public class IndexedListCheckSumUtils {
	
	public static IndexedList<Integer> indexedListCheckSum(IndexedList<Integer> list, int n) {
	    if (list.size() == 0) {
	        IndexedList<Integer> arr = new ArrayIndexedList<>();
	        return arr;
	    } else {
	        IndexedList<Integer> nuevo = new ArrayIndexedList<>();
	        int checksum = 0;
	        int contador = 0;
	        int arrayAntiguo = 0;
	        int arrayNuevo = 0;

	        while (arrayAntiguo < list.size()) {
	            if (contador == n) {
	                nuevo.add(arrayNuevo, checksum);
	                contador = 0;
	                checksum = 0;
	            } else if (arrayAntiguo == list.size()) {
	                nuevo.add(arrayNuevo, checksum);
	            } else { // cuando contador no es n y no final lista
	                nuevo.add(arrayNuevo, list.get(arrayAntiguo));
	                checksum += list.get(arrayAntiguo);
	                arrayAntiguo++;
	                contador++;
	            }

	            arrayNuevo++;
	        }
	        if (arrayAntiguo == list.size()) {
	            nuevo.add(arrayNuevo, checksum);
	        }
	        return nuevo;
	    }
	}

	public static boolean checkIndexedListCheckSum(IndexedList<Integer> list, int n) {
	    if (list.size() == 0) {
	        return true;
	    }

	    int checksum = 0;
	    int contador = 0;

	    for (int i = 0; i < list.size(); i++) {
	        if (contador != n && i != list.size() - 1) {
	            contador++;
	            checksum += list.get(i);
	        } else {
	            if (checksum != list.get(i)) {
	                return false;
	            } else {
	                contador = 0;
	                checksum = 0;
	            }
	        }
	    }

	    return true;
	}
}
/*	public static IndexedList<Integer> indexedListCheckSum(IndexedList<Integer> list, int n) {
if (list.size() == 0) {
    IndexedList<Integer> arr = new ArrayIndexedList<>();
    return arr;
} else {
    IndexedList<Integer> nuevo = new ArrayIndexedList<>();
    int checksum = 0;
    int contador = 0;
    int arrayAntiguo = 0;
    int arrayNuevo = 0;

    while (arrayAntiguo < list.size()) {
        if (contador == n ) {
            nuevo.add(arrayNuevo, checksum);
            contador = 0;
            checksum = 0;
        } 
        else if( arrayAntiguo==list.size()) {
      	nuevo.add(arrayNuevo, checksum);
        }
        	
        else { // cuando contador no es n
            nuevo.add(arrayNuevo, list.get(arrayAntiguo));
            checksum += list.get(arrayAntiguo);
            arrayAntiguo++;
            contador++;
        }

        arrayNuevo++;
    }
    if (arrayAntiguo==list.size()) {
    	nuevo.add(arrayNuevo,checksum);        
    	    }
    return nuevo;
}
}

*/ 


/* public static IndexedList<Integer> indexedListCheckSum(IndexedList<Integer> list, int n) {
if (list.size() == 0) {
IndexedList<Integer> arr = new ArrayIndexedList<>();
return arr;
} else {
IndexedList<Integer> nuevo = new ArrayIndexedList<Integer>();
int sumanNumeros = 0;

for (int i = 0; i < list.size(); i++) {
    sumanNumeros += list.get(i);
    if ((i + 1) % n == 0 || i == list.size() - 1) {
        nuevo.add(i, sumanNumeros);
        sumanNumeros = 0;
    }
}

return nuevo;
}
}
*/
// list no es null, podria tener tamaño 0, n>0

/*
public static boolean checkIndexedListCheckSum(IndexedList<Integer> list, int n) {

boolean correctos = true;
if(list.size()==0) {correctos = true;}
else {
    int checksum = 0;
    int contador = 0;
    int i = 0;
    while (i<list.size() && correctos) {
    	if(contador!=n) {
    		contador++;
    		checksum+=list.get(i);
    	}
    	else { 
    		if(contador == n || i==list.size()){
    		  if(checksum!= list.get(i)) {
    			correctos=false;
    		}
    		}
    	else { 
    		if( checksum==list.get(i)) {
    			contador=0;
    			checksum=0;
    		}
    		
    	}
    		i++;
      }
    }
 }
return correctos;
}
}

*/