package puntos;

public class ProyeccionAlzado implements IProyeccion {

	@Override
	public double proyectar1(Punto3D p) {
		return p.x;
	}

	@Override
	public double proyectar2(Punto3D p) {
		return p.z+0.1;
	}

}
