package modelo;

import java.util.Calendar;

public class Mesa {
	private int numero;
	private Localidad localidad;
	private int cantVotosA;
	private int cantVotosB;
	private char tipo;
	private Calendar fecha;
	
	public Mesa() {}
	
	public Mesa(int numero, Localidad localidad, int cantVotosA, int cantVotosB, char tipo, Calendar fecha) {
		this.numero = numero;
		this.localidad = localidad;
		this.cantVotosA = cantVotosA;
		this.cantVotosB = cantVotosB;
		this.tipo = tipo;
		this.fecha = fecha;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public int getCantVotosA() {
		return cantVotosA;
	}

	public void setCantVotosA(int cantVotosA) {
		this.cantVotosA = cantVotosA;
	}

	public int getCantVotosB() {
		return cantVotosB;
	}

	public void setCantVotosB(int cantVotosB) {
		this.cantVotosB = cantVotosB;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
	
	public Calendar getFecha() {
		return this.fecha;
	}
	
}
