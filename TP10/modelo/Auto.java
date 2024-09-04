package modelo;

import java.util.Calendar;

public class Auto extends Vehiculo {
    private int numeroPuertas;
    private String tipoCombustible;

    public Auto(String patente, String marca, String modelo, int año, int numeroPuertas, String tipoCombustible, Cliente cliente, Calendar fechaCarga){
        super( patente,  marca,  modelo,  año, cliente, fechaCarga);
        this.numeroPuertas = numeroPuertas;
        this.tipoCombustible = tipoCombustible;
    }
    
    public int getNumeroPuertas() { return numeroPuertas; }
    public void setNumeroPuertas(int numeroPuertas) { this.numeroPuertas = numeroPuertas; }

    public String getTipoCombustible() { return tipoCombustible; }
    public void setTipoCombustible(String tipoCombustible) { this.tipoCombustible = tipoCombustible; }
}
