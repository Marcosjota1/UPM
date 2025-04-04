package claseIndexedLists;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import es.upm.aedlib.Position;
import es.upm.aedlib.indexedlist.ArrayIndexedList;
import es.upm.aedlib.indexedlist.IndexedList;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;

public class Prueba {
	public static int sumaIList(IndexedList<Integer>col) {
		int suma=0;
		for(int i=0; i<col.size(); i++) {
			suma += col.get(i);
	}
		return suma;
}


public static void main(String[] args) {
	// TODO Auto-generated method stub

	IndexedList<Integer> l = new ArrayIndexedList<Integer>();
	l.add (l.size(), 2);
	l.add (l.size(), 4);
	l.add (l.size(), 3);
	l.add (l.size(), 22);
	IndexedList<String> s = new ArrayIndexedList<String>();
	s.add (s.size(), "Maria");
	s.add (s.size(), "Luisa Gomez");
	s.add (s.size(), "Maria");
	s.add (s.size(), "Mari Juli");
	
	IndexedList<Complejo> m = new ArrayIndexedList<Complejo>();
	System.out.println(
			sumaIList(l, x -> x % 2==0, 
			             y -> Math.sqrt(y),
			             0.0,
			             (a,b)-> a+b));
	System.out.println(
			sumaIList(l, x -> x % 2==0, 
			             y -> Math.sqrt(y),
			             1.0,
			             (a,b)-> a*b));
	
	System.out.println(
			sumaIList(s, x -> x.toString().contains("Maria"), 
			             y -> y,
			             "",
			             (a,b)-> a+b));

	
	System.out.println(
			existIList(
				filterIList(
				    mapIList(l, x-> 2*x),
				    x -> x > 0),
				x -> x % 2 == 0)
                    
			);
	
	
	System.out.println(
			existIList(l, y -> y+1, x -> x % 2 == 0));
	System.out.println(
			allMatchIList(l, x -> x % 2 == 0));
	System.out.println(
			allMatchIList(l, y -> y+1, x -> x % 2 == 0));
	
	
}

public static <E,F> F sumaIList 
				(IndexedList<E> col, 
				 Predicate<E> pred,
				 Function<E,F> f,
				 F neuter,
				 BiFunction<F,F,F> sum)
{
    F res = neuter;
    for (int i=0; i< col.size(); i++)
    	if (pred.test(col.get(i)))
    		res = sum.apply(res, f.apply(col.get(i)));
    return res;
}

public static <E> boolean existIList
		(IndexedList<E> col, 
		 Predicate<E> pred)
{
	boolean exist = false;
	int i = 0;
	while (!exist && i < col.size() )
		if (pred.test(col.get(i)))
			exist = true;
		else
			i++;
	return exist;
}

public static <E,F> boolean existIList
(
		IndexedList<E> col, 
		Function<E,F> f ,
		Predicate<F> pred)
{
	boolean exist = false;
	int i = 0;
	while (!exist && i < col.size() )
		if (pred.test(f.apply(col.get(i))))
			exist = true;
		else
			i++;
	return exist;
}

public static <E> boolean allMatchIList
(IndexedList<E> col, 
		Predicate<E> pred)
{
	boolean allMatch = true;
	int i = 0;
	while (allMatch && i < col.size() )
		if (!pred.test(col.get(i)))
			allMatch = false;
		else
			i++;
	return allMatch;
}

public static <E,F> boolean allMatchIList
(
		IndexedList<E> col, 
		Function<E,F> f ,
		Predicate<F> pred)
{
	boolean allMatch = true;
	int i = 0;
	while (allMatch && i < col.size() )
		if (!pred.test(f.apply(col.get(i))))
			allMatch = false;
		else
			i++;
	return allMatch;
}

public static <E,F> IndexedList<F> mapIList
(
		IndexedList<E> col,
		Function <E,F> f)
{
	IndexedList<F> res = new ArrayIndexedList<F>();
	for (int i=0; i < col.size(); i++)
		res.add (res.size(), f.apply(col.get(i)));
	return res;
	
}
public static <E> IndexedList<E> filterIList
(
		IndexedList<E> col,
		Predicate <E> pred)
{
	IndexedList<E> res = new ArrayIndexedList<E>();
	for (int i=0; i < col.size(); i++)
		if (pred.test(col.get(i)))
		res.add (res.size(), col.get(i));
	return res;
	
}

public static <E> int countIList
(
		IndexedList<E> col,
		Predicate <E> pred
)
{
	int res = 0;
	for (int i=0; i < col.size(); i++)
		if (pred.test(col.get(i)))
		   res++;
	return res;
	
}

public static <E,F> E maxIList
(
		IndexedList<E> col,
		Function <E,F> f,
		Comparator <F> comp
)
{
	E res = col.get(0);
	F max = f.apply(col.get(0));
	for (int i=1; i < col.size(); i++) 
		if (comp.compare(max, f.apply(col.get(i))) < 0)
		{
			max = f.apply(col.get(i));
			res = col.get(i);
		}
	return res;
}

public static <E extends Comparable<E>> PositionList<E> getMaximosRelativos(PositionList<E> list) {
	PositionList<E> res = new NodePositionList<>();
	if (list.size() < 3) {
		throw new IllegalArgumentException();
	}
	Position<E> anterior = list.first();
	Position<E> cursor = list.next(anterior);
	Position<E> siguiente = list.next(cursor);
	while (siguiente != null) {
		if (cursor.element().compareTo(anterior.element()) >= 0
				&& cursor.element().compareTo(siguiente.element()) >= 0) {
			res.addLast(cursor.element());
		}
		anterior = cursor;
		cursor = siguiente;
		siguiente = list.next(cursor);
	}
	return res;
}


}
