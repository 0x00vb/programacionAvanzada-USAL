package modeloDAO;

import modelo.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Scanner;

public class LimpiezaTXT {
    public void escribirLimpiezaExtra(Calendar fecha, int dniSolicitante, int idAlojamiento){
        FileWriter archivo = null;
        PrintWriter archivoEntrada = null;
        try{
            String nombreArchivo = "Limp" + String.valueOf(idAlojamiento);
            archivo = new FileWriter("../" + nombreArchivo);
            archivoEntrada = new PrintWriter(archivo);

            String fechaFormateada = ServicioBarTXT.formatearFechaAString(fecha);

            archivoEntrada.printf("%-20s%-20s%n", fechaFormateada, dniSolicitante);
        }catch(IOException err){
            err.printStackTrace();
        }finally{
            if (archivoEntrada != null) {
                archivoEntrada.close();
            }
        }
    }
    
    public Limpieza[] leerLimpiezaExtra(Estadia estadia){
        Limpieza[] limpiezas = new Limpieza[5];
        File archivo = null;
        Scanner archivoEntrada = null;
        try{
            String nombreArchivo = "Limp" + estadia.getAlojamiento().getId() + ".txt";
            archivo = new File("../" + nombreArchivo);

            if(archivo.exists()){
                archivoEntrada = new Scanner(archivo);
            }else{ throw new IOException(); }

            while(archivoEntrada.hasNext()){
                String lineaActual = archivoEntrada.nextLine();
                String[] partes = lineaActual.split(" ");

                Calendar fechaSolicitud = ServicioBarTXT.formatearStringACalendar(partes[0]);
                int dniSolicitante = Integer.parseInt(partes[1]);
                
                Calendar fechaEgreso = estadia.getFechaIngreso();
                fechaEgreso.add(Calendar.DAY_OF_MONTH, estadia.getCantidadDias());

                if(fechaSolicitud.after(estadia.getFechaIngreso()) && fechaSolicitud.before(fechaEgreso)){
                    estadia.addServicioLimpieza(fechaSolicitud, dniSolicitante, estadia.getAlojamiento().getId());
                }
            }

        }catch(IOException err){
            err.printStackTrace();
        }finally{
            if(archivoEntrada != null){
                archivoEntrada.close();
            }
        }

        return limpiezas;    
    }

}
