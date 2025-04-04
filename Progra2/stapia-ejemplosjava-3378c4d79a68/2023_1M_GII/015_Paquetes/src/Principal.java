
import saludos.Hola;
import datos.*;

public class Principal {
	public static void main(String[] args) {
		Hola h = new Hola();
		h.diAlgo();
		saludos.Adios a = new saludos.Adios();
		
		Datos d = new Datos();
		// d.a = 4;
		// d.b = 5;
		System.out.println(d);
	}
}
