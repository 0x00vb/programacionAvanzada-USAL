package modelo;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public abstract class Vehiculo {
    private String patente;
    private String marca;
    private String modelo;
    private int año;
    private ArrayList<Reparacion> reparaciones;

    
    public Vehiculo(){
        this.reparaciones = new ArrayList<>();
    }
    
    public Vehiculo( String patente, String marca, String modelo, int año ) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
    }

    public String getPatente() { return patente; }
    public void setPatente(String patente) { this.patente = patente; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getAño() { return año; }
    public void setAño(int año) { this.año = año; }

    public List<Reparacion> getReparaciones() { return reparaciones; }
    public void agregarReparación(String codigoReparacion, String tipoReparacion, double costo, Calendar fechaIngreso, Calendar fechaEntrega, ArrayList<Repuesto> repuestos) {
        this.reparaciones.add( new Reparacion(codigoReparacion, tipoReparacion, costo, fechaIngreso, fechaEntrega, repuestos) );
    }
}
