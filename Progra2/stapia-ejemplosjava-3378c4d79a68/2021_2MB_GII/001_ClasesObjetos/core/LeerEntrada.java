package core;

import java.util.Scanner;
import geometria.Punto;

public class LeerEntrada {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while ( sc.hasNextLine() ) {
			String linea = sc.nextLine();
			Punto p = new Punto(linea);
			System.out.println("punto3d = (" + p.getX() + ", " + p.getY() + ")");			
		}
		sc.close();
	}

}
