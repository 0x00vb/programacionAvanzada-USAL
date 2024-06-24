package modeloDAO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Scanner;

import modelo.*;
import controlador.*;

public class ServicioBarTXT {
    private MenuControlador menuControlador = new MenuControlador();
    private HuespedControlador huespedControlador = new HuespedControlador();
   
    public void escribirConsumo(Calendar fechaSolicitud, int dniSolicitante, int idAlojamiento, String nombrePlato, int cantidad){
        FileWriter archivo = null;
        PrintWriter archivoEntrada = null;
        try{
            String nombreArchivo = "Cons" + String.valueOf(idAlojamiento) + ".txt";
            archivo = new FileWriter("../" + nombreArchivo, true);
            archivoEntrada = new PrintWriter(archivo);

            String fechaFormateada = FormateoFechas.formatearFechaAString(fechaSolicitud);
            archivoEntrada.printf("%-20s%-20s%-20s%-20s%-20s%n", fechaFormateada, dniSolicitante, idAlojamiento, nombrePlato, cantidad);
        }catch(IOException err){
            err.printStackTrace();
        }finally{
            if (archivoEntrada != null) {
                archivoEntrada.close();
            }
        }
    }

    public ServicioBar[] leerConsumos(Estadia estadia){
        ServicioBar[] consumos = new ServicioBar[5];
        File archivo = null;
        Scanner archivoEntrada = null;
        try{
            String nombreArchivo = "Cons" + estadia.getAlojamiento().getId()  + ".txt";
            archivo = new File("../" + nombreArchivo);

            if(archivo.exists()){
                archivoEntrada = new Scanner(archivo);
            }else{ throw new IOException(); }

            while(archivoEntrada.hasNext()){
                String lineaActual = archivoEntrada.nextLine();
                String[] partes = lineaActual.split(" ");

                Calendar fechaSolicitudConsumo = FormateoFechas.formatearStringACalendar(partes[0]);
                int dniSolicitante = Integer.parseInt(partes[1]);
                String nombrePlato = partes[3];
                int cantidad = Integer.parseInt(partes[4]);
                
                Calendar fechaEgreso = estadia.getFechaIngreso();
                fechaEgreso.add(Calendar.DAY_OF_MONTH, estadia.getCantidadDias());

                if(fechaSolicitudConsumo.after(estadia.getFechaIngreso()) && fechaSolicitudConsumo.before(fechaEgreso)){
                    Menu plato = menuControlador.buscarMenu(nombrePlato);
                    Huesped huesped = huespedControlador.buscarHuesped(dniSolicitante);
                    estadia.addServicioBar(fechaSolicitudConsumo, huesped, plato, cantidad);
                }
            }

        }catch(IOException err){
            err.printStackTrace();
        }finally{
            if(archivoEntrada != null){
                archivoEntrada.close();
            }
        }

        return consumos;    
    }


}
