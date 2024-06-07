package modelo;

import java.util.Calendar;

public class Estadia implements ICalculable{
	private Calendar fecha;
	private Vehiculo vehiculo;
	private Cochera cochera;
	private int cantHoras;
	private boolean pagoAdelantado;
	private boolean dejaLlaves;
	
	public Estadia() {}

	public Estadia(Calendar fecha, Vehiculo vehiculo, Cochera cochera, int cantHoras, boolean pago, boolean llaves) {
		this.fecha = fecha;
		this.vehiculo = vehiculo;
		this.cochera = cochera;
		this.cantHoras = cantHoras;
		this.pagoAdelantado = pago;
		this.dejaLlaves = llaves;
	}
	
	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Cochera getCochera() {
		return cochera;
	}

	public void setCochera(Cochera cochera) {
		this.cochera = cochera;
	}

	public void setCantHoras(int a) {
		this.cantHoras = a;
	}

	public int getCantHoras() {
		return this.cantHoras;
	}

	public boolean isPagoAdelantado() {
		return pagoAdelantado;
	}

	public void setPagoAdelantado(boolean pagoAdelantado) {
		this.pagoAdelantado = pagoAdelantado;
	}

	public boolean isDejaLlaves() {
		return dejaLlaves;
	}

	public void setDejaLlaves(boolean dejaLlaves) {
		this.dejaLlaves = dejaLlaves;
	}
	
	public double calcularCostoTotal() {
		double costoTotal = 0.0f;
		costoTotal = this.cantHoras * this.cochera.getTarifa().getPrecio();
		if(this.pagoAdelantado) {
			costoTotal -= (costoTotal * 0.05);
		}
		
		return costoTotal;
	}
}
