package core;

import java.util.*;

import geometria.Punto;

public class LeerArchivo {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Punto p;
		
		while ( sc.hasNextLine() ) {
			String line = sc.nextLine();
			p = new Punto(line);
			System.out.println("(" + p.getX() + ", " + p.getY() + ")");
		}
		
		sc.close();
	}
}
