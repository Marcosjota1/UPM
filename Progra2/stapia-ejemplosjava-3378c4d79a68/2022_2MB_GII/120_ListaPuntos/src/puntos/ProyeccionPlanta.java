package puntos;

public class ProyeccionPlanta implements IProyeccion {

	@Override
	public double proyectar1(Punto3D p) {
		return p.x;
	}

	@Override
	public double proyectar2(Punto3D p) {
		return -p.y-0.1;
	}
}
