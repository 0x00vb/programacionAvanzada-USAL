package modelo;

public class Tarifa {
	private String tipo;
	private int cantidadMaxima;
	private double precio;
	
	public Tarifa() {}
	
	public Tarifa(String tipo, int cantidadMaxima, double precio) {
		this.tipo = tipo;
		this.cantidadMaxima = cantidadMaxima;
		this.precio = precio;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getCantidadMaxima() {
		return cantidadMaxima;
	}
	public void setCantidadMaxima(int cantidadMaxima) {
		this.cantidadMaxima = cantidadMaxima;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
}
