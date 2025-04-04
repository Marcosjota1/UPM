package puntos;

public class ProyeccionIsometrica implements IProyeccion {

    final static double X_OFFSET = 1.0;
    final static double Y_OFFSET = - 1.1;

    @Override
    public double proyectar1(Punto3D p) {
        // Isometrica (sin colocar)
        double xp = Math.sqrt(2.0) *(p.x - p.y) / 2;
        // Isometrica (colocada)
        return xp + X_OFFSET;
    }

    @Override
    public double proyectar2(Punto3D p) {
        // Isometrica (sin colocar)
        double yp = Math.sqrt(2.0/3.0) * p.z - (p.x + p.y) / Math.sqrt(6.0);
        // Isometrica (colocada)
        return yp + Y_OFFSET; 
    }
    
}
