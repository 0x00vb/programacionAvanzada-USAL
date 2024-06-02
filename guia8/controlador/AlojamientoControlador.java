package controlador;
import java.util.Calendar;

import modelo.*;
import modeloDAO.AlojamientosTXT;
import vista.*;

public class AlojamientoControlador{
    AlojamientosTXT alojamientosTXT = new AlojamientosTXT();
    private Alojamiento[] alojamientos = alojamientosTXT.leerAlojamientos();
    HuespedControlador huespedControlador = new HuespedControlador();

    public void mostrarReporteUnidadesDisponibles(){
        AlojamientoVista.mostrarTexto("===Reporte unidades disponibles===");
        for(Alojamiento alojamiento : alojamientos){
            if(alojamiento.getDisponible()){
                AlojamientoVista.mostrarTexto(Validaciones.obtenerFechaFormateada(Calendar.getInstance()));
                AlojamientoVista.mostrarTexto(String.valueOf(alojamiento.getId()));
                AlojamientoVista.mostrarTexto(alojamiento.getArea());
                AlojamientoVista.mostrarTexto(String.valueOf(alojamiento.getTarifaDiaria().getCategoria()));
                AlojamientoVista.mostrarTexto(String.valueOf(alojamiento.getTarifaDiaria().getCapacidad()));
                AlojamientoVista.mostrarTexto(String.valueOf(alojamiento.getTarifaDiaria().getTarifa()));
                if(alojamiento instanceof Cabaña){
                    AlojamientoVista.mostrarTexto(((Cabaña)alojamiento).getHidromasaje() ? "Posee hidromasaje" : "No cuenta con hidromasaje");

                }else{
                    AlojamientoVista.mostrarTexto("Dispositivos electronicos: ");
                    for(String disp : ((Suite)alojamiento).getDispElectronicos()){
                        AlojamientoVista.mostrarTexto(disp);
                    }
                }
            }
        }   
    }

    public Alojamiento asignarAlojamiento(Huesped[] huespedes){
        mostrarReporteUnidadesDisponibles();

        AlojamientoVista.mostrarTexto("Desea hidromasaje: (s/n)");
        boolean deseaHidromasaje = Validaciones.validarBoolean();

        for(Alojamiento alojamiento : alojamientos){
            if(alojamiento.getDisponible() && alojamiento.getTarifaDiaria().getCapacidad() >= huespedes.length){
                if(deseaHidromasaje && ((Cabaña)alojamiento).getHidromasaje()){
                    AlojamientoVista.mostrarTexto(String.valueOf(alojamiento.getId()));
                    AlojamientoVista.mostrarTexto("Cuenta con hidromasaje");
                    AlojamientoVista.mostrarTexto("Capacidad: " + String.valueOf(alojamiento.getTarifaDiaria().getCapacidad()));
                    AlojamientoVista.mostrarTexto("Tarifa diaria: " + String.valueOf(alojamiento.getTarifaDiaria().getTarifa()));
                }
                AlojamientoVista.mostrarTexto(String.valueOf(alojamiento.getId()));
                AlojamientoVista.mostrarTexto("No uenta con hidromasaje");
                AlojamientoVista.mostrarTexto("Capacidad: " + String.valueOf(alojamiento.getTarifaDiaria().getCapacidad()));
                AlojamientoVista.mostrarTexto("Tarifa diaria: " + String.valueOf(alojamiento.getTarifaDiaria().getTarifa()));
            }else{
                AlojamientoVista.mostrarTexto("No hay alojamiento disponible que cumpla con esos requisitos.");
                return null;
            }
        }

        int numeroAlojamientoSeleccionado;
        Alojamiento alojamientoSeleccionado;
        do{
            numeroAlojamientoSeleccionado = Validaciones.validarInt();
            alojamientoSeleccionado = buscarAlojamiento(numeroAlojamientoSeleccionado);
            if (alojamientoSeleccionado == null || !alojamientoSeleccionado.getDisponible()) {
                AlojamientoVista.mostrarTexto("El alojamiento seleccionado no existe o no está disponible. Por favor, seleccione otro.");
            }
        }while(alojamientoSeleccionado == null || !alojamientoSeleccionado.getDisponible());

        alojamientoSeleccionado.setDisponible(false);
        AlojamientoVista.mostrarTexto("Alojamiento " + String.valueOf(numeroAlojamientoSeleccionado) + "seleccionado.");

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