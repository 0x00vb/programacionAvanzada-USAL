package modelo;

import java.util.ArrayList;
import java.util.Calendar;

public class Pasajero {

	private int codigoID;
	private String nombre;
	private String apellido;
	private Camarote camarote;
	private ArrayList<Equipaje> equipaje;
	private Calendar FechaRegistro;
	private static int contador = 0;
	
	public Pasajero() {}
	
	public Pasajero(String nombre, String apellido, Camarote camarote) {
		this();
		this.codigoID = contador;
		this.nombre = nombre;
		this.apellido = apellido;
		this.camarote = camarote;
		contador++;
	}

	public int getCodigoID() {
		return codigoID;
	}

	public void setCodigoID(int codigoID) {
		this.codigoID = codigoID;
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

	public Camarote getCamarote() {
		return camarote;
	}

	public void setCamarote(Camarote camarote) {
		this.camarote = camarote;
	}
	
	public void addEquipaje(String tipo, double peso) {
		equipaje.add(new Equipaje(tipo, peso));
	}

	public ArrayList<Equipaje> getEquipaje(){
		return this.equipaje;
	}
	
	public Calendar getFechaRegistro() {
		return FechaRegistro;
	}

	public void setFechaRegistro(Calendar fechaRegistro) {
		FechaRegistro = fechaRegistro;
	}
	
	public double calcularCostoAdicional() {
		double x = 0.0;
		double pesoTotal = 0.0;
		for(Equipaje e : equipaje) {
			pesoTotal = e.getPeso();
		}
		
		if(pesoTotal > camarote.getPesoMax()) {
			double pesoExcedente = pesoTotal - camarote.getPesoMax();
			x = 10 * pesoExcedente;
		}
		return x;
	}
	
}
