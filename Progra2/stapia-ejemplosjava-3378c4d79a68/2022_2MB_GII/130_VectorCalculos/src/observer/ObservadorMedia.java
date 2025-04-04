package observer;

public class ObservadorMedia implements IObservador {

	Sujeto sujeto;
	double media;

	public ObservadorMedia(Sujeto sujeto) {
		this.sujeto = sujeto;
		sujeto.registrar(this);
		actualizar();
	}

	@Override
	public void actualizar() {
		double suma = 0;
		for ( int i = 0; i < sujeto.size(); ++i ) {
			suma += sujeto.get(i);
		}
		media = suma / sujeto.size();
	}
	
	public String toString() {
		return String.format("La media de mi vector es: %.3f", media);
	}
	

}
