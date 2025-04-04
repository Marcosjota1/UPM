package puntos;

public interface IProyeccion {
	
	// Para obtener la coordenada 1 a partir del punto en 3D
	double proyectar1(Punto3D p);
	
	// Para obtener la coordenada 2 a partir del punto en 3D
	double proyectar2(Punto3D p);

}
