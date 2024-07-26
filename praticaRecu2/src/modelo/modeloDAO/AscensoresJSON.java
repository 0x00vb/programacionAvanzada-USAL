package modelo.modeloDAO;

import javax.json.*;
import java.util.*;
import java.io.*;
import modelo.*;
import controlador.Controlador;

public class AscensoresJSON {
	Controlador c = new Controlador();
	public ArrayList<Ascensor> leerAscensores() throws FileNotFoundException {
		ArrayList<Ascensor> ascensores = new ArrayList<Ascensor>();
		File archivo = null;
		InputStream archivoEntrada = null;
		try {
			archivo = new File("ascensores.json");
			if(!archivo.exists()) {
				throw new FileNotFoundException();
			}
			archivoEntrada = new FileInputStream(archivo);
			JsonReader jsonReader = Json.createReader(archivoEntrada);
	
			JsonArray AscensorObjArr = jsonReader.readArray();
			for(JsonValue value : AscensorObjArr) {
				JsonObject ascensorObj = value.asJsonObject();
				Ascensor ascensor = new Ascensor(
						ascensorObj.getInt("codigo"),
						ascensorObj.getString("direccionEdificio"),
						ascensorObj.getString("tipo")
				);
				
				JsonArray reparacionesArr = ascensorObj.getJsonArray("reparaciones");
				for(JsonValue r : reparacionesArr) {
					JsonObject repObj = r.asJsonObject();
					
					Tecnico t = c.buscarTecnico(repObj.getInt("dniTecnico"));
					Calendar fecha = Calendar.getInstance();
					String[] fechaStr = repObj.getString("fecha").split("/");
					int dia = Integer.parseInt(fechaStr[0]);
					int mes = Integer.parseInt(fechaStr[1]);
					int año = Integer.parseInt(fechaStr[2]);
					fecha.set(año, mes -1, dia);
					ascensor.addReparacion(t, fecha, 
							repObj.getBoolean("luces"),
							repObj.getBoolean("botonera"),
							repObj.getBoolean("infra")
							);
				}
				ascensores.add(ascensor);
			}
			
	}catch(IOException err) {
			err.printStackTrace();
	}finally {
		if(archivoEntrada != null) {
			try {
				archivoEntrada.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
		return ascensores;
	}
}
