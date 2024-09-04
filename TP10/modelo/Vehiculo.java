package modelo;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public abstract class Vehiculo {
    private String patente;
    private String marca;
    private String modelo;
    private int año;
    private Cliente cliente;
    private ArrayList<Reparacion> reparaciones;
    private Calendar fechaCarga;

    public Vehiculo(){
        this.reparaciones = new ArrayList<>();
    }
    
    public Vehiculo( String patente, String marca, String modelo, int año, Cliente cliente, Calendar fechaCarga ) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.cliente = cliente;
    }
    
    public String getPatente() { return patente; }
    public void setPatente(String patente) { this.patente = patente; }
    
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    
    public int getAño() { return año; }
    public void setAño(int año) { this.año = año; }
    
    public void setCliente(Cliente cliente){ this.cliente = cliente; }
    public Cliente getCliente() { return this.cliente; }
    
    public List<Reparacion> getReparaciones() { return reparaciones; }
    public void agregarReparación(String tipoReparacion, double costo, Calendar fechaIngreso, Calendar fechaEntrega, ArrayList<Repuesto> repuestos) {
        this.reparaciones.add( new Reparacion(tipoReparacion, costo, fechaIngreso, fechaEntrega, repuestos) );
    }
    
        public Calendar getFechaCarga() {
            return this.fechaCarga;
        }
    
        public void setFechaCarga(Calendar fechaCarga) {
            this.fechaCarga = fechaCarga;
        }
}
