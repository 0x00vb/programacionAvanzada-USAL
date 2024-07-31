package modelo;

import java.util.*;

public class Prestamo {
	private int dniCliente;
	private Calendar fechaInicial;
	private Calendar fechaRegreso;
	public Prestamo(int dniCliente, Calendar fechaInicial, Calendar fechaRegreso) {
		super();
		this.dniCliente = dniCliente;
		this.fechaInicial = fechaInicial;
		this.fechaRegreso = fechaRegreso;
	}
	public int getDniCliente() {
		return dniCliente;
	}
	public void setDniCliente(int dniCliente) {
		this.dniCliente = dniCliente;
	}
	public Calendar getFechaInicial() {
		return fechaInicial;
	}
	public void setFechaInicial(Calendar fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	public Calendar getFechaRegreso() {
		return fechaRegreso;
	}
	public void setFechaRegreso(Calendar fechaRegreso) {
		this.fechaRegreso = fechaRegreso;
	}
}
