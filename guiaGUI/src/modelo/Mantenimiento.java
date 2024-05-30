package modelo;
import java.util.*;

public class Mantenimiento {
	private char tipo;
	private Calendar fecha;
	private double costo;

	public Mantenimiento(char tipo, Calendar fecha, double costo) {
		this.tipo = tipo;
		this.fecha = fecha;
		this.costo = costo;
	}
	
	public char getTipo() {
		return tipo;
	}
	
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
	
	public Calendar getFecha() {
		return fecha;
	}
	
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	
	public double getCosto() {
		return costo;
	}
	
	public void setCosto(double costo) {
		this.costo = costo;
	}
	
}
