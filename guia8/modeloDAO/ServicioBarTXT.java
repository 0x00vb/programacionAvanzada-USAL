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
   
    public void escribirConsumo(Calendar fechaSolicitud, int dniSolicitante, int idAlojamiento, String nombrePlato, int cantidad){
        FileWriter archivo = null;
        PrintWriter archivoEntrada = null;
        try{
            String nombreArchivo = "Limp" + String.valueOf(idAlojamiento);
            archivo = new FileWriter("../" + nombreArchivo);
            archivoEntrada = new PrintWriter(archivo);

            String fechaFormateada = formatearFechaAString(fechaSolicitud);
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

                Calendar fechaSolicitudConsumo = formatearStringACalendar(partes[0]);
                int dniSolicitante = Integer.parseInt(partes[1]);
                int idAlojamientoConsumo = Integer.parseInt(partes[2]);
                String nombrePlato = partes[3];
                int cantidad = Integer.parseInt(partes[4]);
                
                Calendar fechaEgreso = estadia.getFechaIngreso();
                fechaEgreso.add(Calendar.DAY_OF_MONTH, estadia.getCantidadDias());

                if(fechaSolicitudConsumo.after(estadia.getFechaIngreso()) && fechaSolicitudConsumo.before(fechaEgreso)){
                    Menu plato = menuControlador.buscarMenu(nombrePlato);
                    estadia.addServicioBar(fechaSolicitudConsumo, dniSolicitante, idAlojamientoConsumo, plato, cantidad);
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

    public static String formatearFechaAString(Calendar fecha){
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int año = fecha.get(Calendar.YEAR);
        
        String diaStr = (dia < 10) ? "0" + dia : String.valueOf(dia);
        String mesStr = (mes < 10) ? "0" + mes : String.valueOf(mes);
        String añoStr = String.valueOf(año);
        
        return diaStr + "/" + mesStr + "/" + añoStr;
    }

    public static Calendar formatearStringACalendar(String fecha){
        Calendar fechaCalendar = Calendar.getInstance();

        String partesFecha[] = fecha.split("/");

        int dia = Integer.parseInt(partesFecha[0]);
        int mes = Integer.parseInt(partesFecha[1]);
        int año = Integer.parseInt(partesFecha[2]);

        fechaCalendar.set(año, mes, dia);

        return fechaCalendar;
    }
}
