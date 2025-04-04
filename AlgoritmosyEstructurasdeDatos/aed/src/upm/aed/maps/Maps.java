package upm.aed.maps;
import es.upm.aedlib.map.HashTableMap;
import es.upm.aedlib.map.Map;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Function;
import java.util.function.BiFunction;

import java.util.function.Predicate;

import es.upm.aedlib.*;

import es.upm.aedlib.map.Map;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Function;
import java.util.function.BiFunction;

import java.util.function.Predicate;

import es.upm.aedlib.*;

import es.upm.aedlib.map.HashTableMap;
import es.upm.aedlib.map.Map;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Function;
import java.util.function.BiFunction;

import java.util.function.Predicate;

import es.upm.aedlib.*;

import es.upm.aedlib.map.Map;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Function;
import java.util.function.BiFunction;

import java.util.function.Predicate;

import es.upm.aedlib.*;

public class Maps {
	public static void main(String[] args) {}



		


//	public static <K,V> void show (Map<K,V> map)
//	{
//		EjemplosIteradores.show (map.entries());
//	}


	//	public static void eliminarElem (Map<Character,Integer> map,Integer num) {
	//		Iterator<Entry<Character,Integer>> it = map.entries().iterator();
	//		while(it.hasNext()) {
	//			Entry<Character,Integer> entry = it.next();
	//			if(entry.getValue().equals(num)) {
	//				map.remove(entry.getKey());
	//			}
	//		}
	//	}

	public static <K,V> void eliminarElem (Map<K,V> map,V elem) {
		for (Entry<K,V> entry : map.entries())
		{
			//		Iterator<Entry<K,V>> it = map.entries().iterator();
			//		while(it.hasNext()) {
			//			Entry<K,V> entry = it.next();
			if(entry.getValue().equals(elem) ) {
				map.remove(entry.getKey());
			}
		}
	}

	public static <K,V> void eliminarElem (Map<K,V> map,Predicate<V> pred) {
		for (Entry<K,V> entry : map.entries())
		{
			//		Iterator<Entry<K,V>> it = map.entries().iterator();
			//		while(it.hasNext()) {
			//			Entry<K,V> entry = it.next();
			if(pred.test(entry.getValue()) ) {
				map.remove(entry.getKey());
			}
		}
	}

	public static int suma (Map<String,Integer> map)
	{
		int suma = 0;
//		for (Entry<String,Integer> entry : map.entries())
//			suma += entry.getValue();
		for (String key : map.keys())
			suma += map.get(key);
		return suma;
	}



	public static <K, V extends Comparable<V>> 
	     void eliminarElem2 (Map<K,V> map,V elem) {
//		Iterator<Entry<K,V>> it = map.entries().iterator();
//		while(it.hasNext()) {
//			Entry<K,V> entry = it.next();
//			if(entry.getValue().compareTo(elem)==0) {
//				map.remove(entry.getKey());
//			}
//		}
		
		for (Entry<K,V> entry : map.entries()) {
			if(entry.getValue().compareTo(elem)==0) {
				map.remove(entry.getKey());
			}
		}
	}

	public static <K, V> void eliminarElem3 (Map<K,V> map,V elem, Comparator<V> c) {
		Iterator<Entry<K,V>> it = map.entries().iterator();
		while(it.hasNext()) {
			Entry<K,V> entry = it.next();
			V val = entry.getValue();
			if(c.compare (val,elem)==0) {
				map.remove(entry.getKey());
			}
		}
	}

	public static <K,V> void eliminarPredicado (Map<K,V> map, Predicate<V> p) {
		Iterator<Entry<K,V>> it = map.entries().iterator();
		for (Entry<K,V> entry : map.entries())
		{
			if(p.test(entry.getValue())) {
				map.remove(entry.getKey());
			}
		}
	}
	public static <K,V> void eliminarPredicado2 (Map<K,V> map, Predicate<V> p) {
		Iterator<Entry<K,V>> it = map.entries().iterator();
		for (Entry<K,V> entry : map.entries())
		{
			if(p.test(entry.getValue())) {
				map.remove(entry.getKey());
			}
		}
	}

	public static <K,V> Map<K,V> copyFiltro (Map<K,V> map,Predicate<V> p) {
		Map<K,V> result = new HashTableMap<K,V>();
		for (Entry<K,V> entry : map.entries())
			if (p.test(entry.getValue()))
				result.put(entry.getKey(), entry.getValue());
		return result;
	}
	public static <K,V> Map<K,V> copy (Map<K,V> map) {
		Map<K,V> result = new HashTableMap<K,V>();
		Iterator<Entry<K,V>> it = map.entries().iterator();
		while(it.hasNext()) {
			Entry<K,V> entry = it.next();
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	public static <K,V,W> Map<K,W> duplicateApplyF (Map<K,V> map, Function<V,W> f) {
		Map<K,W> result = new HashTableMap<K,W>();
		Iterator<Entry<K,V>> it = map.entries().iterator();
		while(it.hasNext()) {
			Entry<K,V> entry = it.next();
			result.put(entry.getKey(), f.apply(entry.getValue()));
		}
		return result;
	}

	public static <K,V,W> void duplicateApplyF2 (Map<K,V> map, Function<V,V> f) {
		Iterator<Entry<K,V>> it = map.entries().iterator();
		while(it.hasNext()) {
			Entry<K,V> entry = it.next();
			map.put(entry.getKey(), f.apply(entry.getValue()));
		}
	}

	public static <K,V,W> void duplicateApplyF3 (Map<K,V> map, Function<V,V> f) {
		for (K key : map.keys())
			map.put(key, f.apply(map.get(key)));

	}

	//	 Dada una tabla de K -> V, un predicado sobre K (las clave)  y 
	//	 una funcion f:  V -> W se pide obtener la lista por posicion de las 
	//	 entrys de la tabla que cumplen que las claves cumplen 
	//	 el predicado y los valores de las Entry de la lista posicion son f()

	public static <K,V,W> PositionList<Entry<K,W>> 
	duplicateFP (Map<K,V> map, 
			Predicate<K> p, Function<V,W> f) {
		PositionList<Entry<K,W>> res = new NodePositionList<Entry<K,W>>();
		for (Entry<K,V> e : map.entries())
			if (p.test(e.getKey()))
			{
				Entry<K,W> new_e = new EntryImpl<K,W>
				(e.getKey(), f.apply(e.getValue()));
				res.addLast(new_e);
			}
		return res;
	}


	public static <K,V,W> Map<K,W> duplicateFP2 (Map<K,V> map, Predicate<V> p, Function<V,W> f) {

		Map<K,W> result = new HashTableMap<K,W>();
		Iterator<Entry<K,V>> it = map.entries().iterator();
		while(it.hasNext()) {
			Entry<K,V> entry = it.next();
			if(p.test(entry.getValue())) {
				result.put(entry.getKey(),f.apply(entry.getValue()));
			}
		}
		return result;
	}

	public static <K,V> boolean todosP (Map<K,V> map,Predicate<V> p)
	{
		for (Entry<K,V> e : map.entries())
			if (!p.test(e.getValue()))
				return false;
		return true;
	}

	public static <K,V> boolean algunoP (Map<K,V> map,Predicate<V> p)
	{
		//		boolean existe = false;  
		//		Iterator<Entry<K,V>> it = map.entries().iterator();
		//		while (it.hasNext() && !existe)
		//		{
		//			Entry<K,V> entry = it.next();
		//			if (p.test(entry.getValue()))
		//				existe = true;
		//		}
		//		return existe;

		for (Entry<K,V> e : map.entries())
			if (p.test(e.getValue()))
				return true;
		return false;
	}






	public static <K,V extends Comparable<V>> V max (Map<K,V> map, Predicate<V> p) throws InvalidKeyException
	{
		return max (copyFiltro (map,p));
	}

	public static <K,V extends Comparable<V>> V max (Map<K,V> map) throws InvalidKeyException
	{
		if (map.isEmpty())
			throw new InvalidKeyException();
		else
		{
			Iterator<Entry<K,V>> it = map.entries().iterator();
			V max = it.next().getValue();
			while (it.hasNext())
			{
				V elem = it.next().getValue();
				if (elem.compareTo(max)>0)
					max = elem;
			}
			return max;
		}
	}
	

	public static <K,V extends Comparable<V>> V max2 (Map<K,V> map) throws InvalidKeyException
	{
		if (map.isEmpty())
			throw new InvalidKeyException();
		else
		{
			Iterator<Entry<K,V>> it = map.entries().iterator();
			V max = it.next().getValue();
			for (Entry<K,V> e : map.entries())
			{				
				if (e.getValue().compareTo(max)>0)
					max = e.getValue();
			}
			return max;
		}
	}
	public static <E> boolean compruebaSec (PositionList<E> sec, Map<E,E> mapa) {
		if(sec.size()<=2) throw new IllegalArgumentException();
		boolean si = true;
		Position<E> cursor = sec.first();
		E elem = cursor.element();
		cursor = sec.next(cursor);
		while(cursor!=null&&si) {
			si=cursor.element().equals(mapa.get(elem));
			elem=cursor.element();
			cursor=sec.next(cursor);
		}
		return si;
		
	}

	public static <E> Iterable<E> obtenerSecuencia(Map<E, E> mapa, E pos) {
		PositionList<E> res = new NodePositionList<>();
		Map<E, E> mapAux = new HashTableMap<>();
		while (mapa.containsKey(pos) && !mapAux.containsKey(pos)) {
			mapAux.put(pos, pos);
			res.addLast(pos);
			pos = mapa.get(pos);
		}
		res.addLast(pos);
		return res;
	}

	}