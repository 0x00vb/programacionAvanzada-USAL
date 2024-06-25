package controlador;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import modelo.Alojamiento;
import modelo.Cabaña;
import modelo.Huesped;
import modelo.Estadia;
import modelo.Menu;
import modelo.ServicioBar;
import modelo.Servicios;
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
        	CLIVista.mostrarTexto("Este alojamiento no cuenta con servicio de limpieza extra.");
            return;
        }

        Calendar fechaActual = Calendar.getInstance();
        estadia.addServicioLimpieza(fechaActual, huespedExiste);
        limpiezaTXT.escribirLimpiezaExtra(fechaActual, dniSolicitante, idAlojamiento);
    }

    public void registroServicioBar(){
        int dniSolicitante = Validaciones.validarHuesped();
        int idAlojamiento = Validaciones.validarAlojamiento();

        Huesped huespedSolicitante = huespedControlador.buscarHuesped(dniSolicitante);      	
        
        Estadia estadia = estadiaControlador.buscarEstadia(idAlojamiento, dniSolicitante);
        if(estadia != null){
            Calendar fechaFinalizada = estadia.getFechaIngreso();
            fechaFinalizada.add(Calendar.DAY_OF_MONTH, estadia.getCantidadDias());
            Calendar fechaActual = Calendar.getInstance();
            if(fechaActual.before(fechaFinalizada)){

                String nombrePlato;
                Menu plato;
                do{
                    nombrePlato = CLIVista.ingresar("Ingrese el nombre del plato consumo: ");
                    plato = menuControlador.buscarMenu(nombrePlato);
                    if(plato == null){
                    	CLIVista.mostrarTexto("Ese plato no existe. Intente de nuevo.");
                    }
                }while(plato == null);

                CLIVista.mostrarTexto("Ingresar cantidad de veces que repitio: ");
                int cantidad = Validaciones.validarInt(0, 10);

                estadia.addServicioBar(fechaActual, huespedSolicitante, plato, cantidad);
                servicioBarTXT.escribirConsumo(fechaActual, dniSolicitante, idAlojamiento, plato.getNombre(), cantidad);
            }else{CLIVista.mostrarTexto("Esta estadia ya ha finalizado.");}
        }else{
        	CLIVista.mostrarTexto("Los datos proveidos no coinciden con los de ninguna estadia.");
        }
    }
    
    public void mostrarConsumosEstadia(Estadia estadia) {
    	for(Servicios s : estadia.getServicios()) {
    		if(s instanceof ServicioBar) {
    			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
    			String fecha = dateFormat.format(s.getFecha());
    			CLIVista.mostrarTexto(
    					"id: " + estadia.getServicios().indexOf(s)
    					+ "fecha: " + fecha
    					+ "plato: " + ((ServicioBar)s).getPlato().getNombre()
    					+ "cantidad: " + ((ServicioBar)s).getCantidad()
    					+ "nombre Huesped: " + s.getHuesped().getNombre()
    					);
    		}
    	}
    }
    
    public void modificarConsumo() {
    	CLIVista.mostrarTexto("Ingrese DNI de un huesped");
    	Huesped huesped = huespedControlador.buscarHuesped(HuespedVista.ingresarDni());
    	if(huesped == null) {
        	CLIVista.mostrarTexto("Este huesped no existe!");
        	return;
    	}
    	
    	Estadia estadia = estadiaControlador.huespedEstadiaVigente(huesped);
    	if(estadia == null) {
        	CLIVista.mostrarTexto("Estadia ya finalizada");
        	return;
    	}
    	
    	mostrarConsumosEstadia(estadia);
    	CLIVista.mostrarTexto("ingrese id del consumo: ");
    	int id  = Validaciones.validarInt(0,estadia.getServicios().size());
    	ServicioBar consumo = null;
    	if(estadia.getServicios().get(id) instanceof ServicioBar) {
    		consumo = (ServicioBar) estadia.getServicios().get(id);
    	}else {
    		CLIVista.mostrarTexto("id de consumo invalido!");
    	}
    	
    	CLIVista.mostrarTexto("0. Eliminar\n1.modificar cantidad");
    	int opcion = Validaciones.validarInt(0,1);
    	switch(opcion) {
    		case 0:
    			estadia.getServicios().remove(consumo);
    			break;
    		case 1:
    			CLIVista.mostrarTexto("Ingresar nueva cantidad: ");
    			int nuevaCantidad = Validaciones.validarInt(0, 5);
    			consumo.setCantidad(nuevaCantidad);
    			break;
    		default:
    			break;
    	}    	
    }   
    
}