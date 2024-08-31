package modelo;

public class Auto extends Vehiculo {
    private int numeroPuertas;
    private String tipoCombustible;

    public Auto(String patente, String marca, String modelo, int año, int numeroPuertas, String tipoCombustible){
        super( patente,  marca,  modelo,  año );
        this.numeroPuertas = numeroPuertas;
        this.tipoCombustible = tipoCombustible;
    }
    public int getNumeroPuertas() { return numeroPuertas; }
    public void setNumeroPuertas(int numeroPuertas) { this.numeroPuertas = numeroPuertas; }

    public String getTipoCombustible() { return tipoCombustible; }
    public void setTipoCombustible(String tipoCombustible) { this.tipoCombustible = tipoCombustible; }
}
