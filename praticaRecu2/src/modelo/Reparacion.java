package modelo;
import java.util.*;

public class Reparacion {
	private int id;
	private Tecnico tecnico;
	private Calendar fecha;
	private boolean cambioLuces;
	private boolean cambioBotonera;
	private boolean mejoraInfraestructura;
	
	public Reparacion(int id, Tecnico tecnico, Calendar fecha, boolean cambioLuces, boolean cambioBotonera, boolean mejoraInfraestructura) {
		this.id = id;
		this.tecnico = tecnico;
		this.fecha = fecha;
		this.cambioBotonera = cambioBotonera;
		this.cambioLuces = cambioLuces;
		this.mejoraInfraestructura = mejoraInfraestructura;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
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

	public boolean isMejoraInfraestructura() {
		return mejoraInfraestructura;
	}

	public void setMejoraInfraestructura(boolean mejoraInfraestructura) {
		this.mejoraInfraestructura = mejoraInfraestructura;
	}



}
