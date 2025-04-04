package aed.skiplist;

import java.util.*;
import es.upm.aedlib.*;
import es.upm.aedlib.map.Map;
import es.upm.aedlib.positionlist.*;

public class SkipListMap<K extends Comparable<K>,V> implements Map<K,V> {

    private K minValue;
    private K maxValue;
    private PositionList<PositionList<Node<K,V>>> skipList;
    private Random random;
    private int elementosSkiplist; // Variable que va a seguir cuenta de los elementos

    public SkipListMap(K minValue, K maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.skipList = new NodePositionList<>();
        this.random = new Random();
        addEmptyList(); // Inicializamos para que skiplist no sea vacío
        elementosSkiplist = 0; // Inicializamos elementos de la SkipList a 0
    }

	
	  public int size() { return elementosSkiplist; }
	  
	  public boolean isEmpty() { return elementosSkiplist == 0; } // Comprueba si hay 0 elementos
	 
   
    public boolean containsKey(Object objKey) throws InvalidKeyException {
        throw new UnsupportedOperationException();
    }

   
    public V put(K key, V value) throws InvalidKeyException {
        // Paso 1: Buscar la posición de la clave en la SkipList
        PositionList<Position<Node<K, V>>> listaSearch = search(key);
        
        // Paso 2: Inicializar val a null (para almacenar el valor si la clave ya existe)
        V val = null;
        
        // Paso 3: Creamos un boolean para verificar si la clave ya existe en la SkipList
        boolean encontrado = listaSearch.last().element().element().getKey().compareTo(key) == 0;
        
        if(encontrado){
            // Si la clave ya existe, se almacena su valor actual
            val = listaSearch.last().element().element().getValue();
            
            // Luego, se elimina la entrada con la clave existente
            remove(key);
            
            // Finalmente, se inserta la nueva entrada con la misma clave y el nuevo valor
            put(key, value);
        }
        else {
            // Si la clave no existe en la SkipList
            elementosSkiplist++; // Incrementar el contador de elementos en la SkipList ya que vas a meter algo nuevo a la skipList
            Position<Node<K, V>> ipos = null;
            Position<Position<Node<K, V>>> posicion = listaSearch.last();
            Position<PositionList<Node<K, V>>> cursor = skipList.last();
            boolean cruz = true;
            
            while(cruz){ 
                Node<K, V> entrada = new Node<K, V>(key, value, ipos);
                
                if (posicion != null){
                    // Si hay una posición válida en la búsqueda, se agrega la entrada después de esa posición
                    cursor.element().addAfter(posicion.element(), entrada);
                    ipos = cursor.element().next(posicion.element());
                }
                else{
                    // Si no hay una posición válida en la búsqueda, se agrega al inicio de la primera lista
                    this.addEmptyList();
                    skipList.first().element().addAfter(skipList.first().element().first(), entrada);
                    ipos = skipList.first().element().next(skipList.first().element().first());
                }
                
                cruz = random.nextBoolean(); // Determinar si continuar con la inserción con el valor random proporcionado
                if(cruz && posicion != null){
                    // Avanzar a la siguiente posición en la búsqueda y en la lista principal
                	posicion = listaSearch.prev(posicion);
                    cursor = skipList.prev(cursor); 
                }
            }
        }
        return val; // Devolver el valor almacenado (si la clave ya existía)
    }

    public V get(K key) throws InvalidKeyException {
        // Verificamos si la key proporcionada es nula
        if (key == null) {
            throw new InvalidKeyException("La clave proporcionada es nula");
        }

        // Inicializamos el valor a null
        V valor = null;

        // Buscamos la posición de la key
        PositionList<Position<Node<K, V>>> listaSearch = search(key);

        // Obtenemos la última posición de la lista de posiciones, la que contiene key(lo sabemos gracias al search)
        Position<Position<Node<K, V>>> posicion = listaSearch.last();

        // Verificamos si la posición es válida
        if (posicion != null) {
            // Obtenemos el nodo correspondiente a la posición
            Node<K, V> nodo = posicion.element().element();
            
            // Verificamos si la key que te dan coincide con la key del nodo
            if (nodo.getKey().equals(key)) {
                // Asignamos el valor del nodo a la variable 'value'
            	valor = nodo.getValue();
            }
        }

        // Devolvemos el valor encontrado (o null si no se encontró, ya que value inicializa null y no se cambiaria)
        return valor;
    }


    public V remove(K key) throws InvalidKeyException {
        // Verificamos si la key proporcionada es nula
        if (key == null) {
            throw new InvalidKeyException();
        }
        // Inicializamos el valor de 'valorElim' a null
        V valorElim = null;

        // Buscamos la posición de la key
        PositionList<Position<Node<K, V>>> listaSearch = search(key);

        // Obtenemos la última posición de la lista de posiciones con key
        Position<Position<Node<K, V>>> posicion = listaSearch.last();

        // Obtenemos la última lista de nodos en skipList
        Position<PositionList<Node<K, V>>> cursorListas = skipList.last();

        // Iteramos mientras haya listas en cursorList y posiciones en camino
        while (cursorListas != null && posicion != null && key.equals(posicion.element().element().getKey())) {
            // Comprobamos que valorElim sigue en null
            if (valorElim == null) {
                // Asignamos el valor del nodo a 'valorElim' y decrementamos elementosSkiplist
            	valorElim = posicion.element().element().getValue();
                elementosSkiplist--;
            }

            // Obtenemos la lista de nodos actual
            PositionList<Node<K, V>> listaActual = cursorListas.element();

            // Removemos el nodo de la lista
            listaActual.remove(posicion.element());

            // Movemos a la lista de nodos anterior en skipList, para eliminar todas sus apariciones
            cursorListas = skipList.prev(cursorListas);

            // Movemos a la posición anterior en camino
            posicion = listaSearch.prev(posicion);
        }

        // Devolvemos el valor eliminado (o null si no se encontró)
        return valorElim;
    }

    
    public Iterable<K> keys() {
        PositionList<K> keys = new NodePositionList<K>();
        Iterator<Entry<K, V>> iterador = iterator();

        while (iterador.hasNext()) keys.addLast(iterador.next().getKey());

        return keys;
    }

    public Iterable<Entry<K, V>> entries() {
        PositionList<Entry<K, V>> entries = new NodePositionList<Entry<K, V>>();
        Position<PositionList<Node<K, V>>> listaPosicion = skipList.last();

        if (listaPosicion != null) {
            PositionList<Node<K, V>> lista = listaPosicion.element();
            Position<Node<K, V>> posicion = lista.first();

            if (posicion != null) posicion = lista.next(posicion);

            while (posicion != lista.last()) {
                entries.addLast(posicion.element());
                posicion = lista.next(posicion);
            }
        }

        return entries;
    }

    public Iterator<Entry<K, V>> iterator() {
        return entries().iterator();
    }

    public String toString() {
        return skipList.toString();
    }

    private void addEmptyList() {
        PositionList<Node<K, V>> nuevaLista = new NodePositionList<>();
        Position<Node<K, V>> below = (!skipList.isEmpty()) ? skipList.first().element().first() : null;
        
        // Comprueba que la lista de debajo no este vacía, si lo esta devuelve null, sino apunta al primero
        Node<K, V> nodoMinimo = new Node<K, V>(minValue, null, below);
        
        // Creamos y añadimos nodo mínimo -infinito
        nuevaLista.addLast(nodoMinimo);
        
        //Misma funcion que anterior condicional, comprobar que below!=null
        below = (!skipList.isEmpty()) ? skipList.first().element().last() : null;
    
        Node<K, V> nodoMaximo = new Node<K, V>(maxValue, null, below);
        // Creamos y añadimos nodo máximo +infinito
        nuevaLista.addLast(nodoMaximo);
        
        //Se añade la lista vacía a la skiplist
        skipList.addFirst(nuevaLista);
        
    }

    private PositionList<Position<Node<K, V>>> search(K key) {
        // Lista para almacenar los resultados de la búsqueda
        PositionList<Position<Node<K, V>>> listaRes = new NodePositionList<>();

        // Inicialización de la posición de la primera lista en skipList
        Position<PositionList<Node<K, V>>> posicion = skipList.first();

        // Bucle para iterar a través de las PositionList en skipList
        for (
            PositionList<Node<K, V>> list = posicion.element();
        		posicion != null;
        		posicion = skipList.next(posicion), list = posicion != null ? posicion.element() : null
        ) {
            // Inicialización del primer nodo en la lista
            Position<Node<K, V>> posNodo = list.first();

            // Variable para determinar si hemos finalizado la búsqueda
            boolean fin = false;

            // Bucle para realizar la búsqueda en la lista actual
            while (!fin) {
                while (posicion != null && key.compareTo(list.next(posNodo).element().getKey()) >= 0) {
                	posNodo = list.next(posNodo);
                }

                if (posNodo.element().getBelow() != null) {
                    // Si hay un nodo debajo, se añade a los resultados y se desciende un nivel
                	listaRes.addLast(posNodo);
                	posNodo = posNodo.element().getBelow();
                    posicion = skipList.next(posicion);
                    list = posicion.element();
                } else {
                    // Si no hay un nodo debajo, se añade a los resultados y finaliza la búsqueda
                	listaRes.addLast(posNodo);
                    fin = true;
                }
            }
        }

        // Devolvemos la lista de posiciones, con o sin elemento de search
        return listaRes;
    }


}