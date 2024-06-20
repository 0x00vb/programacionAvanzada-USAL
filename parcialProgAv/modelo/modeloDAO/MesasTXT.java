package modelo.modeloDAO;
import modelo.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class MesasTXT {
    public ArrayList<Mesa> leerMesas(){
        
        return null;
    }

    public void escribirMesa(Mesa mesa){
        File archivo = null;
        PrintWriter archivoSalida = null;
        try{
            archivo = new File("./Mesas.txt");
            archivoSalida = new PrintWriter( new FileWriter(archivo) );

            int numeroM = mesa.getNumero();
            String provincia = mesa.getProvincia().getNombre();
            String localidad = mesa.getLocalidad().getNombre();
            int votosA = mesa.getVotosPartidoA();
            int votosB = mesa.getVotosPartidoB();
            String tipoVotantes = mesa.getTipoVotantes();
            Calendar fecha = mesa.getFecha();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
            String fechaFormateada = dateFormat.format(fecha);

            archivoSalida.printf("%-4d %-15s %-15s %-3d %-3d %-10s %-10s", numeroM, provincia, localidad, votosA, votosB, tipoVotantes, fechaFormateada);

            
        }catch(IOException err){
            err.printStackTrace();
        }finally{
            if(archivoSalida != null){
                archivoSalida.close();
            }
        }
    }
}
