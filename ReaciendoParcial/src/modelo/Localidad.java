package modelo;

public class Localidad {
	private int cod;
	private String nombre;
	private Provincia prov;
	public Localidad(int cod, String nombre, Provincia prov) {
		this.cod = cod;
		this.nombre = nombre;
		this.prov = prov;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Provincia getProv() {
		return prov;
	}
	public void setProv(Provincia prov) {
		this.prov = prov;
	}	
}
