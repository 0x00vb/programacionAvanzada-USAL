package modelo;

import java.util.*;

public class Excursion{
	private String nombre;
	private int diaSemana;
	private EncargadoExcursion encargado;
	private int duracion;
	private double costo;
	private int cantMin;
	private int cantMax;
	
	public Excursion(String nombre, int diaSemana, EncargadoExcursion encargado, int duracion, double costo,int cantMin, int cantMax) {
		this.nombre = nombre;
		this.diaSemana = diaSemana;
		this.encargado = encargado;
		this.duracion = duracion;
		this.costo = costo;
		this.cantMin = cantMin;
		this.cantMax = cantMax;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(int diaSemana) {
		this.diaSemana = diaSemana;
	}

	public EncargadoExcursion getEncargado() {
		return encargado;
	}

	public void setEncargado(EncargadoExcursion encargado) {
		this.encargado = encargado;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public int getCantMin() {
		return cantMin;
	}

	public void setCantMin(int cantMin) {
		this.cantMin = cantMin;
	}

	public int getCantMax() {
		return cantMax;
	}

	public void setCantMax(int cantMax) {
		this.cantMax = cantMax;
	}
	
}
