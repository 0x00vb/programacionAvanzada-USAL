package modelo.dao;
import modelo.*;
import controlador.*;
import javax.json.*;
import javax.json.stream.*;
import java.util.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MesasJSON {
	private Controlador c = new Controlador();
	public ArrayList<Mesa> leerMesas() throws EceptionP, ParseException {
		ArrayList<Mesa> mesas = new ArrayList<>();
		File archivo = null;
		InputStream archivoEntrada = null;
		try {
			archivo = new File("mesas.json");
			if(archivo.exists()) {
				archivoEntrada = new FileInputStream(archivo);
			}
			
			JsonReader jr = Json.createReader(archivoEntrada);
			JsonArray jsonArray = jr.readArray();
			
			for(JsonValue val : jsonArray) {
				JsonObject mesaO = val.asJsonObject();
				Mesa mesa = new Mesa();
				String nombreLoc = mesaO.getString("localidad");
				mesa.setNumero(mesaO.getInt("numero"));
				if(mesaO.getInt("numero") < 1) {
					throw new EceptionP();
				}
				mesa.setCantVotosA(mesaO.getInt("votosA"));
				mesa.setTipo(mesaO.getString("tipo").charAt(0));
				mesa.setLoc(c.buscarLoc(nombreLoc));
				String fechaStr = mesaO.getString("fecha");
				SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
				Date date = format.parse(fechaStr);
				Calendar f = Calendar.getInstance();
				f.setTime(date);
				mesa.setFecha(f);
				
				mesas.add(mesa);
			}
			
		}catch(IOException err) {
			err.printStackTrace();
		}
		
		return mesas;
	}
	
	public void escribirMesa(Mesa mesa) {
		File archivo = null;
		InputStream archivoEntrada = null;
		OutputStream archivoSalida = null;
		try {
			archivo = new File("mesas.json");
			archivoEntrada = new FileInputStream(archivo);
			JsonReader jr = Json.createReader(archivoEntrada);
			JsonArray jsonArray = jr.readArray();
			archivoEntrada.close();			
			
			SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
			String fechaStr = format.format(mesa.getFecha());
			
			JsonObject mesaObj = Json.createObjectBuilder()
					.add("numero", mesa.getNumero())
					.add("provincia", mesa.getLoc().getProv().getNombre())
					.add("localidad", mesa.getLoc().getNombre())
					.add("votos", mesa.getCantVotosA())
					.add("votos", mesa.getCantVotosB())
					.add("tipo", mesa.getTipo())
					.add("fecha", fechaStr)
					.build();
			
			JsonArray arrayBuilder = Json.createArrayBuilder(jsonArray)		
					.add(mesaObj)
					.build();
			
			archivoSalida = new FileOutputStream(archivo);
			JsonWriter jw = Json.createWriter(archivoSalida);
			jw.writeArray(arrayBuilder);			
			archivoSalida.close();
		}catch(IOException err) {
			err.printStackTrace();
		}
	}
	
	
}
