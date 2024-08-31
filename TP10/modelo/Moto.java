package modelo;

public class Moto extends Vehiculo {
    private int cilindrada; 
    private boolean tieneSidecar;

    public Moto(){}

    public Moto( String patente, String marca, String modelo, int año, int cilindrada, boolean tieneSidecar){
        super( patente,  marca,  modelo,  año );
        this.cilindrada = cilindrada;
        this.tieneSidecar = tieneSidecar;
    }

    public int getCilindrada() { return cilindrada; }
    public void setCilindrada(int cilindrada) { this.cilindrada = cilindrada; }

    public boolean isTieneSidecar() { return tieneSidecar; }
    public void setTieneSidecar(boolean tieneSidecar) { this.tieneSidecar = tieneSidecar; }
}
