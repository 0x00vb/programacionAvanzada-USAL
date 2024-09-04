package modelo;

public class Repuesto {
    private String nombre;
    private int codigo;
    private double costo;
    private String marca;
    
    public Repuesto() {}
    
    public Repuesto(String nombre, int codigo, double costo, String marca) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.costo = costo;
        this.marca = marca;
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
    

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double sumaAtributos(){
        return this.codigo + this.costo;
    }

}