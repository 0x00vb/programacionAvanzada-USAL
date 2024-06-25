package modelo;

import java.util.*;


public class InscripcionExcursiones extends Servicios{
	private Excursion excursion;
	private ArrayList<Huesped> huespedes = new ArrayList<Huesped>();

	public InscripcionExcursiones() {}
	
	public InscripcionExcursiones(Calendar fecha, Huesped huesped, Excursion excursion, ArrayList<Huesped> huespedes) {
		super(fecha, huesped);
		this.excursion = excursion;
		this.huespedes = huespedes;
	}
	
	public Excursion getExcursion() {
		return excursion;
	}

	public void setExcursion(Excursion excursion) {
		this.excursion = excursion;
	}

	public void addHuesped(Huesped h) {
		this.huespedes.add(h);
	}
	
	public void setHuespedes(ArrayList<Huesped> huespedes) {
		this.huespedes = huespedes;
	}
	
	public ArrayList<Huesped> getHuespedes(){
		return this.huespedes;
	}
}
