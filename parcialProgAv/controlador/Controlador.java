package controlador;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import modelo.*;
import modelo.modeloDAO.*;

public class Controlador {
    MesasTXT mtxt = new MesasTXT();
    ArrayList<Provincia> provincias = new ArrayList<Provincia>(); //(Provincias ya leidas)
    ArrayList<Localidad> localidades = new ArrayList<Localidad>(); //(Localidades ya leidas)
    ArrayList<Mesa> mesas = new ArrayList<Mesa>();

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

    public int calcularVotosPartidoA() {
        int totalVotosA = 0;
        for (Mesa mesa : mesas) {
            totalVotosA += mesa.getVotosPartidoA();
        }
        return totalVotosA;
    }

    public int calcularVotosPartidoB() {
        int totalVotosB = 0;
        for (Mesa mesa : mesas) {
            totalVotosB += mesa.getVotosPartidoB();
        }
        return totalVotosB;
    }
    public int calcularCantidadTotalVotantes() {
        int cantidadTotal = 0;
        for (Mesa mesa : mesas) {
            cantidadTotal += mesa.getVotosPartidoA() + mesa.getVotosPartidoB();
        }
        return cantidadTotal;
    }

    public String determinarPartidoGanador() {
        int votosA = calcularVotosPartidoA();
        int votosB = calcularVotosPartidoB();

        if (votosA > votosB) {
            return "Partido A";
        } else if (votosB > votosA) {
            return "Partido B";
        } else {
            return "Empate";
        }
    }

    public double calcularPorcentajePartidoGanador() {
        int votosGanador = 0;
        String partidoGanador = determinarPartidoGanador();

        if (partidoGanador.equals("Partido A")) {
            votosGanador = calcularVotosPartidoA();
        } else if (partidoGanador.equals("Partido B")) {
            votosGanador = calcularVotosPartidoB();
        }

        int votosTotales = calcularVotosTotales();
        if (votosTotales > 0) {
            return (double) votosGanador / votosTotales * 100;
        } else {
            return 0.0;
        }
    }

    public int contarMesasConVotosPartidoA(int votosPartidoA) {
        int count = 0;
        for (Mesa mesa : mesas) {
            if (mesa.getVotosPartidoA() == votosPartidoA) {
                count++;
            }
        }
        return count;
    }

    public List<Mesa> mesasConMasVotantes() {
        List<Mesa> mesasOrdenadas = new ArrayList<>(mesas);
        mesasOrdenadas.sort(Comparator.comparingInt(Mesa::getTotalVotantes).reversed());
        return mesasOrdenadas;
    }
}
