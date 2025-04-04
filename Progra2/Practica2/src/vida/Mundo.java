package vida;

import java.util.Scanner;

public class Mundo {
	public static final int TAM = 100;
	public Celula [][] mundo;
	
	public Mundo(Scanner sc) {
	mundo = Factoria.crearCelulas(sc,TAM);
	pasarVecinas();
    }
	public void pasarVecinas() {
	    final int n = mundo.length;     //filas
	    final int m = mundo[0].length;  //columnas

	    for (int i = 0; i < n; i++) {
	        for (int j = 0; j < m; j++) {
	            // Si la célula implementa la interfaz CambiarEstado, le pasamos las células vecinas
	            if (mundo[i][j] instanceof CambiarEstado) {
	                for (int dx = -1; dx <= 1; dx++) {
	                    for (int dy = -1; dy <= 1; dy++) {
	                        int x = (i + dx + n) % n;
	                        int y = (j + dy + m) % m;
	                        if (x != i || y != j) {
	                            ((CambiarEstado) mundo[i][j]).indicarVecina(mundo[x][y]);
	                        }
	                    }
	                }
	            }
	        }
	    }
	}

	public void dibujar() {
	    for(int i = 0; i < TAM; i++) {
	        for(int j = 0; j < TAM; j++) {
	            mundo[i][j].dibujar();
	        }
	    }
	}

	public void tick() {
	    // Recorremos todas las células del mundo
	    for (int i = 0; i < TAM; i++) {
	        for (int j = 0; j < TAM; j++) {
	            if (mundo[i][j] instanceof CambiarEstado) { // seleccionas las afectadas por la interfaz
	            			 ((CambiarEstado)mundo[i][j]).calcularNuevoEstado();
	            		 }
	            	 }
	            }
	           
	 for (int i = 0; i < TAM; i++) {
	        for (int j = 0; j < TAM; j++) {
	            if (mundo[i][j] instanceof CambiarEstado) {
	            			 ((CambiarEstado)mundo[i][j]).actualizar();
	            		 }
	            if(mundo[i][j] instanceof Tick) {
	            	((Tick) mundo[i][j]).tick();
	            
	            
	            	 }
	            }
	           
}
}
}


