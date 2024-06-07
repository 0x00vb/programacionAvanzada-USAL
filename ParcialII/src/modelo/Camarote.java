package modelo;

import java.util.ArrayList;

public class Camarote {
	private int numero;
	private int piso;
	private double pesoMax;
	private ArrayList<String> comodidades;
	
	public Camarote() {}
	
	public Camarote(int numero, int piso, int pesoMax, ArrayList<String> comodidades) {
		this.numero = numero;
		this.piso = piso;
		this.pesoMax = pesoMax;
		this.comodidades = comodidades;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
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
	
	public void setComodidades(ArrayList<String> comodidades) {
		this.comodidades = comodidades;
	}
	
	public ArrayList<String> getComodidades(){
		return this.comodidades;
	}
	
}
