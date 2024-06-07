package modelo;

public class Vehiculo {
	private int patente;
	private String tipo;
	
	public Vehiculo(int patente, String tipo) {
		super();
		this.patente = patente;
		this.tipo = tipo;
	}

	public int getPatente() {
		return patente;
	}

	public void setPatente(int patente) {
		this.patente = patente;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
