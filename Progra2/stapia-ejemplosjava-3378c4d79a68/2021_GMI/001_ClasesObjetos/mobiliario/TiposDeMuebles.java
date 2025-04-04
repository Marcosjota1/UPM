package mobiliario;

enum TiposDeMuebles {
	
	SILLA, // Este es el 0
	MESA;  // Este es el 1
	
	public static void main(String[] args) {
		System.out.println("Probando");
		
		TiposDeMuebles mueble1 = TiposDeMuebles.SILLA;
		System.out.println(mueble1);
		System.out.println(mueble1.ordinal());
		
		if ( mueble1 == TiposDeMuebles.SILLA ) {
			System.out.println("Esto es una silla");
		}
		
		System.out.println(TiposDeMuebles.values());
		
		for ( int i = 0; i < TiposDeMuebles.values().length; ++i) {
			System.out.println("values[" + i +"] = " + TiposDeMuebles.values()[i]);
		}
		
		TiposDeMuebles mueble2 = TiposDeMuebles.values()[1];
		System.out.println("Este es el 1: " + mueble2);
	}
}


