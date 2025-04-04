package matriz;

import java.util.*;

public class Matriz implements IMatriz {
	
	static final int SIZE = 10;
	private short[] matriz = new short[SIZE*SIZE];
	private static final Random random = new Random();
	
	public Matriz() {
		for ( int i = 0; i < SIZE; ++i ) {
			for ( int j = 0; j < SIZE; ++j ) {
				matriz[i*SIZE+j] = (short) (random.nextInt(21) - 10);
			}			
		}
	}
	
	@Override
	public short get(int i, int j) {
		return matriz[i*SIZE+j];
	}
	
	@Override
	public IMatriz set(int i, int j, short valor) {
		return new MatrizLigera(this, i, j, valor);
	}	
	
	private Matriz(Matriz aCopiar) {
		matriz = Arrays.copyOf(aCopiar.matriz, SIZE*SIZE);
	}
	
	public Matriz setSinMinimizar(int i, int j, short valor) {
		Matriz nueva = new Matriz(this);
		nueva.matriz[i*SIZE+j] = valor;
		return nueva;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		for ( int i = 0; i < SIZE; ++i ) {
			for ( int j = 0; j < SIZE; ++j ) {
				str.append(matriz[i*SIZE+j]);
				str.append(" ");
			}
			str.append("\n");
		}
		return str.toString();
	}

}
