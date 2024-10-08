package modelo;
import java.util.ArrayList;

public class Cliente {
    private String nombre;
    private String apellido;
    private int dni;
    private int telefono;
    private String direccion;

    public Cliente(String nombre, String apellido, int dni, int telefono, String direccion){
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public int getDNI() { return dni; }
    public void setDNI(int dni) { this.dni = dni; }

    public int getTelefono() { return telefono; }
    public void setTelefono(int telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public double sumaAtributos(){
        return dni + telefono; 
    }
}
