package canicas;

import util.ParGenerico;

public class NombreEdad extends ParGenerico<String, Integer> {
	@Override
	public String toString() {
		return String.format("Nombre: %s, Edad: %s", getFirst(), getSecond().toString());
	}
}
