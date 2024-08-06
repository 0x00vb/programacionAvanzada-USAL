package modelo.dao;

import javax.json.*;
import javax.json.stream.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import controlador.Controlador;
import modelo.Evento;

public class EventosJSON {
	public void escribirEvento(Evento e) throws IOException {
		File archivo = null;
		InputStream archivoEntrada = null;
		OutputStream archivoSalida = null;
		try {
			archivo = new File("eventos.json");
			archivoEntrada = new FileInputStream(archivo);
			JsonReader jr = Json.createReader(archivoEntrada);
			
			JsonArray jsonArray = jr.readArray();
			archivoEntrada.close();
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
			String fechaStr = format.format(e.getFecha());
			JsonObject reservaObj = Json.createObjectBuilder()
					.add("nombre", e.getNombre())
					.add("fecha", fechaStr)
					.add("ubicacion", e.getUbicacion())
					.add("tipo", e.getTipo())
					.build();
			
			JsonArray nuevoArray = Json.createArrayBuilder(jsonArray)
					.add(reservaObj)
					.build();
			
			JsonWriter jw = Json.createWriter(archivoSalida);
			jw.write(nuevoArray);
			
		}catch(IOException err) {
			err.printStackTrace();
		}finally {
			if(archivo.exists()) {
				archivoSalida.close();
			}
		}
	}
}
