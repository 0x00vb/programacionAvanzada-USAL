package modelo.dao;

import javax.json.*;
import java.io.*;
import java.text.*;
import java.util.Calendar;


public class EstadisticasJSON {
    public static void escribirJson(String resp, Calendar fecha){
        File archivo = null;
        FileWriter fileWriter = null;
        JsonWriter jsonWriter = null;
        try{
            archivo = new File("estadisticas.json");
            fileWriter = new FileWriter(archivo);
            jsonWriter = Json.createWriter(fileWriter);
            jsonWriter.writeObject();

        }catch(IOException e){
            e.printStackTrace();
        }
    }   
}
