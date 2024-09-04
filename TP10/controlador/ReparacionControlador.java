package controlador;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import modelo.*;
import vista.ConsultaVista;

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
}
