package modelo;

import java.util.ArrayList;

public class Camarote {
	private int num;
	private int piso;
	private double pesoMax;
	private ArrayList<String> comodidades;
	
	public Camarote() {
		
	}
	
	public Camarote(int num, int piso, double pesoMax, ArrayList<String> comodidades) {
		this.num = num;
		this.piso = piso;
		this.pesoMax = pesoMax;
		this.comodidades = comodidades;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public double getPesoMax() {
		return pesoMax;
	}

	public void setPesoMax(double pesoMax) {
		this.pesoMax = pesoMax;
	}

	public ArrayList<String> getComodidades() {
		return comodidades;
	}

	public void setComodidades(ArrayList<String> comodidades) {
		this.comodidades = comodidades;
	}
	
	
}
