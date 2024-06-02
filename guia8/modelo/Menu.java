package modelo;

public class Menu {
    private String tipo;
    private String nombre;
    private double precio;
    private String[] ingredientes;
    private boolean compartir;

    public Menu(String tipo, String nombre, double precio, String[] ingredientes, boolean compartir) {
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

    public String[] getIngredientes() {
        return this.ingredientes;
    }

    public void setIngredientes(String[] ingredientes) {
        this.ingredientes = ingredientes;
    }
}
