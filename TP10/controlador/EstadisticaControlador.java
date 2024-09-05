package controlador;

import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Random;

import modelo.*;
import modelo.dao.EstadisticasJSON;

public class EstadisticaControlador {
    private VehiculoControlador vehiculoControlador;
    private ReparacionControlador reparacionControlador;
    private RepuestosControlador repuestoControlador;
    private ClienteControlador clienteControlador;
    private int arg;

    public EstadisticaControlador(){
        try {
            this.repuestoControlador = new RepuestosControlador();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.vehiculoControlador = new VehiculoControlador();
        this.reparacionControlador = new ReparacionControlador();
    }

    public EstadisticaControlador(int arg){
        this();
        this.arg = arg; 
    }


    public String estadistica1(){
        Calendar fecha6meses = Calendar.getInstance();
        fecha6meses.add(Calendar.MONTH, -6);
        for(Vehiculo v : vehiculoControlador.getVehiculos()){
            if(v.getFechaCarga().after(fecha6meses) && v instanceof Moto && ((Moto)v).isTieneSidecar()){
                return "" + ((Moto)v).getCilindrada();
            }
        }

        return "";
    }


    public String estadistica2(){
        String resp = "";
        Calendar fecha = Calendar.getInstance();
        for(Vehiculo v : vehiculoControlador.getVehiculos()){
            for(Reparacion r : v.getReparaciones()){
                if(r.getCodigoReparacion() != arg && r.getCosto() != arg){
                    resp =  "" + v.getPatente() + " " + v.getMarca() + " " + v.getModelo() + " " + v.getAño();
                }
            }
        }
        EstadisticasJSON.escribirJson(resp, fecha);
        return resp;
    }

    public int estadistica3(){
        Random rand = new Random();
        int x = rand.nextInt(1000);
        int a = 0;

        for(Vehiculo v : vehiculoControlador.getVehiculos()){
            if(v.sumaAtributos() > x){
                a++;
            }
        }
        for(Reparacion r : reparacionControlador.getReparaciones()){
            if(r.sumaAtributos() > x){
                a++;
            }
        }
        for(Repuesto r : repuestoControlador.getRepuestos()){
            if(r.sumaAtributos() > x){
                a++;
            }
        }
        for(Cliente c : clienteControlador.getClientes()){
            if(c.sumaAtributos() > x){
                a++;
            }
        }


        return a;
    }
}
