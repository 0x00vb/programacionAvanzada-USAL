package controlador;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;
import java.util.TreeSet;

import modelo.*;
import modelo.Menu;
import modeloDAO.*;
import vista.*;

public class EstadiaControlador{
    private AlojamientoControlador alojamientoControlador = new AlojamientoControlador();
    private HuespedControlador huespedControlador = new HuespedControlador();
    private EstadiasJSON estadiasJSON = new EstadiasJSON();
    private AlojamientosTXT alojamientosTXT = new AlojamientosTXT();
    private ArrayList<Estadia> estadias = estadiasJSON.leerEstadias();

    public boolean estadiaEnCurso(Estadia e) {
    	Calendar fechaActual = Calendar.getInstance();
    	Calendar fechaEgreso = e.getFechaIngreso();
    	fechaEgreso.add(Calendar.DAY_OF_YEAR, e.getCantidadDias());
    	
    	return (e.getFechaIngreso().before(fechaActual) && fechaEgreso.after(fechaActual) ? true : false);
    }
    
    public void registrarEstadia(){
    	ArrayList<Huesped> huespedes = huespedControlador.registrarHuespedes();

        Alojamiento alojamiento = alojamientoControlador.asignarAlojamiento(huespedes);
        if(alojamiento != null){
            Calendar fechaActual = Calendar.getInstance();
            CLIVista.mostrarTexto("Cuantos dias sera ocupado el alojamiento? ");
            int diasEstadia = Validaciones.validarInt();
    
            Estadia estadia = new Estadia(fechaActual, diasEstadia, alojamiento, huespedes);
            estadia.getAlojamiento().setDisponible(false);
            estadiasJSON.escribirEstadia(estadia);
            alojamientosTXT.modificarDisponibilidad(alojamiento.getId());
        }
    }

    public void checkoutEstadia(){
    	CLIVista.mostrarTexto("Ingrese el numero de alojamiento: ");
        int numeroAlojamiento = Validaciones.validarAlojamiento();
        CLIVista.mostrarTexto("Ingresar el dni de uno de los huespedes: ");
        int dniHuesped = Validaciones.validarInt(0, 50000000);

        Estadia estadia = buscarEstadia(numeroAlojamiento, dniHuesped);
        if(estadia == null){
        	CLIVista.mostrarTexto("Esa estadia no existe!");
            return;
        }

        CLIVista.mostrarTexto("El costo total a pagar es: $" + estadia.calcularCostoTotal());
        estadia.getAlojamiento().setDisponible(true);
        alojamientosTXT.modificarDisponibilidad(numeroAlojamiento);
    }

    public void metodoAdicional2(){        
        Calendar mesAnterior = Calendar.getInstance();
        mesAnterior.add(Calendar.MONTH, -1);

        int numeroCabinaObjetivo = -1;

        for(Estadia estadia : estadias){
            Calendar fechaEgreso = estadia.getFechaIngreso();
            fechaEgreso.add(Calendar.DAY_OF_MONTH, estadia.getCantidadDias());
            boolean utilizadaAntesMesAnterior = false;
            if(fechaEgreso.after(mesAnterior) && estadia.getAlojamiento() instanceof Cabaña && ((Cabaña)estadia.getAlojamiento()).getHidromasaje()){
                if(fechaEgreso.before(mesAnterior)){
                    utilizadaAntesMesAnterior = true;
                    if(numeroCabinaObjetivo == estadia.getAlojamiento().getId()){numeroCabinaObjetivo = -1;}
                }    

                if(!utilizadaAntesMesAnterior){
                    numeroCabinaObjetivo = estadia.getAlojamiento().getId();
                }
            }
        }

        if(numeroCabinaObjetivo == -1){
        	CLIVista.mostrarTexto("" + numeroCabinaObjetivo);
        }else{
        	CLIVista.mostrarTexto("Ninguna Cabaña cumple con los requisitos.");
        }
    }

    public void metodoAdicional4(){
        for(Estadia estadia : estadias){
            if(!estadia.getAlojamiento().getDisponible()){
                AlojamientosOcupadosJSON.escribirInformacion(estadia);
            }
        }
    }

    public boolean pertenecenMismaEstadia(ArrayList<Huesped> huespedes) {
    	Estadia estadia = huespedEstadiaVigente(huespedes.get(0));
    	Set<Integer> dnis = new TreeSet<Integer>();
    	for(Huesped hEstadia : estadia.getHuespedes()) {
    		dnis.add(hEstadia.getDocumento());
    	}
    	for(Huesped h : huespedes) {
    		if(!dnis.contains(h.getDocumento())) return false;
    	}
    	return true;
    }
    
    public Estadia huespedEstadiaVigente(Huesped huesped) {
    	for(Estadia e : estadias) {
    		if(e != null && estadiaEnCurso(e)) {
    			for(Huesped h : e.getHuespedes()) {
    				if(h.getDocumento() == huesped.getDocumento()) {
    					return e;
    				}
    			}
    		}
    	}
    	return null;
    }
    
    public Estadia buscarEstadia(int idAlojamiento, int dniHuesped) {
        for (Estadia estadia : estadias) {
            if (estadia.getAlojamiento().getId() == idAlojamiento) {
                for (Huesped huesped : estadia.getHuespedes()) {
                    if (huesped.getDocumento() == dniHuesped) {
                        return estadia;
                    }
                }
            }
        }
        return null;
    }

    public ArrayList<Estadia> getEstadias(){
        return this.estadias;
    }
}