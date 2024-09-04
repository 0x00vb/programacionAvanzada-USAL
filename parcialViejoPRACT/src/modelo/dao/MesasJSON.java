package modelo.dao;

import javax.json.*;

import controlador.Controlador;

import java.io.*;
import java.util.*;
import java.text.*;
import modelo.*;

public class MesasJSON {
	private SimpleDateFormat f = new SimpleDateFormat("dd/mm/yyyy");
	private Controlador c = new Controlador();
	public void escribirMesa(Mesa m) {
		File archivo = null;
		InputStream archivoEntrada = null;
		OutputStream archivoSalida = null;
		
		try {
			archivo = new File("mesas.json");
			if(!archivo.exists()) {
				throw new FileNotFoundException();
			}
			archivoEntrada = new FileInputStream(archivo);
			JsonReader jr = Json.createReader(archivoEntrada);
			JsonArray viejoArray = jr.readArray();
			archivoEntrada.close();
			
			String fechaStr = f.format( m.getFecha() );
			
			JsonObject nuevoObj = Json.createObjectBuilder()
					.add("numero", m.getNum())
					.add("provincia", m.getLocalidad().getProv().getNombre())
					.add("localidad", m.getLocalidad().getNombre())
					.add("votos a", m.getVotosA())
					.add("votos b", m.getVotosB())
					.add("fecha", fechaStr)
					.add("tipo", m.getTipo())
					.build();
			
			JsonArray nuevoArray = Json.createArrayBuilder(viejoArray)
					.add(nuevoObj)
					.build();
			
			archivoSalida = new FileOutputStream(archivo);
			JsonWriter jw = Json.createWriter(archivoSalida);
			jw.write(nuevoArray);			
			
		}catch(IOException err) {
			err.printStackTrace();
		}finally {
			if(archivoSalida != null) {
				try {
					archivoSalida.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public ArrayList<Mesa> leerMesas(){
		ArrayList<Mesa> mesas = new ArrayList<>();
		File archivo = null;
		InputStream archivoEntrada= null;
		try {
		
			archivo = new File("mesas.txt");
			archivoEntrada = new FileInputStream(archivo);
			
			JsonReader jr = Json.createReader(archivoEntrada);
			JsonArray array = jr.readArray();
			
			for(JsonValue val : array) {
				JsonObject obj = val.asJsonObject();
				
				int numero = 0, votA = 0, votB = 0;
				
				try {
					numero = obj.getInt("numero");
					votA = obj.getInt("votos a");
					votB = obj.getInt("votos b");
				}catch(NumberFormatException err) {
					err.printStackTrace();
				}
				
				Localidad l = c.buscarLoc(obj.getString("localidad"), obj.getString("provincia"));
				
				Calendar fecha = Calendar.getInstance();
				try {
					fecha.setTime( f.parse(obj.getString("fecha")) );
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				Mesa m = new Mesa(
						numero,
						l,
						votA,
						votB,
						obj.getString("tipo").charAt(0),
						fecha
						
				);
				mesas.add(m);
			}
			
		}catch(IOException err) {
			err.printStackTrace();
		}
		
		return mesas;
	}
}
