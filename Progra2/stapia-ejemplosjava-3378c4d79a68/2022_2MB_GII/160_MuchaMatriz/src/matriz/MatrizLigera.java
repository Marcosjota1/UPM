package matriz;

public class MatrizLigera implements IMatriz {
	
	IMatriz matriz;
	int i_modificado;
	int j_modificado;
	short valor;
	
	public MatrizLigera(IMatriz matriz, int i, int j, short valor) {
		this.matriz = matriz;
		i_modificado = i;
		j_modificado = j;
		this.valor = valor;
	}

	@Override
	public short get(int i, int j) {
		if ( i_modificado == i && j_modificado == j ) {
			return valor;
		} else {
			return matriz.get(i, j);
		}
	}

	@Override
	public IMatriz set(int i, int j, short valor) {
		// TODO Auto-generated method stub
		return null;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		for ( int i = 0; i < Matriz.SIZE; ++i ) {
			for ( int j = 0; j < Matriz.SIZE; ++j ) {
				str.append(get(i,j));
				str.append(" ");
			}
			str.append("\n");
		}
		return str.toString();
	}
	

}
