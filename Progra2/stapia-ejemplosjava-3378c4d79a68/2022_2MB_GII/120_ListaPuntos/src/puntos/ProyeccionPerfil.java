package puntos;

public class ProyeccionPerfil implements IProyeccion {

	@Override
	public double proyectar1(Punto3D p) {
		return -p.y -0.1;
	}

	@Override
	public double proyectar2(Punto3D p) {
		return p.z+0.1;
	}

}
