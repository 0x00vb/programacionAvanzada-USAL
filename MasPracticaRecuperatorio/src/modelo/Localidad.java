package modelo;

public class Localidad {
	private int codigo;
	private String nombre;
	private Provincia provincia;
	
	public Localidad() {}
	
	public Localidad(int codigo, String nombre, Provincia provincia) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.provincia = provincia;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
}
