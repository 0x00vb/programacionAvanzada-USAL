package modelo;

import java.util.ArrayList;
import java.util.Calendar;

public class Estadia {
	private int id;
	private Pasajero pasajero;
	private Camarote camarote;
	private ArrayList<Equipaje> equipaje;
	private Calendar fechaRegistro;
	private static int c = 0;
	
	public Estadia(Pasajero pasajero, Camarote camarote, ArrayList<Equipaje> equipaje) {
		this.id = c;
		this.pasajero = pasajero;
		this.camarote = camarote;
		this.equipaje = equipaje;
		this.fechaRegistro = Calendar.getInstance();
		c++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public ArrayList<Equipaje> getEquipaje() {
		return equipaje;
	}

	public void setEquipaje(ArrayList<Equipaje> equipaje) {
		this.equipaje = equipaje;
	}

	public Calendar getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Calendar fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	public double calcularCostoAdicional() {
		double costo = 0.0;
		double pesoTotal = 0.0;
		for(Equipaje e : equipaje) {
			pesoTotal += e.getPeso();
		}
		double pesoExcedido = pesoTotal - this.camarote.getPesoMax();
		if(pesoExcedido > 0) {
			costo = pesoExcedido * 10;
		}
			
			return costo;
	}
	
}
