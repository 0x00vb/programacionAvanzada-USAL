package modelo;

import java.util.ArrayList;
import java.util.List;

public class Provincia {
    private int codigo;
    private String nombre;
    private ArrayList<Localidad> localidades;


    public Provincia(){}

    public Provincia(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.localidades = new ArrayList<Localidad>();
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Localidad> getLocalidades() {
        return localidades;
    }

    public void addLocalidad(Localidad localidad) {
        localidades.add(localidad);
    }
}
