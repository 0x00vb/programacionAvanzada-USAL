package modelo.modeloDAO;
import modelo.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class MesasTXT {
    public ArrayList<Mesa> leerMesas() throws ExcepcionPropia{
        ArrayList<Mesa> mesas = new ArrayList<Mesa>();
        File archivo = null;
        Scanner archivoEntrada = null;
        try{
            archivo = new File("Mesas.txt");
            if(archivo.exists()){
                archivoEntrada = new Scanner(archivo);
            }

            while (archivoEntrada.hasNext()) {
                String lineaActual = archivoEntrada.nextLine();

                String numeroMStr = lineaActual.substring(0, 4).trim();
                try{
                    int numeroM = Integer.parseInt(numeroMStr);
                }catch(NumberFormatException e){
                    throw new ExcepcionPropia("");
                }
                String provincia = lineaActual.substring(5, 20).trim();
                String localidad = lineaActual.substring(21, 36).trim();
                String votosAStr = lineaActual.substring(37, 40).trim();
                String votosBStr = lineaActual.substring(41, 44).trim();
                String tipoVotantes = lineaActual.substring(45, 55).trim();
                String fechaStr = lineaActual.substring(56, 66).trim();

                


            }




        }catch(IOException err){
            err.printStackTrace();
        }finally{
            if(archivoEntrada != null)
                archivoEntrada.close();
        }



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
