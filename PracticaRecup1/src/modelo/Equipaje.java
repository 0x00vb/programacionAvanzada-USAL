package modelo;

public class Equipaje {
	private int id;
	private char tipo;
	private double peso;
	
	public Equipaje(int id, char tipo, double peso) {
		this.id = id;
		this.tipo = tipo;
		this.peso = peso;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}	
}
