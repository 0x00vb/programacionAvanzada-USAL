package modelo;
import java.util.*;

public class Auto {
	private String patente;
	private int anio;
	private double precioCompra;
	private List<Mantenimiento> mantenimientos;
	
	public Auto() {}
	
	public Auto(String patente, int anio, double precioCompra) {
		this.patente = patente;
		this.anio = anio;
		this.precioCompra = precioCompra;
		this.mantenimientos = new ArrayList<Mantenimiento>();
	}
	
	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}

    public List<Mantenimiento> getMantenimientos() {
        return mantenimientos;
    }

    public void agregarMantenimiento(char tipo, Calendar fecha, double costo) {
        mantenimientos.add(new Mantenimiento(tipo, fecha, costo));
    }
	
    public double calcularCostoTotal() {
    	double costoTotal = this.precioCompra;
    	
    	for(Mantenimiento m : mantenimientos) {
    		costoTotal += m.getCosto();
    	}
    	
    	return costoTotal;
    }
    
}
