package matutino;

enum QueTomar { CAFE, TE, CHOCOLATE }

public class Yo {
	
	private boolean soyPersona = false;
	
	public void tomar(QueTomar queTomar) {
		if ( queTomar == QueTomar.CAFE ) {
			soyPersona = true;
		}
	}
	
	public void eresPersona() {
		if ( soyPersona ) {
			System.out.println("Soy persona");
		} else {
			System.out.println("No soy persona, me falta un café");
		}
	}
	
	public static Yo levantarse() {
		return new Yo();
	}
	
	public static void main(String[] args) {
		Yo yo = Yo.levantarse();
		yo.eresPersona();
		yo.tomar(QueTomar.CHOCOLATE);
		yo.eresPersona();
		yo.tomar(QueTomar.CAFE);
		yo.eresPersona();
	}

}
