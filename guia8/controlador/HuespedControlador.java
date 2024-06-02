package controlador;
import java.util.Calendar;

import modelo.Estadia;
import modelo.Huesped;
import modeloDAO.HuespedesTXT;
import vista.*;

public class HuespedControlador{
    private HuespedesTXT huespedesTXT = new HuespedesTXT();
    private Huesped[] huespedes = huespedesTXT.leerHuespedes();
    private EstadiaControlador estadiaControlador = new EstadiaControlador();
    
    public Huesped[] registrarHuespedes() {
        HuespedVista.mostrarTexto("Ingresar la cantidad de huespedes a registrar: ");
        int cantidadHuespedes = Validaciones.validarInt(0, 6);
        Huesped[] nuevosHuespedes = new Huesped[cantidadHuespedes];
        for (int i = 0; i < cantidadHuespedes; i++) {
            boolean huespedValido = false;
            while (!huespedValido) {
                HuespedVista.mostrarTexto("Ingresar DNI del huesped " + (i + 1) + ": ");
                int dni = Validaciones.validarInt(0, 50000000);
                String nombre = HuespedVista.ingresar("Nombre: ");
                String apellido = HuespedVista.ingresar("Apellido: ");
                String paisResidencia = HuespedVista.ingresar("País de residencia: ");
                Huesped huespedExistente = buscarHuesped(dni);
                if (huespedExistente != null) {
                    if (huespedExistente.getNombre().equals(nombre) && huespedExistente.getApellido().equals(apellido) && huespedExistente.getPaisRecidencia().equals(paisResidencia)) {
                        HuespedVista.mostrarTexto("Este huésped ya estaba registrado.");
                        for (Estadia estadia : estadiaControlador.getEstadias()) {
                            for (Huesped huesped : estadia.getHuespedes()) {
                                if (huespedExistente.getDocumento() == huesped.getDocumento()) {
                                    Calendar fechaActual = Calendar.getInstance();
                                    Calendar fechaFinalizaEstadia = estadia.getFechaIngreso();
                                    fechaFinalizaEstadia.add(Calendar.DAY_OF_MONTH, estadia.getCantidadDias());
                                    if (fechaFinalizaEstadia.after(fechaActual)) {
                                        HuespedVista.mostrarTexto("Este huésped ya tiene una estadía activa.");
                                        break;
                                    }
                                }
                            }
                        }
                        nuevosHuespedes[i] = huespedExistente;
                    }
                } else {
                    Huesped nuevoHuesped = new Huesped(dni, nombre, apellido, paisResidencia);
                    huespedesTXT.escribirHuesped(nuevoHuesped);
                    nuevosHuespedes[i] = nuevoHuesped;
                    HuespedVista.mostrarTexto("¡Huésped nuevo registrado!");
                    huespedValido = true;
                }
            }
        }
        return nuevosHuespedes;
    }
    
    public void metodoAdicional3(){
        int cantidadHuespedes = 0;
        for(Huesped huesped : huespedes){
            int contadorHuesped = 0;
            for(Estadia estadia : estadiaControlador.getEstadias()){
                for(Huesped huesped2 : estadia.getHuespedes()){
                    if(huesped.getDocumento() == huesped2.getDocumento()){
                        contadorHuesped++;
                    }
                }
            }
            if(contadorHuesped > 3){
                cantidadHuespedes++;
            }
        }
        HuespedVista.mostrarTexto("" + cantidadHuespedes);
    }

    public Huesped buscarHuesped(int dni){
        for(Huesped huesped : huespedes){
            if(huesped.getDocumento() == dni){
                return huesped;
            }
        }
        return null;
    }
}   