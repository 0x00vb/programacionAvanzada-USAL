package modelo;

public class Tratamiento {
    private String nombre;
    private double precio;
    private boolean inyectable;
    private int cantMaxSesiones;
    private char tipoTratamiento;

    public Tratamiento(String nombre, double precio, boolean inyectable, int cantMaxSesiones, char tipoTratamiento){
        this.nombre = nombre;
        this.precio = precio;
        this.inyectable = inyectable;
        this.cantMaxSesiones = cantMaxSesiones;
        this.tipoTratamiento = tipoTratamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean getInyectable() {
        return inyectable;
    }

    public void setInyectable(boolean inyectable) {
        this.inyectable = inyectable;
    }

    public int getCantMaxSesiones() {
        return cantMaxSesiones;
    }

    public void setCantMaxSesiones(int cantMaxSesiones) {
        this.cantMaxSesiones = cantMaxSesiones;
    }

    public char getTipoTratamiento() {
        return tipoTratamiento;
    }

    public void setTipoTratamiento(char tipoTratamiento) {
        this.tipoTratamiento = tipoTratamiento;
    }    
}