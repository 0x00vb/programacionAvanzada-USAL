package modelo;

public class Provincia {
	private int cod;
	private String nombre;
	public Provincia(int cod, String nombre) {
		this.cod = cod;
		this.nombre = nombre;
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
}
