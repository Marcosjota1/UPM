package pruebas;

import java.util.HashMap;
import java.util.Map;

public class PruebasMarcos2 {
	public static void main(String[] args) {
		Map<Integer,String> lakers = new HashMap<Integer, String>();
		lakers.put(6, "James");
		lakers.put(0, "Shaq");
		lakers.put(14, "Pippen");
		
		//lakers.remove(0); //Para remove necesitas la key, primer valor
		//lakers.clear();
		lakers.replace(6, "Marcos");
		//System.out.println(lakers);
		//System.out.println(lakers.containsKey(0));
		for(Map.Entry m: lakers.entrySet())
			//if(m.getKey()<5)
				System.out.println(m.getKey()+" "+m.getValue());


	}
}