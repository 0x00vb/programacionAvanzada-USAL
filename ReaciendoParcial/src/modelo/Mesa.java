package modelo;

import java.util.Calendar;

public class Mesa {
	private int numero;
	private Localidad loc;
	private int cantVotosA;
	private int cantVotosB;
	private char tipo;
	private Calendar fecha;
	public Mesa() {}
	public Mesa(int numero, Localidad loc, int cantVotosA, int cantVotosB, char tipo, Calendar fecha) {
		this.numero = numero;
		this.loc = loc;
		this.cantVotosA = cantVotosA;
		this.cantVotosB = cantVotosB;
		this.tipo = tipo;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Localidad getLoc() {
		return loc;
	}
	public void setLoc(Localidad loc) {
		this.loc = loc;
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
}
