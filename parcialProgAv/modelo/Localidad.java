package modelo;
import java.util.ArrayList;

import modelo.*;

public class Localidad {
    private int codigoProvincia;
    private int codigoLocalidad;
    private String nombre;

    public Localidad(int codigoProvincia, int codigoLocalidad, String nombre) {
        this.codigoProvincia = codigoProvincia;
        this.codigoLocalidad = codigoLocalidad;
        this.nombre = nombre;
    }

    public int getCodigoProvincia() {
        return this.codigoProvincia;
    }

    public void setCodigoProvincia(int codigoProvincia) {
        this.codigoProvincia = codigoProvincia;
    }

    public int getCodigoLocalidad() {
        return this.codigoLocalidad;
    }

    public void setCodigoLocalidad(int codigoLocalidad) {
        this.codigoLocalidad = codigoLocalidad;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
