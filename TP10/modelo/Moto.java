package modelo;

import java.util.Calendar;

public class Moto extends Vehiculo {
    private int cilindrada; 
    private boolean tieneSidecar;

    public Moto(){}

    public Moto( String patente, String marca, String modelo, int año, int cilindrada, boolean tieneSidecar, Cliente cliente, Calendar fechaCarga){
        super( patente,  marca,  modelo,  año, cliente, fechaCarga );
        this.cilindrada = cilindrada;
        this.tieneSidecar = tieneSidecar;
    }

    public int getCilindrada() { return cilindrada; }
    public void setCilindrada(int cilindrada) { this.cilindrada = cilindrada; }

    public boolean isTieneSidecar() { return tieneSidecar; }
    public void setTieneSidecar(boolean tieneSidecar) { this.tieneSidecar = tieneSidecar; }
}
