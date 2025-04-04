package util;

public class Par {
	private String peque;
	private int numeroCanicas;
	
	public String getPeque() {
		return peque;
	}
	public void setPeque(String peque) {
		this.peque = peque;
	}
	public int getNumeroCanicas() {
		return numeroCanicas;
	}
	public void setNumeroCanicas(int numeroCanicas) {
		this.numeroCanicas = numeroCanicas;
	}
	public String toString() {
		return String.format("%s: %d", peque, numeroCanicas);
	}
}
