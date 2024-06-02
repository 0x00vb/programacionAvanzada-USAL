package controlador;

import java.util.Calendar;

import modelo.Alojamiento;
import modelo.Cabaña;
import modelo.Huesped;
import modelo.Estadia;
import modelo.Menu;
import modeloDAO.ServicioBarTXT;
import modeloDAO.LimpiezaTXT;
import vista.*;

public class ServiciosControlador {
    private ServicioBarTXT servicioBarTXT = new ServicioBarTXT();
    private LimpiezaTXT limpiezaTXT = new LimpiezaTXT();
    private HuespedControlador huespedControlador = new HuespedControlador();
    private AlojamientoControlador alojamientoControlador = new AlojamientoControlador();
    private EstadiaControlador estadiaControlador = new EstadiaControlador();
    private MenuControlador menuControlador = new MenuControlador();
    public void registrarServicioLimpieza(){
        Huesped huespedExiste;
        Alojamiento alojamientoExiste;
        int dniSolicitante;
        int idAlojamiento;
        
        do {
            idAlojamiento = Validaciones.validarInt(0, 99);
            alojamientoExiste = alojamientoControlador.buscarAlojamiento(idAlojamiento);
            if (alojamientoExiste == null) {
                System.out.println("El alojamiento no existe. Intente nuevamente.");
            }
        } while (alojamientoExiste == null);

        do {
            dniSolicitante = Validaciones.validarInt(0, 50000000);
            huespedExiste = huespedControlador.buscarHuesped(dniSolicitante);
            if (huespedExiste == null) {
                System.out.println("El huésped no existe. Intente nuevamente.");
            }
        } while (huespedExiste == null);
    
        Estadia estadia = estadiaControlador.buscarEstadia(idAlojamiento, dniSolicitante);
        
        if(!(estadia.getAlojamiento() instanceof Cabaña)){
            ServiciosVista.mostrarTexto("Este alojamiento no cuenta con servicio de limpieza extra.");
            return;
        }

        Calendar fechaActual = Calendar.getInstance();
        estadia.addServicioLimpieza(fechaActual, dniSolicitante, idAlojamiento);
        limpiezaTXT.escribirLimpiezaExtra(fechaActual, dniSolicitante, idAlojamiento);
    }

    public void registroServicioBar(){
        int dniSolicitante = Validaciones.validarHuesped();
        int idAlojamiento = Validaciones.validarAlojamiento();

        Estadia estadia = estadiaControlador.buscarEstadia(idAlojamiento, dniSolicitante);
        if(estadia != null){
            Calendar fechaFinalizada = estadia.getFechaIngreso();
            fechaFinalizada.add(Calendar.DAY_OF_MONTH, estadia.getCantidadDias());
            Calendar fechaActual = Calendar.getInstance();
            if(fechaActual.before(fechaFinalizada)){

                String nombrePlato;
                Menu plato;
                do{
                    nombrePlato = ServiciosVista.ingresar("Ingrese el nombre del plato consumo: ");
                    plato = menuControlador.buscarMenu(nombrePlato);
                    if(plato == null){
                        ServiciosVista.mostrarTexto("Ese plato no existe. Intente de nuevo.");
                    }
                }while(plato == null);

                ServiciosVista.mostrarTexto("Ingresar cantidad de veces que repitio: ");
                int cantidad = Validaciones.validarInt(0, 10);

                estadia.addServicioBar(fechaActual, dniSolicitante, idAlojamiento, plato, cantidad);
                servicioBarTXT.escribirConsumo(fechaActual, dniSolicitante, idAlojamiento, plato.getNombre(), cantidad);
            }else{ServiciosVista.mostrarTexto("Esta estadia ya ha finalizado.");}
        }else{
            ServiciosVista.mostrarTexto("Los datos proveidos no coinciden con los de ninguna estadia.");
        }
    }
}