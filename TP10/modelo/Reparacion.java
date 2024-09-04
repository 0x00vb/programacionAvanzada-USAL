package modelo;

import java.util.ArrayList;
import java.util.Calendar;

public class Reparacion {
    private static int contador = 0;
    private int codigoReparacion;
    private String tipoReparacion;
    private double costo;
    private Calendar fechaIngreso;
    private Calendar fechaEntrega;  
    private boolean lavado;
    private boolean entregaRapida;
    private ArrayList<Repuesto> repuestos;
    
    public Reparacion() {}
    
    public Reparacion(String tipoReparacion, double costo, Calendar fechaIngreso, Calendar fechaEntrega, ArrayList<Repuesto> repuestos, boolean lavado, boolean entregaRapida) {
        contador++;
        this.codigoReparacion = contador;
        this.tipoReparacion = tipoReparacion;
        this.costo = costo;
        this.fechaIngreso = fechaIngreso;
        this.fechaEntrega = fechaEntrega;
        this.repuestos = repuestos;
        this.lavado = lavado;
        this.entregaRapida = entregaRapida;
    }

    public Reparacion(int id, String tipoReparacion, double costo, Calendar fechaIngreso, Calendar fechaEntrega, ArrayList<Repuesto> repuestos, boolean lavado, boolean entregaRapida) {
        this.codigoReparacion = id;
        this.tipoReparacion = tipoReparacion;
        this.costo = costo;
        this.fechaIngreso = fechaIngreso;
        this.fechaEntrega = fechaEntrega;
        this.repuestos = repuestos;
        this.lavado = lavado;
        this.entregaRapida = entregaRapida;
    }
    
    public int getCodigoReparacion() { return codigoReparacion; }
    public void setCodigoReparacion(int codigoReparacion) { this.codigoReparacion = codigoReparacion; }

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
    
    public boolean isLavado() {
        return this.lavado;
    }
    public void setLavado(boolean lavado) {
        this.lavado = lavado;
    }
    public boolean getLavado() {
        return this.lavado;
    }
    public boolean getEntregaRapida() {
        return this.entregaRapida;
    }
    public void setEntregaRapida(boolean entregaRapida) {
        this.entregaRapida = entregaRapida;
    }

    public double sumaAtributos(){
        return this.codigoReparacion + this.costo;
    }
}
