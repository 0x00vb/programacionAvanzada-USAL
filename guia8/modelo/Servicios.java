package modelo;
import java.util.Calendar;

public class Servicios {
    private Calendar fecha;
    private int dniSolicitante;
    private int numeroAlojamiento;

    public Servicios(){}

    public Servicios(Calendar fecha, int dniSolicitante, int numeroAlojamiento){
        this.fecha = fecha;
        this.dniSolicitante = dniSolicitante;
        this.numeroAlojamiento = numeroAlojamiento;
    }

    public Calendar getFecha() {
        return this.fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public int getDniSolicitante() {
        return this.dniSolicitante;
    }

    public void setDniSolicitante(int dniSolicitante) {
        this.dniSolicitante = dniSolicitante;
    }

    public int getNumeroAlojamiento() {
        return this.numeroAlojamiento;
    }

    public void setNumeroAlojamiento(int numeroAlojamiento) {
        this.numeroAlojamiento = numeroAlojamiento;
    } 
}
