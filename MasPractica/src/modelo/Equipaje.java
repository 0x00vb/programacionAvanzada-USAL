package modelo;

public class Equipaje {
	private int codigo;
	private char tipo;
	private double peso;
	public Equipaje(int codigo, char tipo, double peso) {
		this.codigo = codigo;
		this.tipo = tipo;
		this.peso = peso;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
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
