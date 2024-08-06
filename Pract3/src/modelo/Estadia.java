package modelo;
import java.util.*;

public class Estadia {
	private int id;
	private Cochera cochera;
	private Vehiculo vehiculo;
	private int horasE;
	private Calendar fecha;
	private boolean dejaLlaves;
	private boolean pagoAd;
	private static int cont = 0;
	
	public Estadia(Cochera cochera, Vehiculo vehiculo, Calendar fecha, int horasE, boolean dejaLlaves, boolean pagoAd) {
		this.id = cont;
		this.cochera = cochera;
		this.vehiculo = vehiculo;
		this.fecha = fecha;
		this.dejaLlaves = dejaLlaves;
		this.pagoAd = pagoAd;
		this.horasE = horasE;
		cont++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cochera getCochera() {
		return cochera;
	}

	public void setCochera(Cochera cochera) {
		this.cochera = cochera;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public boolean isDejaLlaves() {
		return dejaLlaves;
	}

	public void setDejaLlaves(boolean dejaLlaves) {
		this.dejaLlaves = dejaLlaves;
	}

	public boolean isPagoAd() {
		return pagoAd;
	}

	public void setPagoAd(boolean pagoAd) {
		this.pagoAd = pagoAd;
	}
	

	public int getHorasE() {
		return horasE;
	}

	public void setHorasE(int horasE) {
		this.horasE = horasE;
	}
	
	public double calcularCosto() {
		double t = horasE * this.cochera.getTarifa();
		if(this.pagoAd) {
			t -= t * 0.05;
		}
		return t;
	}
}
