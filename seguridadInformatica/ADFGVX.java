
import java.util.ArrayList;
import java.util.Hashtable;

public class ADFGVX {
	static char[] todo = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
	public static int getIndex(char val, char[] perm) {
		Boolean encontrado = false;
		int ret = -1;
		for(int x = 0; x < perm.length && !encontrado; x++) {
			//System.out.println(val+"=="+perm[x]);
			if(val == perm[x]) {

				encontrado = true;
				ret = x;
			}	
		}
		return ret;

		
	}
	
	public static String getABCD(String palabara) {
		String salida = "";
		for(char carcater : palabara.toCharArray()) {
			int num = getIndex(Character.toLowerCase(carcater),todo);
			if(num!=-1) {
				salida += carcater;
				//System.out.print(carcater);
				if((((int) Character.toLowerCase(carcater))-96)<=10) {
					salida+= ((((int) Character.toLowerCase(carcater))-96)%10);
				}
				//System.out.print(index%10);
				todo[num]=' ';

			}

		}
		for(char carcater : todo) {
			if(carcater!=' ') {
				salida+=carcater;
				if((((int) Character.toLowerCase(carcater))-96)<=10) {
					salida+= ((((int) Character.toLowerCase(carcater))-96)%10);
				}
			}
		}
		return salida;
	}
	
	static void printTabla(String tab) {
		char[] realTab = tab.toCharArray();
		char[] letrero = {'A','D','F','G','V','X'};
		System.out.print("\t| ");
		for(char carac : letrero) {
			System.out.print(carac+"\t");
		}
		System.out.println();
		for(char carac : letrero) {
			System.out.print("_\t");
		}
		System.out.println();
		int offset = 0;
		int index = 0;
		for(char carac : realTab) {
			if(offset%letrero.length==0) {
				System.out.println();
				System.out.print(letrero[index]+"\t| ");
				index++;
			}
				System.out.print(carac+"\t");
			offset++;
		}
		System.out.println();
		
	}
}
