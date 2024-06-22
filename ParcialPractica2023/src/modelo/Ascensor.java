package modelo;

import java.util.ArrayList;

public class Ascensor {
	private int codigo;
	private String direccionEdificio;
	private char tipo;
	private ArrayList<Reparacion> reparaciones;
	
	public Ascensor() {
		this.reparaciones = new ArrayList<>();
	}

	public Ascensor(int codigo, String direccionEdificio, char tipo) {
		super();
		this.codigo = codigo;
		this.direccionEdificio = direccionEdificio;
		this.tipo = tipo;
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

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public ArrayList<Reparacion> getReparaciones() {
		return reparaciones;
	}

	public void addReparacion(Reparacion reparacion) {
		this.reparaciones.add(reparacion);
	}
	
	
	
	
}
