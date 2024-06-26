package controlador;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import modelo.Mesa;
import modelo.Localidad;
import modelo.Provincia;

import modelo.modeloDAO.*;

public class Controlador {
    MesasTXT mtxt = new MesasTXT();
    ArrayList<Provincia> provincias = new ArrayList<Provincia>(); //(Provincias ya leidas)
    ArrayList<Localidad> localidades = new ArrayList<Localidad>(); //(Localidades ya leidas)
    ArrayList<Mesa> mesas = new ArrayList<Mesa>();
    private String arg;
    public Controlador(String arg){
        this.arg = arg;
    }

    public void asociarLocalidadesAProvincias() {
        for (Localidad localidad : localidades) {
            for (Provincia provincia : provincias) {
                if (localidad.getCodigoProvincia() == provincia.getCodigo()) {
                    provincia.addLocalidad(localidad);
                }
            }
        }
    }

    public ArrayList<String> obtenerNombresProvs(){
        Collections.sort(provincias, (p1, p2) -> p1.getNombre().compareToIgnoreCase(p2.getNombre()));
        ArrayList<String> nombres = new ArrayList<String>();
        for(Provincia p : provincias){
            nombres.add(p.getNombre());
        }
        return nombres;
    }

    public ArrayList<String> obtenerNombresLocalidades(String nombreProvincia){
        Provincia provincia = buscarProvincia(nombreProvincia);
        ArrayList<Localidad> localidadesValidas = provincia.getLocalidades();
        ArrayList<String> nombres = new ArrayList<String>();
        for(Localidad l : localidadesValidas){
            nombres.add(l.getNombre());
        }
        return nombres;
    }


    public Provincia buscarProvincia(String nombre){
        for(Provincia p : provincias){
            if(p.getNombre().equals(nombre)){
                return p;
            }
        }
        return null;
    }


    public Localidad buscarLocalidad(String nombre){
        for(Localidad l : localidades){
            if(l.getNombre().equals(nombre)){
                return l;
            }
        }
        return null;
    }

    public int guardarRegistro(int numeroMesa, String nombreProvincia, String nombreLocalidad, int votosPartidoA, int votosPartidoB, String sexoVotantes){
        if(numeroMesa < 1 && numeroMesa > 9999){
            return -1;
        }

        Provincia provincia = buscarProvincia(nombreProvincia);
        Localidad localidad = buscarLocalidad(nombreLocalidad);
        Mesa nuevaMesa = new Mesa(numeroMesa, provincia, localidad, votosPartidoA, votosPartidoB, sexoVotantes);
        mesas.add(nuevaMesa);
        mtxt.escribirMesa(nuevaMesa);
        return 0;
    }



    public String getPartidoGanador(){
        int votosA = 0;
        int votosB = 0;
        int totalVotos = 0;
        for(Mesa mesa : mesas){
            votosA += mesa.getVotosPartidoA();
            votosB += mesa.getVotosPartidoB();
            totalVotos += mesa.getVotosPartidoA() + mesa.getVotosPartidoB();
        }

        if(votosA == votosB)
            return "Empate";

        String ganador = (votosA > votosB ? "A" : "B");
        double porcentaje = (ganador == "A" ? votosA : votosB) / totalVotos * 100;
        String porcentajeS = String.format(" %.2f", porcentaje);
        ganador += porcentajeS;

        return ganador;
    }


    public String getCantVotantes(){
        Iterator<Mesa> iteratorMesa = mesas.iterator();
        int cantidadTotal = 0;
        while(iteratorMesa.hasNext()){
            Mesa mesa = iteratorMesa.next();
            cantidadTotal += mesa.getVotosPartidoA() + mesa.getNumero();
        }
        String r = "" + cantidadTotal;
        return r;
    }

    public void puntoA(){
        int cantidad = 0;
        int a = Integer.parseInt(arg);
        for(Mesa mesa : mesas){
            if(mesa.getVotosPartidoA() == a){
                cantidad++;
            }
        }
        System.out.println(cantidad);
    }

    public void puntoB(){

    }

    public List<Mesa> puntoC(){
        ArrayList<Mesa> mesasOrd = mesas;

        mesasOrd.sort((m0, m1) -> Integer.compare(m1.getVotosPartidoA() + m1.getVotosPartidoB(), m0.getVotosPartidoA() + m0.getVotosPartidoB()));
        mesasOrd
            .stream().limit(3).
            sorted((m0, m1) -> Integer.compare( m1.getNumero(), m0.getNumero() )).toArray();






        mesasOrd.stream().limit(3);
        return mesasOrd;
    }

    public Map<Integer, Integer> puntoD(){
        HashMap<Integer, Integer> map = new HashMap<>();
        for(Mesa mesa : mesas){
            int cantidadVotantes = mesa.getVotosPartidoA() + mesa.getVotosPartidoB();
            map.put(mesa.getNumero(), cantidadVotantes);
        }
        return map;
    }

}
