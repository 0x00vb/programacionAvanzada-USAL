package modelo;

import java.util.ArrayList;

public class Menu {
    private String tipo;
    private String nombre;
    private double precio;
    private ArrayList<String> ingredientes;
    private boolean compartir;

    public Menu() {}
    
    public Menu(String tipo, String nombre, double precio, ArrayList<String> ingredientes, boolean compartir) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.precio = precio;
        this.ingredientes = ingredientes;
        this.compartir = compartir;
    }

    public double getPrecio() {
        return this.precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean getCompartir() {
        return this.compartir;
    }

    public void setCompartir(boolean compartir) {
        this.compartir = compartir;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getTarifaDiaria() {
        return precio;
    }

    public void setTarifaDiaria(double precio) {
        this.precio = precio;
    }

    public ArrayList<String> getIngredientes() {
        return this.ingredientes;
    }

    public void setIngredientes(ArrayList<String> ingredientes) {
        this.ingredientes = ingredientes;
    }
}
