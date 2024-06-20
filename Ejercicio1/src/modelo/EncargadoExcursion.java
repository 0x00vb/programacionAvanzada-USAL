package modelo;

public class EncargadoExcursion {
	private String nombre;
	private String email;
	
	public EncargadoExcursion() {}
	
	public EncargadoExcursion(String nombre, String email) {
		super();
		this.nombre = nombre;
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
