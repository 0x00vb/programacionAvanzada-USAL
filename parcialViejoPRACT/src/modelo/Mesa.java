package modelo;

import java.util.Calendar;

public class Mesa {
	private int num;
	private Localidad localidad;
	private int votosA;
	private int votosB;
	private char tipo;
	private Calendar fecha;
	public Mesa(int num, Localidad localidad, int votosA, int votosB, char tipo, Calendar fecha) {
		this.num = num;
		this.localidad = localidad;
		this.votosA = votosA;
		this.votosB = votosB;
		this.tipo = tipo;
		this.fecha = fecha;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	public int getVotosA() {
		return votosA;
	}
	public void setVotosA(int votosA) {
		this.votosA = votosA;
	}
	public int getVotosB() {
		return votosB;
	}
	public void setVotosB(int votosB) {
		this.votosB = votosB;
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
}
