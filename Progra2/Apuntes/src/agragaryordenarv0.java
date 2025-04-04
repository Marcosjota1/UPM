
public class agragaryordenarv0 {
	public void agregarMonomio(Monomio monomio) {
		if (cantidadMonomios >= DELTA_LONGITUD) {
			System.out.println("No caben más monomios");
			return;
		}

		if (cantidadMonomios == 0) {  
			monomios[0] = monomio;
			cantidadMonomios++;
			return;
		}
		int i = 0;
		while (i < cantidadMonomios && monomios[i].getExponente() < monomio.getExponente()) {
			i++;
		}
		if (i == cantidadMonomios) {
			monomios[cantidadMonomios] = monomio;
		} else {
			for (int j = cantidadMonomios - 1; j >= i; j--) {
				monomios[j + 1] = monomios[j];
			}
			monomios[i] = monomio;
		}

		cantidadMonomios++;
		public void sumar(Monomio nuevo) {
	        boolean encontrado = false;
	        for (Monomio m : monomios) {
	            if (m.getExponente() == nuevo.getExponente()) {
	                m.sumar(nuevo);
	                encontrado = true;
	                break;
	            }
	        }
	        if (!encontrado) {
	  

}
