package controlador;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import modelo.Repuesto;
import modelo.dao.RepuestosTXT;

public class RepuestosControlador {
    private ArrayList<Repuesto> repuestos;

    public RepuestosControlador() throws FileNotFoundException {
        this.repuestos = RepuestosTXT.leerRepuestos();
    }

    public Repuesto buscarRepuesto(String nombre){
        for(Repuesto r : repuestos){
            if(r.getNombre().equals(nombre)){
                return r;
            }
        }
        return null;
    }

    public ArrayList<String> getNombresRepuestos() {
        ArrayList<String> nombres = new ArrayList<>();
        for(Repuesto r : repuestos){
            nombres.add(r.getNombre());
        }
        return nombres;
    }



}
