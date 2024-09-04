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

    public Vehiculo buscarVehiculo(String patente){
        for(Vehiculo v : vehiculos){
            if(v.getPatente().equals(patente)){
                return v;
            }
        }
        return null;
    }

    public ArrayList<Vehiculo> getVehiculos(){ return vehiculos; }

    public String estadistica1(){
        Calendar fecha6meses = Calendar.getInstance();
        fecha6meses.add(Calendar.MONTH, -6);
        for(Vehiculo v : vehiculos){
            if(v.getFechaCarga().after(fecha6meses) && v instanceof Moto && ((Moto)v).isTieneSidecar()){
                return "" + ((Moto)v).getCilindrada();
            }
        }

        return "";
    }

    public String estadistica2(){
        for(Vehiculo v : vehiculos){
            for(Reparacion r : v.getReparaciones()){
                if(r.getCodigoReparacion() != arg && r.getCosto() != arg){
                    return "" + v.getPatente() + " " + v.getMarca() + " " + v.getModelo() + " " + v.getAño();
                }
            }
        }

        return "";
    }
}
