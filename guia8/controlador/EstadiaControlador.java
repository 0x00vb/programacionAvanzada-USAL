package controlador;

import java.util.Calendar;

import modelo.*;
import modeloDAO.*;
import vista.*;

public class EstadiaControlador{
    private AlojamientoControlador alojamientoControlador = new AlojamientoControlador();
    private HuespedControlador huespedControlador = new HuespedControlador();
    private EstadiasJSON estadiasJSON = new EstadiasJSON();
    private AlojamientosTXT alojamientosTXT = new AlojamientosTXT();
    private Estadia[] estadias = estadiasJSON.leerEstadias();

    public void registrarEstadia(){
        Huesped[] huespedes = huespedControlador.registrarHuespedes();

        Alojamiento alojamiento = alojamientoControlador.asignarAlojamiento(huespedes);
        if(alojamiento != null){
            Calendar fechaActual = Calendar.getInstance();
            EstadiaVista.mostrarTexto("Cuantos dias sera ocupado el alojamiento? ");
            int diasEstadia = Validaciones.validarInt();
    
            Estadia estadia = new Estadia(fechaActual, diasEstadia, alojamiento, huespedes);
            estadia.getAlojamiento().setDisponible(false);
            estadiasJSON.escribirEstadia(estadia);
            alojamientosTXT.modificarDisponibilidad(alojamiento.getId());
        }
    }

    public void checkoutEstadia(){
        EstadiaVista.mostrarTexto("Ingrese el numero de alojamiento: ");
        int numeroAlojamiento = Validaciones.validarAlojamiento();
        EstadiaVista.mostrarTexto("Ingresar el dni de uno de los huespedes: ");
        int dniHuesped = Validaciones.validarInt(0, 50000000);

        Estadia estadia = buscarEstadia(numeroAlojamiento, dniHuesped);
        if(estadia == null){
            EstadiaVista.mostrarTexto("Esa estadia no existe!");
            return;
        }

        EstadiaVista.mostrarTexto("El costo total a pagar es: $" + estadia.calcularCostoTotal());
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
            EstadiaVista.mostrarTexto("" + numeroCabinaObjetivo);
        }else{
            EstadiaVista.mostrarTexto("Ninguna Cabaña cumple con los requisitos.");
        }
    }

    public void metodoAdicional4(){
        for(Estadia estadia : estadias){
            if(!estadia.getAlojamiento().getDisponible()){
                AlojamientosOcupadosJSON.escribirInformacion(estadia);
            }
        }
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

    public Estadia[] getEstadias(){
        return this.estadias;
    }
}