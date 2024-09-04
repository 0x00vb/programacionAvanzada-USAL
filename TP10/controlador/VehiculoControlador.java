package controlador;
import java.util.ArrayList;
import java.util.Calendar;

import modelo.*;

public class VehiculoControlador {
    private ArrayList<Vehiculo> vehiculos = null;
    private int arg;
    
    public VehiculoControlador(){}

    public VehiculoControlador(int arg){
        this();
        this.arg = arg;
    }

    public ArrayList<String> buscarVehiculoPorCliente(int dni){
        ArrayList<String> lista = new ArrayList<>();
        for(Vehiculo v : vehiculos){
            if(v.getCliente().getDNI() == dni){
                lista.add(v.getPatente());
            }
        }
        return lista;
    }

    public Vehiculo buscarVehiculo(String patente){
        for(Vehiculo v : vehiculos){
            if(v.getPatente().equals(patente)){
                return v;
            }
        }
        return null;
    }

    public ArrayList<Vehiculo> getVehiculos(){ return vehiculos; }

}
