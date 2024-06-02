package modeloDAO;

import java.io.*;
import java.util.Scanner;

import modelo.Huesped;

public class HuespedesTXT{
    public Huesped[] leerHuespedes(){
        Huesped huespedes[] = new Huesped[20];
        File archivo = null;
        Scanner archivoEntrada = null;
        try{
            archivo = new File("../huespedes.txt");

            if(archivo.exists()){
                archivoEntrada = new Scanner(archivo);
            }else{
                throw new IOException();
            }

            int contador = 0;
            while(archivoEntrada.hasNextLine()){
                String lineaActual = archivoEntrada.nextLine();
                String[] contenidoLineaActual = lineaActual.split("|");

                huespedes[contador] = new Huesped(
                    Integer.parseInt(contenidoLineaActual[0]),
                    contenidoLineaActual[1],
                    contenidoLineaActual[2],
                    contenidoLineaActual[3]
                );
                contador++;
            }
        }catch(IOException err){
            err.printStackTrace();
        }finally{
            try{
                if(archivoEntrada != null){archivoEntrada.close();}
            }catch(Exception err){
                err.printStackTrace();
            }
        }

        return huespedes;
    }
    public void escribirHuesped(Huesped huesped){
        try{
            FileWriter archivo = new FileWriter("../huespedes.txt");
            PrintWriter archivoSalida = new PrintWriter(archivo);
        
            archivoSalida.println(
                huesped.getDocumento() + "|" +
                huesped.getNombre() + "|" +
                huesped.getApellido() + "|" +
                huesped.getPaisRecidencia()
            );

            archivoSalida.close();

        }catch(IOException err){
            err.printStackTrace();
        }
    }

}