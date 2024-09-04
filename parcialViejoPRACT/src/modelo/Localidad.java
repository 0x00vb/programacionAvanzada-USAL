package modelo;

public class Localidad {
	private int cod;
	private Provincia prov;
	private String nombre;
	public Localidad(int cod, Provincia prov, String nombre) {
		this.cod = cod;
		this.prov = prov;
		this.nombre = nombre;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public Provincia getProv() {
		return prov;
	}
	public void setProv(Provincia prov) {
		this.prov = prov;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
