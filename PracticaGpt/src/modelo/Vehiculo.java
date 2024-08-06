package modelo;

public class Vehiculo {
	private int id;
	private String modelo;
	private char tipo;
	private int capacidad;
	private boolean gps;
	private boolean sillaBebes;
	private double tarifaD;
	private static int count = 1;
	public Vehiculo(String modelo, char tipo, int capacidad, boolean gps, boolean sillaBebes, double tarifaD) {
		this.id = count;
		this.modelo = modelo;
		this.tipo = tipo;
		this.capacidad = capacidad;
		this.tarifaD = tarifaD;
		this.gps = gps;
		this.sillaBebes = sillaBebes;
		count++;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public char getTipo() {
		return tipo;
	}
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public double getTarifaD() {
		return tarifaD;
	}
	public void setTarifaD(double tarifaD) {
		this.tarifaD = tarifaD;
	}
	public boolean isGps() {
		return gps;
	}
	public void setGps(boolean gps) {
		this.gps = gps;
	}
	public boolean isSillaBebes() {
		return sillaBebes;
	}
	public void setSillaBebes(boolean sillaBebes) {
		this.sillaBebes = sillaBebes;
	}
	
	
}
