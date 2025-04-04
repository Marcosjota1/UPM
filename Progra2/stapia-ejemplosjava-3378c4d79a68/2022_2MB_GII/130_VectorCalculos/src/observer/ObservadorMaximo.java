package observer;

public class ObservadorMaximo implements IObservador {
	
	Sujeto sujeto;
	double maximo;

	public ObservadorMaximo(Sujeto sujeto) {
		this.sujeto = sujeto;
		sujeto.registrar(this);
		actualizar();
	}

	@Override
	public void actualizar() {
		maximo = sujeto.get(0);
		for (int i = 1; i < sujeto.size(); ++i) {
			if ( maximo < sujeto.get(i) ) {
				maximo = sujeto.get(i);
			}
		}
		System.out.println("Maximo comprobado (actualizado):" + toString());
	}

	public String toString() {
		return String.format("El maximo de mi vector es: %.3f", maximo);
	}
}
