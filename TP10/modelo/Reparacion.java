package modelo;

import java.util.ArrayList;
import java.util.Calendar;

public class Reparacion {
    private String codigoReparacion;
    private String tipoReparacion;
    private double costo;
    private Calendar fechaIngreso;
    private Calendar fechaEntrega;
    private ArrayList<Repuesto> repuestos;

    public Reparacion() {}

    public Reparacion(String codigoReparacion, String tipoReparacion, double costo, Calendar fechaIngreso, Calendar fechaEntrega, ArrayList<Repuesto> repuestos) {
        this.codigoReparacion = codigoReparacion;
        this.tipoReparacion = tipoReparacion;
        this.costo = costo;
        this.fechaIngreso = fechaIngreso;
        this.fechaEntrega = fechaEntrega;
        this.repuestos = repuestos;
    }
    
    public String getCodigoReparacion() { return codigoReparacion; }
    public void setCodigoReparacion(String codigoReparacion) { this.codigoReparacion = codigoReparacion; }

    public String getTipoReparacion() { return tipoReparacion; }
    public void setTipoReparacion(String tipoReparacion) { this.tipoReparacion = tipoReparacion; }

    public double getCosto() { return costo; }
    public void setCosto(double costo) { this.costo = costo; }

    public Calendar getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(Calendar fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public Calendar getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(Calendar fechaEntrega) { this.fechaEntrega = fechaEntrega; }

    public ArrayList<Repuesto> getRepuestos() { return repuestos; }
    public void setRepuestos(ArrayList<Repuesto> repuestos) { this.repuestos = repuestos; }

}
