package modelo;

import java.util.*;

public class Evento {
	private String id;
	private String nombre;
	private Calendar fecha;
	private String ubicacion;
	private char tipo;
	private int limiteEntradas;
	public Evento(String nombre, Calendar fecha, String ubicacion, char tipo, int limiteEntradas) {
		this.id = nombre + fecha.getTimeInMillis();
		this.nombre = nombre;
		this.fecha = fecha;
		this.ubicacion = ubicacion;
		this.tipo = tipo;
		this.limiteEntradas = limiteEntradas;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Calendar getFecha() {
		return fecha;
	}
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public char getTipo() {
		return tipo;
	}
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
	public int getLimiteEntradas() {
		return limiteEntradas;
	}
	public void setLimiteEntradas(int limiteEntradas) {
		this.limiteEntradas = limiteEntradas;
	}
}
