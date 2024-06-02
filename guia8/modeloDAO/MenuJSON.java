package modeloDAO;

import javax.json.*;
import java.io.*;

import modelo.Menu;

public class MenuJSON {
    public Menu[] leerMenus(){
        Menu[] menu = new Menu[10];
        File archivo = null;
        FileInputStream archivoEntrada = null;
        try{
            archivo = new File("../menu.json");
            archivoEntrada = new FileInputStream(archivo);
            int contador = 0;
            JsonReader jsonReader = Json.createReader(archivoEntrada);
            JsonObject jsonObject = jsonReader.readObject();

            for(String tipo : jsonObject.keySet()){
                JsonArray jsonArray = jsonObject.getJsonArray(tipo);
                for(JsonObject jsonObject2 : jsonArray.getValuesAs(JsonObject.class)){
                    menu[contador] = new Menu(
                        tipo,
                        jsonObject2.getString("nombre"),
                        jsonObject2.getJsonNumber("precio").doubleValue(),
                        jsonObject2.getString("ingredientes").split(", "),
                        jsonObject2.getBoolean("paraCompartir")
                    );
                    contador++;
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
        
        return menu;
    }
}
