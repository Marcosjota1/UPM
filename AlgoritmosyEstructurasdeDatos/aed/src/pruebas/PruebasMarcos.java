package pruebas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
public class PruebasMarcos { public static void main(String[] args) {
/*
 * public class PruebasMarcos { public static void main(String[] args) {
 * ArrayList <String> foods= new ArrayList<String>(); 
 * foods.add("pizza");
 * foods.add("ice"); 
 * foods.add("chicken");
 *  foods.add("chicken");
 * 
 * Iterator<String> it = foods.iterator(); 
 * while(it.hasNext())
 * System.out.println(it.next());
 */
		//--------------------------------------------------------------------------------
 /*public class PruebasMarcos { public static void main(String[] args) {
		HashSet <String> foods2 = new HashSet<String>();
		foods2.add("pizza");
		foods2.add("ice");
		foods2.add("chicken");
		foods2.add("chicken");

		Iterator<String> it2 = foods2.iterator();
		while(it2.hasNext())
			System.out.println(it2.next());
			
	*/	
	
	ArrayList<Integer> nums = new ArrayList<Integer>();
	nums.add(33);
	nums.add(-73);
	nums.add(50);
	nums.add(109);
	
	Iterator<Integer> it = nums.iterator();
	while(it.hasNext()) {
		int i = it.next();
		if(i<50)
			it.remove();
	}
	System.out.println(nums);

	}
}
	
