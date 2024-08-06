package modelo;

public class Vehiculo {
	private int patente;
	private char tipo;
	public Vehiculo(int patente, char tipo) {
		this.patente = patente;
		this.tipo = tipo;
	}
	public int getPatente() {
		return patente;
	}
	public void setPatente(int patente) {
		this.patente = patente;
	}
	public char getTipo() {
		return tipo;
	}
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
}
