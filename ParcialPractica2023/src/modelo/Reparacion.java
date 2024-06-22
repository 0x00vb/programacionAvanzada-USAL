package modelo;

import java.util.Calendar;

public class Reparacion {
	private static int contador = 0;
	private int id;
	private Calendar fecha;
	private double costo;
	private boolean cambioLuces;
	private boolean cambioBotonera;
	private boolean mejoraEstructura;
	private Tecnico tecnico;

	public Reparacion() {
		this.id = contador;
		contador++;
	}

	public Reparacion(Calendar fecha, double costo, boolean cambioLuces, boolean cambioBotonera,
			boolean mejoraEstructura, Tecnico tecnico) {
		this.id = contador;
		this.fecha = fecha;
		this.costo = costo;
		this.cambioLuces = cambioLuces;
		this.cambioBotonera = cambioBotonera;
		this.mejoraEstructura = mejoraEstructura;
		this.tecnico = tecnico;
		contador++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public boolean isCambioLuces() {
		return cambioLuces;
	}

	public void setCambioLuces(boolean cambioLuces) {
		this.cambioLuces = cambioLuces;
	}

	public boolean isCambioBotonera() {
		return cambioBotonera;
	}

	public void setCambioBotonera(boolean cambioBotonera) {
		this.cambioBotonera = cambioBotonera;
	}

	public boolean isMejoraEstructura() {
		return mejoraEstructura;
	}

	public void setMejoraEstructura(boolean mejoraEstructura) {
		this.mejoraEstructura = mejoraEstructura;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}
	
	
	
}
