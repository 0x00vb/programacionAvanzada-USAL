package modelo;

public class Repuesto {
    private String nombre;
    private int codigo;
    private double costo;

    public Repuesto() {}

    public Repuesto(String nombre, int codigo, double costo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.costo = costo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public double getCosto() {
        return costo;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}