package EjemplosClase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import es.upm.aedlib.Position;
import es.upm.aedlib.indexedlist.ArrayIndexedList;
import es.upm.aedlib.indexedlist.IndexedList;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;


public class EjemplosIteradores {

	public static void main(String[] args) {
		PositionList<Integer> listPosition = new NodePositionList<>(new Integer[] {1,2,2,3,5});
		ArrayList<Integer> a = new ArrayList<Integer>();
		IndexedList<Integer> listIndexed = new ArrayIndexedList<>(new Integer[] {1,5});
		List<Integer> listList =new ArrayList<Integer>();
		Stack<Integer> st = new Stack<Integer>();
		st.push(1);
		st.push(3);
		st.push(2);
		listList.add(1);
		listList.add(3);
		listList.add(2);

		// System.out.println(listPosition);

		System.out.println(member3  (listPosition,2));
		System.out.println(member3  (listIndexed,2));
		System.out.println(member3  (listList,2));
		System.out.println(member3  (st,2));
		System.out.println(sorted (st));
		System.out.println(sorted (listIndexed));

		System.out.println(member4 (listPosition.iterator(),2));
		System.out.println(member4 (listIndexed.iterator(),2));
		System.out.println(member4 (listList.iterator(),2));
		System.out.println(member4 (st.iterator(),2));
		System.out.println(iguales  (st,listList));
		//		
		System.out.println(subsetIter(listList,st));
		System.out.println(subPred(listList,st,predEjemplo (listList)));
		//System.out.println(
			//	sumaIter(filterIter(listPosition, 
		//				     x -> true, 
		//		             y -> Math.sqrt(y),
		//		             0.0,
		//		             (x,y)-> x+y);
		System.out.println(
				sumaIter(st, 
						  x -> x % 2==0, 
				             y -> Math.sqrt(y),
				             0.0,
				             (x,y)-> x+y));
		
				System.out.println(subsetIter(listPosition,st));
				System.out.println(subsetIter(st, listPosition));
				
		//		
		//		Iterator<Integer> it1 = list.iterator();
		//		System.out.println(member4(5, it1));
		//		
		//		Iterator<Integer> it2 = a.iterator();
		//		System.out.println(member4(5, it2));
		//		
		//		Iterator<Integer> it3 = indexl.iterator();
		//		System.out.println(member4(5, it3));
		//		
		//
		////		borra (3, a);
		////		borra (3, list);
		//
		//		System.out.println(member3(5, a));
		//
		//		System.out.println(subsetI(list,list2));
		//		System.out.println(iguales(list,a));


	}

	// METODO INCORRECTO!!!
	public static <E> boolean member(E e, PositionList<E> list) {
		Iterator<E> it = list.iterator();
		while (it.hasNext() && !eqNull  (e,it.next()));

		return it.hasNext();
	}




	public static <E> boolean memberI (PositionList<E> list, E element) {
		if (list == null || list.size() == 0) {  // valores frontera 
			return false;
		}
		boolean enc = false; // list determina el DOMINIO DE RECORRIDO
		Position<E> cursor = list.first();  // cursor variable de recorrido
		while (cursor != null && !enc) { // i < list.length
			if (eqNull(cursor.element(),element)) //  list[i]
				enc = true;
			else
				cursor = list.next(cursor); // i++
		}
		return enc;		
	}
	public static Iterable<Integer> sumarTrios (Iterable<Integer> iterable){
		PositionList<Integer> res = new NodePositionList<>();
		Iterator<Integer> it = iterable.iterator();
		Integer p1 = it.next();
		if(!it.hasNext()) return res;
		Integer p2 = it.next();
		while(it.hasNext()) {
			Integer current=it.next();
			res.addLast(current+p1+p2);
			p1=p2;
			p2=current;
		}
		return res;	
		}
	
	
	
	
	
	public static <E> boolean member2(PositionList<E> list, E e) {
		if (list == null || list.size() == 0) {  // valores frontera 
			return false;
		}
		Iterator<E> it = list.iterator();
		boolean found = false;
		while ( it.hasNext() && !found) {
			found = eqNull(e, (it.next()));
		}
		return found;
	}
	
	
	
	
	

	static <E> boolean member3 (Iterable<E> col, E e) {
		Iterator<E> it = col.iterator();
		boolean found = false;
		while ( it.hasNext() && !found) {
			found = eqNull (e, it.next());
		}
		return found;
	}




	public static <E> boolean member4 (Iterator<E> it, E e) {
		boolean found = false;
		while ( it.hasNext() && !found) 
			found = e.equals(it.next());
		return found;
	}
	
	
	
	static <E> int cuantosCumplen (Iterable<E> col, Predicate<E> p) {
		
		int cuantos = 0;
		Iterator<E> it = col.iterator();
		while ( it.hasNext()) {
			if (p.test(it.next()))
					cuantos++;
		}
		return cuantos;
	}
	
	static <E> int cuantosCumplen2 (Iterable<E> col, Predicate<E> p) {
//		Iterator<E> it = col.iterator();
		int cuantos = 0;
		for (E e : col) 
			if (p.test(e)) cuantos++;
		return cuantos;
	}
	
	

	public static <E extends Comparable<E>> boolean sorted (Iterable<E> l) {
		if (l == null) return false;
		
		boolean todos = true;
		Iterator<E> it = l.iterator();
		E elem = null;
		if (it.hasNext()) 
			elem = it.next();
		while ( it.hasNext() && todos) 
		{
			E aux = it.next();
			if (elem.compareTo(aux) > 0)
			    todos = false;
			else
				elem = aux;
			    	
		}
		return todos;	
	}
	public static <E,F> F sumaIter 
	(		Iterable<E> col, 
			Predicate<E> pred,
			Function<E,F> f,
			F neuter,
			BiFunction<F,F,F> sum)
	{
		F res = neuter;
		Iterator<E> it = col.iterator();
		while (it.hasNext())
		{
			E elem = it.next();
			if (pred.test(elem))
				res = sum.apply(res, f.apply(elem));
		}
		return res;
	}
		

	public static <E extends Comparable<E>> boolean member5(E e, Iterator<E> it) {
		//		Iterator<E> it = col.iterator();
		boolean found = false;
		while ( it.hasNext() && !found) {
			found = e.equals(it.next());
		}
		return found;
	}

	public static <E> void borraTonto (PositionList<E> list, E e, E a) {
		Iterator<E> it = list.iterator();
		while (it.hasNext()) {
			E elem = it.next();
			if (eqNull(e,elem)) 
				it.remove();
			else if (eqNull(a, elem))
				System.out.println(elem);
		}
	}


	public static <E> void borra(Iterable<E> list, E e) {
		Iterator<E> it = list.iterator();
		while (it.hasNext()) {
			if (eqNull(e, it.next()))  
				it.remove();
		}
	}

	public static <E> void borra(E e, Iterable<E> list) {
		Iterator<E> it = list.iterator();
		while (it.hasNext()) {
			E elem = it.next();
			if (e == null && elem == null || e != null && e.equals(elem))
				it.remove();
		}
	}

	
	public static <E extends Comparable<E>> E maxIter (Iterable<E> col)
	{
		Iterator<E> it = col.iterator();
		E max = it.next();
		while (it.hasNext())
		{
			E aux = it.next();
			if (aux.compareTo(max) > 0)
				max = aux;
		}
		return max;
	}
	
	public static <E> E maxIter (Iterable<E> col, Comparator<E> comp)
	{
		Iterator<E> it = col.iterator();
		E max = it.next();
		while (it.hasNext())
		{
			E aux = it.next();
			if (comp.compare(aux,max) > 0)
				max = aux;
		}
		return max;
	}
	
	public static <E> Iterable<E> filterIter (Iterable<E> col, Predicate<E> p) 
    {
		IndexedList<E> res = new ArrayIndexedList<E>();
		Iterator<E> it = col.iterator();
		while (it.hasNext())
		{
			 E elem = it.next();
  		     if (p.test(elem))
  		        res.add(res.size(), elem);	
		}
		return res;
    }	
	
	
	
	
	
	
	
	
	
	//	public static <E> boolean subset (PositionList<E> l1, PositionList<E> l2) {
	//		if (l1 == null || l2 == null) return false;
	//		if (l1 == l2) return true;
	//		Iterator<E> it1 = l1.iterator();
	//		boolean res = true;
	//		while ( it1.hasNext() && res ) {
	//			
	//			res = member2(it1.next(),l2);
	//		}
	//		return res;
	//	}
	public static <E> boolean subsetIter (Iterable<E> l1, Iterable<E> l2) {
		if (l1 == null || l2 == null) return false;
		if (l1 == l2) return true;
		Iterator<E> it1 = l1.iterator();
		boolean res = true;
		while ( it1.hasNext() && res ) 
			res = member3(l2, it1.next());
		return res;
	}
	// ESQUEMA DE TIPO "PARA TODO"
	public static <E> boolean subsetIter2 (Iterable<E> l1, Iterable<E> l2) {
		if (l1 == null || l2 == null) return false;
		if (l1 == l2) return true;
		boolean res = true;
		for (E e : l1)
			if (!member3  (l2, e))
				return false;
		return res;
	}
	public static <E> Predicate<E> predEjemplo (Iterable<E> l)
	{
		return x -> member3  (l,x);
	}
	
	
	
	public static <E> boolean subPred (
			Iterable<E> l1, 
			Iterable<E> l2,
			Predicate<E> p) {
		if (l1 == null || l2 == null) return false;
		if (l1 == l2) return true;
		boolean res = true;
		for (E e : l1)
			if (!p .test(e))
				return false;
		return res;
	}
	//	public static <E> boolean subset (ArrayList<E> l1, ArrayList<E> l2) {
	//		if (l1 == null || l2 == null) return false;
	//		if (l1 == l2) return true;
	//		Iterator<E> it1 = l1.iterator();
	//		boolean res = true;
	//		while ( it1.hasNext() && res ) {
	//			
	//			res = member2(it1.next(),l2);
	//		}
	//		return res;
	//	}

	// TODOS
	public static <E> boolean iguales (Iterable<E> iter1,
			Iterable<E> iter2) {
		Iterator<E> it1 = iter1.iterator();
		Iterator<E> it2 = iter2.iterator();
		boolean iguales = true;
		while (it1.hasNext() &&  it2.hasNext() && iguales) 
			iguales = eqNull(it1.next(),it2.next());
		return it1.hasNext() == it2.hasNext() && iguales;
	}
	
	
	
	
	
	
	
	
	
	public static <E> boolean iguales (PositionList<E> list1,
			PositionList<E> list2) {
		
		return igualesRec (list1.iterator(), list2, list2.first());
	}

	public static <E> boolean igualesRec (Iterator<E> it1,
			PositionList<E> list2, Position<E> cursor) 
	{
        if (!it1.hasNext() && cursor == null)
        	return true;
        else if (!it1.hasNext() || cursor == null)
        	return false;
        else
			return eqNull(it1.next(),cursor.element()) &&
		                igualesRec (it1, list2, list2.next(cursor));
	}
	private static <E> boolean eqNull(E e1, E e2) {
		return e1 == null && e2 == null || e1 != null && e1.equals(e2);
	}

	private static <E> void decimoElem(PositionList<E> l) {
		if (l != null && l.size() < 10) {
			return;
		}


	}
	public static <E> E maxIter2 (Iterable<E> col, Comparator<E> comp, Predicate<E> p)
	{
		Iterable<E> colFiltered = filterIter(col,p);
		Iterator<E> it = colFiltered.iterator();
		E max = it.next();
		while (it.hasNext())
		{
			E aux = it.next();
			if (comp.compare(aux,max) > 0)
				max = aux;
		}
		return max;
	}
	
	public static Iterable<Integer> getMaximosRelativosIterador (Iterable<Integer> list){
		PositionList<Integer> res = new NodePositionList<>();
		Iterator<Integer> it = list.iterator();
		if(!it.hasNext()) return res;
		Integer first = it.next(); 
		Integer second = it.next(); 
		Integer third = it.next();
		while(it.hasNext()) {
			if(second>=first && second>=third)
				res.addLast(second);
			first=second;
			second=third;
			third=it.next();
		}
		
		return res;
	}

	public static boolean esSerieFibonacci(Iterable<Integer> datos) {
		Iterator<Integer> it = datos.iterator();
		boolean esFib = true;
		if (!it.hasNext()) {
			return esFib;
		}
		Integer prev1 = it.next();
		if (!it.hasNext()) {
			return esFib;
		}
		Integer prev2 = it.next();
		while (it.hasNext() && esFib) {
			Integer current = it.next();
			esFib = current.equals(prev1 + prev2);
			prev1 = prev2;
			prev2 = current;
		}
		return esFib;
	}

}