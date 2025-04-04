package aed.individual6;


import es.upm.aedlib.positionlist.*;
import es.upm.aedlib.set.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.text.html.HTMLDocument.Iterator;

import es.upm.aedlib.graph.*;
import es.upm.aedlib.map.HashTableMap;
import es.upm.aedlib.map.Map;


public class Utils {

  /**
   * Devuelve un conjunto con todos los vertices alcanzables desde AMBOS
   * v1 y v2.
   */

	public static <V> Set<Vertex<Integer>> reachableFromBoth(
	    DirectedGraph<V, Boolean> g, Vertex<V> v1, Vertex<V> v2) {

	    Set<Vertex<Integer>> reachableFromV1 = reachableFrom(g, v1);
	    Set<Vertex<Integer>> reachableFromV2 = reachableFrom(g, v2);

	    Set<Vertex<Integer>> result = new HashTableMapSet<>();
	    for (Vertex<Integer> vertex : reachableFromV1) {
	        if (reachableFromV2.contains(vertex)) {
	            result.add(vertex);
	        }
	    }

	    return result;
	}

	private static <V> Set<Vertex<Integer>> reachableFrom(
	    DirectedGraph<V, Boolean> g, Vertex<V> source){
		Set<Vertex<Integer>> reachable = new HashTableMapSet<>();
		
		return reachable;
	}

	public static PositionList<Edge<Integer>> existsPathLess(UndirectedAdjacencyListGraph<Integer, Integer> x_1,
			Vertex<Integer> x_2, Vertex<Integer> x_3, int x_4) {
		// TODO Auto-generated method stub
		return null;
	}




  /**
   * Devuelve un camino (una lista de aristas) que llevan desde from y to,
   * donde la suma de los elementos de las aristas del camino <= limit.
   * Si no existe ningun camino que cumple con esta restriccion se devuelve
   * el valor null. 
   */


}

