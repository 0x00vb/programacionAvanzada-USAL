package modelo;

import java.util.*;

public class Reserva {
	private int numReserva;
	private Vehiculo vehiculo;
	private Cliente cliente;
	private Calendar fechaInicio;
	private Calendar fechaFin;
	public Reserva(int numReserva, Vehiculo vehiculo, Cliente cliente, Calendar fechaInicio, Calendar fechaFin) {
		this.numReserva = numReserva;
		this.vehiculo = vehiculo;
		this.cliente = cliente;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	public int getNumReserva() {
		return numReserva;
	}
	public void setNumReserva(int numReserva) {
		this.numReserva = numReserva;
	}
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Calendar getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Calendar fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Calendar getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Calendar fechaFin) {
		this.fechaFin = fechaFin;
	}
}
