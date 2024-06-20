package modeloDAO;

import javax.json.*;
import java.io.*;
import java.util.ArrayList;

import modelo.Menu;

public class MenuJSON {
    public ArrayList<Menu> leerMenus(){
    	ArrayList<Menu> menu = new ArrayList<Menu>();
        File archivo = null;
        FileInputStream archivoEntrada = null;
        try{
            archivo = new File("../menu.json");
            archivoEntrada = new FileInputStream(archivo);
            JsonReader jsonReader = Json.createReader(archivoEntrada);
            JsonObject jsonObject = jsonReader.readObject();

            for(String tipo : jsonObject.keySet()){
                JsonArray jsonArray = jsonObject.getJsonArray(tipo);
                for(JsonObject jsonObject2 : jsonArray.getValuesAs(JsonObject.class)){
                	ArrayList<String> ingredientes = new ArrayList<String>();
                	for(String s: jsonObject2.getString("ingredientes").split(", ")) {
                		ingredientes.add(s);
                	}
                    menu.add(new Menu(
                        tipo,
                        jsonObject2.getString("nombre"),
                        jsonObject2.getJsonNumber("precio").doubleValue(),
                        ingredientes,
                        jsonObject2.getBoolean("paraCompartir")
                    ));
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
