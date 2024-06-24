package modeloDAO;

import java.util.*;

import javax.json.*;
import javax.json.stream.JsonParser.*;
import java.io.*;
import java.text.SimpleDateFormat;

import modelo.*;

public class ExcursionesJSON {
	public void escribirExcursion(Excursion excursion, HashMap<Integer, String> participantes) {
		File archivo = null;
		FileOutputStream archivoSalida = null;
		String nombreArchivo = "./" + excursion.getEncargado().getNombre() + ".txt";
		try {
			archivo = new File(nombreArchivo);
			JsonArray excursiones;
			try (JsonReader reader = new Json.createReader(new FileReader(nombreArchivo))){
				excursiones = reader.readArray();
			}
			
			archivoSalida = new FileOutputStream(archivo);
			JsonWriter writer = new Json.createWriter(archivoSalida);
			
			JsonArrayBuilder participantesArrayJson = Json.createArrayBuilder();
			for(Map.Entry<Integer, String> entry : participantes.entrySet()) {
				JsonObjectBuilder participantesJson = Json.createObjectBuilder()
						.add("numeroCabaña", entry.getKey())
						.add("nombreHuesped", entry.getValue());
				
			}
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
			String fecha = dateFormat.format(Calendar.getInstance());
			
			JsonObject excursionJson = Json.createObjectBuilder()
					.add("nombreExcursion", excursion.getNombre())
					.add("fecha", fecha)
					.add("participantes", participantesArrayJson)
					.build();
			
			excursiones.add(excursionJson);
			
		}catch(IOException err) {
			err.printStackTrace();
		}finally {
			if(archivoSalida != null) {
				archivoSalida.close();
			}
		}
	}
}
