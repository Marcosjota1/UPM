package personajes;

public class Rango {
	
	public static int calcular(TipoDeAtaque tipo) {
		if ( tipo == TipoDeAtaque.A_DISTANCIA ) {
			return 150;
		} else if ( tipo == TipoDeAtaque.CUERPO_A_CUERPO ) {
			return 30;
		}
		return 0;
	}

}
