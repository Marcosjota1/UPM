import java.util.ArrayList;
import java.util.Hashtable;

public class PortaBellaso {
	
	static char[] filaComun = {'a','b','c','d','e','f','g','h','i','j','k','l','m'};
	
	static char[] AB = {'n','o','p','q','r','s','t','u','v','w','x','y','z'};
	static char[] CD = {'z', 'n','o','p','q','r','s','t','u','v','w','x','y'};
	static char[] EF = {'y', 'z', 'n','o','p','q','r','s','t','u','v','w','x'};
	static char[] GH = {'x', 'y', 'z', 'n','o','p','q','r','s','t','u','v','w'};
	static char[] IJ = {'w', 'x', 'y', 'z', 'n','o','p','q','r','s','t','u','v'};
	static char[] KL = {'v', 'w', 'x', 'y', 'z', 'n','o','p','q','r','s','t','u'};
	static char[] MN = {'u', 'v', 'w', 'x', 'y', 'z', 'n','o','p','q','r','s','t'};
	static char[] OP = {'t', 'u', 'v', 'w', 'x', 'y', 'z', 'n','o','p','q','r','s'};
	static char[] QR = {'s', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'n','o','p','q','r'};
	static char[] ST = {'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'n','o','p','q'};
	static char[] UV = {'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'n','o','p'};
	static char[] WX = {'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'n','o'};
	static char[] YZ = {'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'n'};
	
	public static void main(String[] args) {
		String texto = args[0];
		String clave = args[1];
		char[] ordenada = texto.toCharArray();
		int index = 0;
		int bigIndex = 0;
		
		for (char caracter : clave.toCharArray()) {
 			System.out.println(caracter);
 			System.out.println(filaComun);
 			switch(Character.toLowerCase(caracter)) {
			case 'a':
			case 'b':
				System.out.println(AB);
				break;
			case 'c':
			case 'd':
				System.out.println(CD);
				break;
			case 'e':
			case 'f':
				System.out.println(EF);
				break;
			case 'g':
			case 'h':
				System.out.println(GH);
				break;
			case 'i':
			case 'j':
				System.out.println(IJ);
				break;
			case 'k':
			case 'l':
				System.out.println(KL);
				break;
			case 'm':
			case 'n':
				System.out.println(MN);
				break;
			case 'o':
			case 'p':
				System.out.println(OP);
				break;
			case 'q':
			case 'r':
				System.out.println(QR);
				break;
			case 's':
			case 't':
				System.out.println(ST);
				break;
			case 'u':
			case 'v':
				System.out.println(UV);
				break;
			case 'w':
			case 'x':
				System.out.println(WX);
				break;
			case 'y':
			case 'z':
				System.out.println(YZ);
				break;
		}
		}	
		
		for (char caracter : ordenada) {
 			System.out.print(caracter+"\t");
		}	
		System.out.println();
		for (char caracter : ordenada) { 
			char let = getLetra(clave.toCharArray()[index],ordenada[bigIndex]);
 			System.out.print(let+"\t");
 			if(caracter!=' ') {
 				index++;
 			}
			
			bigIndex++;
			if(index==clave.length()) {
				index = 0;
			}
		}
		System.out.println();
	}

	private static char getLetra(char c, char d) {
		switch(Character.toLowerCase(c)) {
			case 'a':
			case 'b':
				return getRealLetra(filaComun,AB,Character.toLowerCase(d));
			case 'c':
			case 'd':
				return getRealLetra(filaComun,CD ,Character.toLowerCase(d));
			case 'e':
			case 'f':
				return getRealLetra(filaComun,EF ,Character.toLowerCase(d));
			case 'g':
			case 'h':
				return getRealLetra(filaComun,GH ,Character.toLowerCase(d));
			case 'i':
			case 'j':
				return getRealLetra(filaComun,IJ ,Character.toLowerCase(d));
			case 'k':
			case 'l':
				return getRealLetra(filaComun,KL ,Character.toLowerCase(d));
			case 'm':
			case 'n':
				return getRealLetra(filaComun,MN ,Character.toLowerCase(d));
			case 'o':
			case 'p':
				return getRealLetra(filaComun,OP ,Character.toLowerCase(d));
			case 'q':
			case 'r':
				return getRealLetra(filaComun,QR ,Character.toLowerCase(d));
			case 's':
			case 't':
				return getRealLetra(filaComun,ST ,Character.toLowerCase(d));
			case 'u':
			case 'v':
				return getRealLetra(filaComun,UV ,Character.toLowerCase(d));
			case 'w':
			case 'x':
				return getRealLetra(filaComun,WX ,Character.toLowerCase(d));
			case 'y':
			case 'z':
				return getRealLetra(filaComun,YZ ,Character.toLowerCase(d));
		}
		return ' ';
	}
	


	private static char getRealLetra(char[] filaComun2, char[] aB2, char d) {
		int num = getIndex(filaComun2,d);
		if(num!=-1) {
			return aB2[num];
		}else {
			num = getIndex(aB2,d);
			if(num!=-1) {
				return filaComun2[num];
			}else {
				return ' ';
			}

		}
	}

	private static int getIndex(char[] filaComun2, char d) {
		boolean encontrado = false;
		int ret = -1;
		//System.out.println("Buscando a "+d+" en "+filaComun2);
		for(int x = 0; x < filaComun2.length && !encontrado; x++) {
			if(filaComun2[x]==d) {
				encontrado = true;
				ret = x;
			}
			
		}
		//System.out.println("Esta en "+ret);
		return ret;
	}
	
	
}
