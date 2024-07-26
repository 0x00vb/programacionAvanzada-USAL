package modelo;
import java.util.*;

public class Ascensor {
	private int codigo;
	private String direccionEdificio;
	private String tipo;
	private ArrayList<Reparacion> reparaciones;
	
	public Ascensor() {}
	
	public Ascensor(int codigo, String direccionEdificio, String tipo) {
		this.codigo = codigo;
		this.direccionEdificio = direccionEdificio;
		this.tipo = tipo;
		this.reparaciones = new ArrayList<Reparacion>();
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDireccionEdificio() {
		return direccionEdificio;
	}
	public void setDireccionEdificio(String direccionEdificio) {
		this.direccionEdificio = direccionEdificio;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public ArrayList<Reparacion> getReparaciones() {
		return reparaciones;
	}
	public void setReparaciones(ArrayList<Reparacion> reparaciones) {
		this.reparaciones = reparaciones;
	}	
	
	public void addReparacion(Tecnico t, Calendar fecha, boolean luces, boolean botonera, boolean infra) {
		reparaciones.add(new Reparacion(this.reparaciones.size(), t, fecha, luces, botonera, infra));
	}
	
}
