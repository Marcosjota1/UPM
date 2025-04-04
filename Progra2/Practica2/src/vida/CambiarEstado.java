package vida;

public interface CambiarEstado {
	/* Añade la célula c a los vecinos de la célula */
	void indicarVecina(Celula c);

	/*
	 * Calcula el nuevo estado de la célula a partir del estado (viva/muerta) de las
	 * células vecinas. Se calcula y se guarda, pero no se cambia (todavía)
	 */
	void calcularNuevoEstado();

	/* Modifica el estado de la célula al calculado previamente */
	void actualizar();

}
