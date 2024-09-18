package controlador;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import modelo.*;
import modelo.dao.ReparacionesSQL;
import modelo.dao.ReparacionesTXT;

public class ReparacionControlador {
    private ArrayList<Reparacion> reparaciones = ReparacionesTXT.leerReparaciones();
    private VehiculoControlador vehiculoControlador = new VehiculoControlador();
    private RepuestosControlador repuestoControlador;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private ReparacionesSQL reparacionesSQL = new ReparacionesSQL();

    public ReparacionControlador() {
        repuestoControlador = new RepuestosControlador();
    }

    public int registrarReparacion(String patente, String descripcion, String fechaDevolucionStr, ArrayList<String> repuestosStr, boolean lavado, boolean entregaRapida, double costo){

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
            repuestos.add(repuestoControlador.buscarRepuesto(val));
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
            try {
                ReparacionesTXT.eliminarReparacion(codigo);
            } catch (IOException e) {
                e.printStackTrace();
            }
            reparacionesSQL.eliminarReparacion(codigo);
        }
    }
    public ArrayList<Reparacion> getReparaciones(){
        return this.reparaciones;
    }
}
