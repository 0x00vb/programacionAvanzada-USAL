package modeloDAO;
import modelo.*;
import java.util.*;
import javax.json.*;
import javax.json.stream.*;

import controlador.Controlador;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AscensoresJSON {
	public ArrayList<Ascensor> leerAscensores() throws Excepcion, ParseException{
		Controlador controlador = new Controlador();
		ArrayList<Ascensor> ascensores = new ArrayList<>();
		File archivo = null;
		FileInputStream archivoEntrada = null;
		try {
			archivo = new File("ascensores.json");
			archivoEntrada = new FileInputStream(archivo);
			
			JsonReader jsonReader = Json.createReader(archivoEntrada);
			JsonArray jsonArray = jsonReader.readArray();
			
			for(JsonValue jsonValue : jsonArray) {
				JsonObject jsonObject = jsonValue.asJsonObject();
				String codigoStr = jsonObject.getString("codigo");
				int codigo = 0;
				try {
					codigo = Integer.parseInt(codigoStr);
				}catch(NumberFormatException err) {
					throw new Excepcion("");
				}
				String direc = jsonObject.getString("direccion");
				char tipo = jsonObject.getString("tipo").charAt(0);
				
				Ascensor ascensor = new Ascensor(codigo, direc, tipo);
				
				JsonArray arrayReparaciones = jsonObject.getJsonArray("reparaciones");
				for(JsonValue reparacionValue : arrayReparaciones) {
					JsonObject reparacionObject = reparacionValue.asJsonObject();
					SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");
					String fechaStr = reparacionObject.getString("fecha");
					Calendar fecha = Calendar.getInstance();
					fecha.setTime(formato.parse(fechaStr));

					Tecnico tecnico = controlador.buscarTecnico(reparacionObject.getJsonNumber("dniTec").intValue());
					
					ascensor.addReparacion(new Reparacion(
							fecha, 
							reparacionObject.getJsonNumber("costo").doubleValue(),
							reparacionObject.getBoolean("cambioLuces"),
							reparacionObject.getBoolean("cambioBotonera"),
							reparacionObject.getBoolean("mejoraEstructura"),
							tecnico
							));
					
					ascensores.add(ascensor);			
				}
			}
		}catch(IOException err) {
			err.printStackTrace();
		}
		
		return ascensores;
	}
	
	
//	public ArrayList<Ascensor> leerAscensoresParser() throws ParseException{
//		Controlador controlador = new Controlador();
//		ArrayList<Ascensor> ascensores = new ArrayList<>();
//		File archivo = null;
//		InputStream archivoEntrada = null;
//
//		try {
//			archivo = new File("ascensores.json");
//			archivoEntrada = new FileInputStream(archivo);
//
//			JsonParser jsonParser = Json.createParser(archivoEntrada);
//			
//			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
//			Ascensor ascensor = null;
//			String keyName = null;
//			
//			
//			while(jsonParser.hasNext()) {
//				Event event = jsonParser.next();
//				switch(event) {
//					case START_OBJECT:
//						if(ascensor == null) ascensor = new Ascensor();
//						break;
//					case END_OBJECT:
//						
//						break;
//					case KEY_NAME:
//						keyName = jsonParser.getString();
//						break;
//					case VALUE_STRING:
//						switch(keyName) {
//						case "direccion":
//							ascensor.setDireccionEdificio(jsonParser.getString());
//							break;
//						case "tipo":
//							ascensor.setTipo(jsonParser.getString().charAt(0));
//							break;
//						default:
//							break;
//						}
//					
//					case VALUE_NUMBER:
//						switch(keyName) {
//						case "codigo":
//							ascensor.setCodigo(jsonParser.getInt());
//							break;
//						case "dniTec":
//							ascensor.getReparaciones()
//						}
//						
//					default:
//						break;
//				}
//				
//			}			
//		}catch(IOException err) {
//			err.printStackTrace();
//		}
//		return ascensores;
//		
//		
//		
//	}

}
