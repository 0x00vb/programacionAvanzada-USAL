package modelo;

public class Cochera {
	private int numero;
	private boolean disponible;
	private String ubicacion;
	private Tarifa informacionTarifa;
		
	public Cochera(int numero, boolean disponible,  String ubicacion, Tarifa tarifa) {
		this.numero = numero;
		this.disponible = disponible;
		this.informacionTarifa = tarifa;
		this.ubicacion = ubicacion;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	public Tarifa getTarifa() {
		return informacionTarifa;
	}
	public void setTarifa(Tarifa tarifa) {
		this.informacionTarifa = tarifa;
	}
	public String getUbicacion() {
		return this.ubicacion;
	}
	
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
}
