package modelo.dao;

import javax.json.*;
import java.io.*;
import java.util.*;
import controlador.Controlador;
import modelo.*;

public class CamarotesJSON {
	public ArrayList<Camarote> leerCamarotes(){
		ArrayList<Camarote> camarotes = new ArrayList<>();
		File archivo = null;
		InputStream archivoEntrada = null;
		try {
			archivo = new File("camarotes.json");
			if(!archivo.exists()) {
				throw new FileNotFoundException();
			}
			archivoEntrada = new FileInputStream(archivo);
			
			JsonReader jr = Json.createReader(archivoEntrada);
			JsonArray ja = jr.readArray();
			
			for(JsonValue val : ja) {
				JsonObject obj = val.asJsonObject();
				ArrayList<String> com = new ArrayList<>();
				
				for(JsonValue v : obj.getJsonArray("comodidades")) {
					com.add( v.toString() );
				}
				
				Camarote c = new Camarote(
					obj.getInt("numero"),
					obj.getInt("piso"),
					obj.getJsonNumber("pesoMax").doubleValue(),
					com
				);
				
				camarotes.add(c);
			}
		}catch(IOException err) {
			err.printStackTrace();
		}finally {
			if(archivoEntrada!= null) {
				try {
					archivoEntrada.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return camarotes;
	}
}
