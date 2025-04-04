package upm.aed.arboles;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.swing.text.Position;

import es.upm.aedlib.tree.GeneralTree;
import es.upm.aedlib.tree.LinkedGeneralTree;

public class PruebaConFunciones {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Function<Integer,Integer> cuadrado = new AlCuadrado();
		Function<Integer,Double> cuadradoR = new AlCuadradoR();
		Function<Integer,Double> comp= cuadrado.andThen(cuadradoR);
		Function<Integer,Double> comp2= cuadradoR.compose(cuadrado.compose(cuadrado));


		
//		Function<Integer,List<Double>> cuadradoRLista = new AlCuadradoR();

		// cuadrado : Z Z -> Z
		// cuadrado (x) = Math.pow(x,2)
		Predicate<Integer> mayor = new MayorTamanoQueInteger(57);
		// mayor : Z -> Bool
		// mayor (n) = x ->  x > n
		
		// 
		
//		List<List<Integer>> sublistas=aplicar();
		
		List<Integer> lista = new ArrayList<Integer>();
		for (int i=0; i < 10; i++)
              lista.add(3*i);
		
		PuntoInt p = new PuntoInt (4);
		//System.out.println(aplicar(x -> p.multiplica(x),lista));
		//System.out.println(aplicar(cuadradoR,lista));
		System.out.println(filter(mayor,lista));
		System.out.println(filter(new MayorTamanoQueInteger(45),lista));
		System.out.println(filter( x -> (x > 89),lista));
		
		//System.out.println(acum(lista,(x,y)-> x+y,0));
		System.out.println (listPos(lista, 5));
		List<Integer> posL = listPos(lista,lista.size());
		System.out.println (posL);
		//System.out.println (aplicarT(pos -> subLista(lista,pos),posL));

		GeneralTree<Integer> tree = new LinkedGeneralTree<>();
			tree.addRoot(1);
	
			/*
			 * Position<Integer> n2 = tree.addChildLast(tree.root(), 2); Position<Integer>
			 * n3 = tree.addChildLast(tree.root(), 3); Position<Integer> n10 =
			 * tree.addChildLast(tree.root(),10); Position<Integer> n11 =
			 * tree.insertSiblingBefore(n10, 11); Position<Integer> n12 =
			 * tree.insertSiblingAfter(n10, 12);
			 * 
			 * Position<Integer> n4 = tree.addChildLast(n2, 4); Position<Integer> n6 =
			 * tree.addChildLast(n2, 6); Position<Integer> n5 = tree.insertSiblingBefore(n6,
			 * 5);
			 * 
			 * Position<Integer> n7 = tree.addChildLast(n3, 7); Position<Integer> n8 =
			 * tree.insertSiblingAfter(n7, 8);
			 * 
			 * Position<Integer> n9 = tree.addChildLast(n8, 9); tree.set(n8, 18);
			 */

			System.out.println(aplicarT(x -> 2*x,tree));
			System.out.println(aplicarL(x -> 2*x,lista));
		
//		System.out.println(acum(lista,(x,y)->  , false));

		
		
	  }
	
	
	 
	  
	  public static <E>List<E> subLista (List<E> l, int n)
	  {
		  List<E> resultado = new ArrayList<E>();
		  for (int i=0; i < n; i++)
			  resultado.add(i, l.get(i));
		  return resultado;
	  }
	  
	  public static <E>List<Integer> listPos (List<E> l, int n)
	  {
		  List<Integer> resultado = new ArrayList<Integer>();
		  for (int i=0; i < n; i++)
			  resultado.add(i, i+1);
		  return resultado;
	  }
	
		public static <E,F> GeneralTree<F> aplicarT ( Function<E,F> f, GeneralTree<E> tree)
		{
			GeneralTree<F> result = new LinkedGeneralTree<F>();
			if (tree == null || tree.isEmpty())
				return result;
			result.addRoot(f.apply(tree.root().element()));
			
			//mapRec (tree, result, tree.root(), result.root(), f);
			return result;
		}
		/*
		 * public static <E,F> void mapRec ( GeneralTree<E> tree, GeneralTree<F> result,
		 * //Position<E> v, //Position<F> w, Function<E,F> f) { if (!tree.isExternal(v))
		 * // for(Position<E> child : tree.children(v)) { // mapRec(tree, result, child,
		 * // result.addChildFirst(w, f.apply(child.element())),f); } }
		 */
		
	  public static <E,F> List<F> aplicarL (Function<E,F> f, List<E> lista)
	  {
		  // aplicar f <x1,x2,...,xn>
		  // aplicar(f, <x1,x2,... ,xn>) = <, ..., f(xn)>
		  List<F> resultado = new ArrayList<F>();
		  for (E x : lista)
			  resultado.add(f.apply(x));
		  return resultado;
	  }
	  
	  public static <E> List<E> filter (Predicate<E> p, List<E> lista)
	  {
		  // aplicar f <x1,x2,...,xn>
		  // aplicar(f, <x1,x2,... ,xn>) = <f(x1), ..., f(xn)>
		  List<E> resultado = new ArrayList<E>();
		  for (E x : lista)
			  if (p.test(x))
				  resultado.add(x);
		  return resultado;
	  }
	  


//	  sublistas<1,2,3> = <<1>, <1,2>, <1,2,3>> 
//	  aplicar <x1,..xn> f = <f(x1), ... , f(xn)>
//	  sublistas<1,2,3> = <<1>, <2>, <3>> 
//	  // col = <x1,x2,... ,xn> + E x E -> E   x1+x2 ... +xn
	  //       <x          xn> == (x1==x) || (x2==x) ...... (xn==x)
//	

	  
	  public static <E> E acum (List<E> l, BiFunction<E,E,E> opBin, E neutro)
	  {
		  E val = neutro;
		  for (E x : l)
			  val = opBin.apply(x,val);
		  return val; 
	  }
	  
	  
	  public static Function<Integer,Integer> construyeFun (int x)
	  {
		  return new AlCuadrado ();
	  }
	

}
