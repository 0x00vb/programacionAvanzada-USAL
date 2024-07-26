package modelo;

public class Pasajero {
	private int id;
	private String nombre;
	private String apellido;
	private static int a = 0;
	
	public Pasajero(String nombre, String apellido) {
		this.id = a;
		this.nombre = nombre;
		this.apellido = apellido;
		a++;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

}
