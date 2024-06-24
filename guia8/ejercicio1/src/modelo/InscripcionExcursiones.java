package modelo;

import java.util.Calendar;

public class InscripcionExcursiones extends Servicios{
	private Excursion excursion;

	public InscripcionExcursiones(Calendar fecha, Huesped huesped, Excursion excursion) {
		super(fecha, huesped);
		this.excursion = excursion;
	}
	
	public Excursion getExcursion() {
		return excursion;
	}

	public void setExcursion(Excursion excursion) {
		this.excursion = excursion;
	}

	
}
