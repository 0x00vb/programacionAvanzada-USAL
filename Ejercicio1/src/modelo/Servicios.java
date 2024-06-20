package modelo;
import java.util.Calendar;

public class Servicios {
    private Calendar fecha;
    private Huesped huesped;

    public Servicios(){}

    public Servicios(Calendar fecha, Huesped huesped){
        this.fecha = fecha;
        this.huesped = huesped;
    }

    public Calendar getFecha() {
        return this.fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public void setHuesped(Huesped h) {
    	this.huesped = h;
    }
    
    public Huesped getHuesped() {
    	return this.huesped;
    }
}
