package modelo;

import java.util.*;

public class Estadia {
	private Pasajero pasajero;
	private Camarote camarote;
	private Calendar fechaRegistro;
	private ArrayList<Equipaje> equipaje;
	public Estadia(Pasajero pasajero, Camarote camarote, Calendar fechaRegistro) {
		this.pasajero = pasajero;
		this.camarote = camarote;
		this.fechaRegistro = fechaRegistro;
		this.equipaje = new ArrayList<>();
	}
	public Pasajero getPasajero() {
		return pasajero;
	}
	public void setPasajero(Pasajero pasajero) {
		this.pasajero = pasajero;
	}
	public Camarote getCamarote() {
		return camarote;
	}
	public void setCamarote(Camarote camarote) {
		this.camarote = camarote;
	}
	public Calendar getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Calendar fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public ArrayList<Equipaje> getEquipaje() {
		return equipaje;
	}
	public void setEquipaje(ArrayList<Equipaje> equipaje) {
		this.equipaje = equipaje;
	}
	public void addEquipaje(int cod, char t, double peso) {
		equipaje.add( new Equipaje(cod, t, peso) );
	}
}
