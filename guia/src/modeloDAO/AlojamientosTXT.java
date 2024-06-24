package modeloDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import modelo.Alojamiento;
import modelo.TarifaDiaria;
import modelo.Cabaña;
import modelo.Suite;

public class AlojamientosTXT {
    public ArrayList<Alojamiento> leerAlojamientos(){
    	ArrayList<Alojamiento> alojamientos = new ArrayList<Alojamiento>();
        TarifasDiariasCSV tarifasDiariasCSV = new TarifasDiariasCSV();
        TarifaDiaria[] tarifasDiarias = tarifasDiariasCSV.leerTarifas();
        File archivo = null;
        Scanner archivoEntrada = null;
        try{
            archivo = new File("../alojamientos.txt");
            if(archivo.exists()){
                archivoEntrada = new Scanner(archivo);
            }else{
                throw new IOException();
            }

            while(archivoEntrada.hasNext()){
                String lineaActual = archivoEntrada.nextLine();
                int id = Integer.parseInt(lineaActual.substring(0, 4).trim());
                String area = lineaActual.substring(4,16).trim();
                char tipo = lineaActual.substring(16, 17).charAt(0);
                char categoria = lineaActual.substring(17,19).trim().charAt(0);
                int cantidadMax = Integer.parseInt(lineaActual.substring(19, 21).trim());
                char disp = lineaActual.substring(21,22).trim().charAt(0);
                boolean disponible = (disp == 'd' ? true : false);
                
                TarifaDiaria tarifa = new TarifaDiaria();
                for(TarifaDiaria tarifaDiaria : tarifasDiarias){
                    if(tarifaDiaria.getCategoria() == categoria && tarifaDiaria.getCapacidad() == cantidadMax){
                        tarifa = tarifaDiaria;
                    }
                }

                if(tipo == 'c'){
                    char tieneHidromasaje = lineaActual.substring(23,24).trim().charAt(0);
                    boolean hidromasaje = tieneHidromasaje == 's' ? true : false;
                    double porcentajeAdicional = Double.parseDouble(lineaActual.substring(25,30));
                    alojamientos.add(new Cabaña(id, area, tarifa, disponible, hidromasaje, porcentajeAdicional));
                }else{
                    String[] dispositivos = lineaActual.substring(23,50).trim().split(",");
					ArrayList<String> dispElectronicos = new ArrayList<String>();
                    for(String d : dispositivos){
                    	dispElectronicos.add(d);
                    }
                    alojamientos.add(new Suite(id, area, tarifa, disponible, dispElectronicos));
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(archivoEntrada != null){archivoEntrada.close();}
            }catch(Exception err){
                err.printStackTrace();
            }
        }
        return alojamientos;
    }

    public void modificarDisponibilidad(int idAlojamiento){
        File archivo = null;
        FileWriter archivoSalida = null;
        PrintWriter escritor = null;
        Scanner archivoEntrada = null;
        try{
            archivo = new File("../alojamientos.txt");
            archivoEntrada = new Scanner(archivo);
            StringBuilder contenidoArchivo = new StringBuilder();

            while(archivoEntrada.hasNextLine()){
                String lineaActual = archivoEntrada.nextLine();
                int id = Integer.parseInt(lineaActual.substring(0, 4).trim());
                if(id == idAlojamiento){
                    char disponibilidad = lineaActual.substring(21, 22).trim().charAt(0);
                    if(disponibilidad == 'd'){
                        lineaActual = lineaActual.substring(0, 21) + 'o' + lineaActual.substring(22);
                    }
                }
                contenidoArchivo.append(lineaActual).append("\n");
            }

            archivoSalida = new FileWriter(archivo);
            escritor = new PrintWriter(archivoSalida);

            escritor.write(contenidoArchivo.toString());
        }catch(IOException err){
            err.printStackTrace();
        }finally{
            try{
                if(archivoEntrada != null){archivoEntrada.close();}
            }catch(Exception err){
                err.printStackTrace();
            }
        }
    }
}
