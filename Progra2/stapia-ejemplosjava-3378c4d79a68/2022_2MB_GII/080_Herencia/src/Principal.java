import java.util.Arrays;

import geometria.*;

public class Principal {
	
	public static void main(String[] args) {
		Punto3D p = new Punto3D(3.4, 5.6, 8.3);
		double[] array2 = p.getCoordenadas();
		System.out.println(Arrays.toString(array2));
		System.out.println("z: " + p.getCoordenada(2));
		array2[0] = 999.9;
		Punto2D q = p;
		array2 = q.getCoordenadas();
		System.out.println(Arrays.toString(array2));
		// Esto no lo puedo hacer:
		// System.out.println("z: " + q.getCoordenada(2));
		Cuadrado cuadrado = new Cuadrado(3.0001);
		System.out.println(cuadrado);
		System.out.println("Area: " + cuadrado.area());
		ParalelogramoRecto paralelogramo = cuadrado;
		System.out.println(paralelogramo);
		paralelogramo = new ParalelogramoRecto(1.4,5.5);
		// cuadrado = paralelogramo; No se puede  
		System.out.println(paralelogramo);
	}
}
