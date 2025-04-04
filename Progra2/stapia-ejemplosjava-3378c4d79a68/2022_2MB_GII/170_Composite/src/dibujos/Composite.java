package dibujos;

import java.util.ArrayList;

public class Composite extends ArrayList<IDibujo> implements IDibujo {

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		for ( IDibujo dib : this ) {
			dib.dibujar();
		}
	}

	@Override
	public IDibujo mover(double incX, double incY) {
		Composite resultado = new Composite();
		for ( int i = 0; i < this.size(); ++i ) {
			IDibujo dib = this.get(i);
			IDibujo movido = dib.mover(incX, incY);
			resultado.add(movido);
		}
		return resultado;
	}

	/* Como atributo :
	ArrayList<IDibujo> lista; 
	
	public void add(IDibujo e) {
		lista.add(e);
	} 
	 */
}
