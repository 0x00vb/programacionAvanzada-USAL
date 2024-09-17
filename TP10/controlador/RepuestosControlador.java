package controlador;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import modelo.Repuesto;
import modelo.dao.RepuestosTXT;

public class RepuestosControlador {
    private ArrayList<Repuesto> repuestos = RepuestosTXT.leerRepuestos();

    public RepuestosControlador() {
        this.repuestos = RepuestosTXT.leerRepuestos();
    }

    public ArrayList<String> getRepuestosPorMarca(String marca){
        ArrayList<String> lista = new ArrayList<>();
        for(Repuesto r : repuestos){
            if(r.getMarca().equals(marca)){
                lista.add(r.getNombre());
            }
        }
        return lista;
    }

    public Repuesto buscarRepuesto(int cod){
        for(Repuesto r : repuestos){
            if(r.getCodigo() == cod){
                return r;
            }
        }
        return null;
    }


    public Repuesto buscarRepuesto(String nombre){
        for(Repuesto r : repuestos){
            if(r.getNombre().equals(nombre)){
                return r;
            }
        }
        return null;
    }

    public Repuesto buscarRepuesto(String nombre, String marca){
        for(Repuesto r : repuestos){
            if(r.getNombre().equals(nombre) && r.getMarca().equals(marca)){
                return r;
            }
        }
        return null;
    }

    public ArrayList<String> getMarcasRepuestos() {
        ArrayList<String> marcas = new ArrayList<>();
        for(Repuesto r : repuestos){
            if(!marcas.contains(r.getMarca())){
                marcas.add(r.getMarca());
            }
        }
        return marcas;
    }

    public ArrayList<String> getNombresRepuestos() {
        ArrayList<String> nombres = new ArrayList<>();
        for(Repuesto r : repuestos){
            nombres.add(r.getNombre());
        }
        return nombres;
    }

    public ArrayList<Repuesto> getRepuestos(){
        return this.repuestos;
    }

}
