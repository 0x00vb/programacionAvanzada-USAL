package modeloDAO;
import java.io.*;
import java.util.Scanner;

import modelo.TarifaDiaria;

public class TarifasDiariasCSV {
    public TarifaDiaria[] leerTarifas(){
        TarifaDiaria[] tarifaDiarias = new TarifaDiaria[10];
        File archivo = null;
        Scanner archivoEntrada = null;
        try{
            archivo = new File("../tarifas.csv");
            archivoEntrada = new Scanner(archivo);
            int contador = 0;
            while(archivoEntrada.hasNext()){
                String lineaActual = archivoEntrada.nextLine();
                String[] linea = lineaActual.split(";");
                char categoria = linea[0].trim().charAt(0);
                int capacidad = Integer.parseInt(linea[1].trim());
                double tarifaDiaria = Double.parseDouble(linea[2].replaceAll("\"", "").trim());

                tarifaDiarias[contador] = new TarifaDiaria(capacidad, categoria, tarifaDiaria);
                contador++;
            }
        }catch(IOException e){  
            e.printStackTrace();
        }finally{
            try{
                if(archivoEntrada != null)archivoEntrada.close();
            }catch(Exception err){
                err.printStackTrace();
            }
        }

        return tarifaDiarias;
    }
}
