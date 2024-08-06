package modelo.dao;

import modelo.*;
import java.util.*;
import javax.json.*;
import java.io.*;


public class VehiculosJSON {
	public ArrayList<Vehiculo> leerVehiculos(){
		File archivo = null;
		InputStream archivoEntrada = null;
		ArrayList<Vehiculo> vehiculos = new ArrayList<>();
		try {
			archivo = new File("vehiculos.json");
			if(!archivo.exists()) {
				throw new FileNotFoundException();
			}
			archivoEntrada = new FileInputStream(archivo);
			JsonReader jr = Json.createReader(archivoEntrada);
			JsonArray jArray = jr.readArray();
			for(JsonValue val : jArray) {
				JsonObject obj = val.asJsonObject();
				Vehiculo v = new Vehiculo(
						obj.getString("modelo"),
						obj.getString("tipo").charAt(0),
						obj.getInt("capacidad"),
						obj.getBoolean("gps"),
						obj.getBoolean("sillaBebes"),
						obj.getJsonNumber("tarifaDiaria").doubleValue()
				);
				vehiculos.add(v);
			}
			
			
		}catch(IOException err) {
			err.printStackTrace();
		}
		return vehiculos;
	}
}
