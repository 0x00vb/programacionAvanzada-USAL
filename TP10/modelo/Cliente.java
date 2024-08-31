package modelo;

import java.util.ArrayList;

public class Cliente {
    private String nombre;
    private String apellido;
    private int dni;
    private String telefono;
    private String direccion;
    private ArrayList<Vehiculo> vehiculos;

    public Cliente(){
        this.vehiculos = new ArrayList<>();
    }

    public Cliente(String nombre, String apellido, int dni, String telefono, String direccion){
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

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }


    public ArrayList<Vehiculo> getVehiculos() { return this.vehiculos; }
    public void setVehiculos(ArrayList<Vehiculo> vehiculos) { this.vehiculos = vehiculos; }
    public void addAuto(String patente, String marca, String modelo, int año, int numeroPuertas, String tipoCombustible){
        this.vehiculos.add( new Auto(patente, marca, modelo, año, numeroPuertas, tipoCombustible) );
    }

    public void addMoto( String patente, String marca, String modelo, int año, int cilindrada, boolean tieneSidecar){
        this.vehiculos.add( new Moto(patente, marca, modelo, año, cilindrada, tieneSidecar) );
    }
}
