import puntos.*;
import stdlib.StdDraw;

public class ConVistas {
	
	public static void main(String[] args) {
		if ( false ) {
			StdDraw.setXscale(-1.2, 1.2);
			StdDraw.setYscale(-1.2, 1.2);
			
			Pieza pieza = new Pieza("PuntosCaras.txt","Caras.txt");
			Vista alzado = new Vista(pieza, new ProyeccionAlzado());
			Vista planta = new Vista(pieza, new ProyeccionPlanta());
			Vista perfil = new Vista(pieza, new ProyeccionPerfil());
			alzado.pintar();
			planta.pintar();
			perfil.pintar();			
		} else {
			StdDraw.setXscale(-2, 2);
			StdDraw.setYscale(-2, 2);
			
			Pieza pieza = new Pieza("PuntosCaras.txt","Caras.txt");
			Vista isometrica = new Vista(pieza, new ProyeccionIsometrica());
			isometrica.pintar();
		}
	}

}
