package controlador;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import modelo.*;

public class ReparacionControlador {
    private ArrayList<Reparacion> reparaciones = null;
    private VehiculoControlador vehiculoControlador = new VehiculoControlador();
    private RepuestosControlador repuestoControlador;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public ReparacionControlador() {
        try {
            repuestoControlador = new RepuestosControlador();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error initializing RepuestosControlador", e);
        }
    }

    public int registrarReparacion(String patente, String descripcion, String fechaDevolucionStr, ArrayList<String> repuestosStr, String marcaRep, boolean lavado, boolean entregaRapida, double costo){

        Vehiculo v = vehiculoControlador.buscarVehiculo(patente);
        Calendar fechaIngreso = Calendar.getInstance();
        Calendar fechaDevolucion = Calendar.getInstance();
        ArrayList<Repuesto> repuestos = new ArrayList<>();
        try {
            fechaDevolucion.setTime( dateFormat.parse(fechaDevolucionStr) );
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for(Vehiculo ve : vehiculoControlador.getVehiculos()){
            for(Reparacion r : ve.getReparaciones()){
                if(r.getFechaEntrega() == fechaIngreso && r.getFechaEntrega() == fechaDevolucion){
                    return -1;
                }
            }
        }

        for(String val : repuestosStr){
            repuestos.add(repuestoControlador.buscarRepuesto(val, marcaRep));
        }

        v.agregarReparación(descripcion, costo, fechaIngreso, fechaDevolucion, repuestos, lavado, entregaRapida);
        return 0;
    }  

    public Reparacion buscarReparacion(int codReparacion){
        for(Reparacion r : reparaciones){
            if(r.getCodigoReparacion() == codReparacion){
                return r;
            }
        }
        return null;
    }

    public void eliminarReparacion(int codigo) {
        Reparacion reparacionAEliminar = buscarReparacion(codigo);
        if (reparacionAEliminar != null) {
            reparaciones.remove(reparacionAEliminar);
        }
    }
    public ArrayList<Reparacion> getReparaciones(){
        return this.reparaciones;
    }
}
