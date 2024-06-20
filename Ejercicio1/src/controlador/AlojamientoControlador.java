package controlador;
import java.util.ArrayList;
import java.util.Calendar;

import modelo.*;
import modeloDAO.AlojamientosTXT;
import vista.*;

public class AlojamientoControlador{
    AlojamientosTXT alojamientosTXT = new AlojamientosTXT();
    private ArrayList<Alojamiento> alojamientos = alojamientosTXT.leerAlojamientos();
    HuespedControlador huespedControlador = new HuespedControlador();

    public void mostrarReporteUnidadesDisponibles(){
        CLIVista.mostrarTexto("===Reporte unidades disponibles===");
        for(Alojamiento alojamiento : alojamientos){
            if(alojamiento.getDisponible()){
            	CLIVista.mostrarTexto(Validaciones.obtenerFechaFormateada(Calendar.getInstance()));
            	CLIVista.mostrarTexto(String.valueOf(alojamiento.getId()));
                CLIVista.mostrarTexto(alojamiento.getArea());
                CLIVista.mostrarTexto(String.valueOf(alojamiento.getTarifaDiaria().getCategoria()));
                CLIVista.mostrarTexto(String.valueOf(alojamiento.getTarifaDiaria().getCapacidad()));
                CLIVista.mostrarTexto(String.valueOf(alojamiento.getTarifaDiaria().getTarifa()));
                if(alojamiento instanceof Cabaña){
                    CLIVista.mostrarTexto(((Cabaña)alojamiento).getHidromasaje() ? "Posee hidromasaje" : "No cuenta con hidromasaje");

                }else{
                    CLIVista.mostrarTexto("Dispositivos electronicos: ");
                    for(String disp : ((Suite)alojamiento).getDispElectronicos()){
                        CLIVista.mostrarTexto(disp);
                    }
                }
            }
        }   
    }

    public Alojamiento asignarAlojamiento(ArrayList<Huesped> huespedes){
        mostrarReporteUnidadesDisponibles();

        CLIVista.mostrarTexto("Desea hidromasaje: (s/n)");
        boolean deseaHidromasaje = Validaciones.validarBoolean();

        for(Alojamiento alojamiento : alojamientos){
            if(alojamiento.getDisponible() && alojamiento.getTarifaDiaria().getCapacidad() >= huespedes.size()){
                if(deseaHidromasaje && ((Cabaña)alojamiento).getHidromasaje()){
                    CLIVista.mostrarTexto(String.valueOf(alojamiento.getId()));
                    CLIVista.mostrarTexto("Cuenta con hidromasaje");
                    CLIVista.mostrarTexto("Capacidad: " + String.valueOf(alojamiento.getTarifaDiaria().getCapacidad()));
                    CLIVista.mostrarTexto("Tarifa diaria: " + String.valueOf(alojamiento.getTarifaDiaria().getTarifa()));
                }
                CLIVista.mostrarTexto(String.valueOf(alojamiento.getId()));
                CLIVista.mostrarTexto("No uenta con hidromasaje");
                CLIVista.mostrarTexto("Capacidad: " + String.valueOf(alojamiento.getTarifaDiaria().getCapacidad()));
                CLIVista.mostrarTexto("Tarifa diaria: " + String.valueOf(alojamiento.getTarifaDiaria().getTarifa()));
            }else{
                CLIVista.mostrarTexto("No hay alojamiento disponible que cumpla con esos requisitos.");
                return null;
            }
        }

        int numeroAlojamientoSeleccionado;
        Alojamiento alojamientoSeleccionado;
        do{
            numeroAlojamientoSeleccionado = Validaciones.validarInt();
            alojamientoSeleccionado = buscarAlojamiento(numeroAlojamientoSeleccionado);
            if (alojamientoSeleccionado == null || !alojamientoSeleccionado.getDisponible()) {
                CLIVista.mostrarTexto("El alojamiento seleccionado no existe o no está disponible. Por favor, seleccione otro.");
            }
        }while(alojamientoSeleccionado == null || !alojamientoSeleccionado.getDisponible());

        alojamientoSeleccionado.setDisponible(false);
        CLIVista.mostrarTexto("Alojamiento " + String.valueOf(numeroAlojamientoSeleccionado) + "seleccionado.");

        return alojamientoSeleccionado;
    }


    public Alojamiento buscarAlojamiento(int id){
        for(Alojamiento alojamiento : alojamientos){
            if(alojamiento.getId() == id){
                return alojamiento;
            }
        }
        return null;
    }

}