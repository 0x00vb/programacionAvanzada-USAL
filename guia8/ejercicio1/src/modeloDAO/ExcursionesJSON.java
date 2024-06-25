package modeloDAO;

import java.util.*;

import javax.json.*;
import javax.json.stream.JsonParser.*;
import java.io.*;
import java.text.SimpleDateFormat;

import modelo.*;

public class ExcursionesJSON {
	public void escribirExcursion(Excursion excursion, Estadia estadia, InscripcionExcursiones inscripcion) {
		File archivo = null;
		OutputStream archivoSalida = null;
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
			for(Huesped h : inscripcion.getHuespedes()) {
				participantesArrayJson.add(h.getNombre());
			}
			
			JsonObject participantesJson = Json.createObjectBuilder()
					.add("numeroCabaña", estadia.getAlojamiento().getId())
					.add("participantes", participantesArrayJson)
					.build();
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
			String fecha = dateFormat.format(Calendar.getInstance());
			
			JsonObject excursionJson = Json.createObjectBuilder()
					.add("nombreExcursion", excursion.getNombre())
					.add("fecha", fecha)
					.add("participantes", participantesJson)
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
