package modeloDAO;
import modelo.*;
import javax.json.*;
import javax.json.stream.*;
import javax.json.stream.JsonParser.*;
import java.io.*;
import java.util.*;

public class CamarotesJSON {
	public static ArrayList<Camarote> leerCamarotes(){
		ArrayList<Camarote> camarotes = new ArrayList<Camarote>();
		try {
			InputStream inputStream = new FileInputStream("./Camarotes.json");
			JsonParser objJsonParser = Json.createParser(inputStream);
			
			Camarote nuevoCamarote = null;
			ArrayList<String> comodidades = null;
			String key = null;
			while(objJsonParser.hasNext()) {
				Event event = objJsonParser.next();
				switch(event) {
				case START_OBJECT:
					nuevoCamarote = new Camarote();
					break;
				case END_OBJECT:
					camarotes.add(nuevoCamarote);
					break;
				case START_ARRAY:
					if("comodidades".equals(key)) {
						comodidades = new ArrayList<String>();
					}
					break;
				case END_ARRAY:
					nuevoCamarote.setComodidades(comodidades);
					break;
				case KEY_NAME:
					key = objJsonParser.getString();
					break;
				case VALUE_STRING:
					if("comodidades".equals(key))
						comodidades.add(objJsonParser.getString());
					break;
				case VALUE_NUMBER:
					switch(key) {
						case "numeroCamarote":
							nuevoCamarote.setNumero(objJsonParser.getInt());
							break;
						case "piso":
							nuevoCamarote.setPiso(objJsonParser.getInt());
							break;
						case "pesoMaximoPermitido":
							nuevoCamarote.setPesoMax(objJsonParser.getInt());
							break;
					}
					break;
				
				default:	
					break;
				}
			}
			
			
		}catch(IOException err) {
			err.printStackTrace();
		}
		for (Camarote c : camarotes) {
            System.out.println(c.getNumero());
        }

		return camarotes;
	}
	
	public ArrayList<Camarote> leerCamarotesNormal(){
		ArrayList<Camarote> camarotes = new ArrayList<Camarote>();
		try {
			File archivo = new File("camarotes.json");
			InputStream archivoEntrada = new FileInputStream(archivo);
			
			JsonReader jsonReader = Json.createReader(archivoEntrada);
			JsonArray arrayCamarotes = jsonReader.readArray();
			
			for(JsonValue camaroteValue : arrayCamarotes) {
				JsonObject camaroteObj = camaroteValue.asJsonObject();
				Camarote camarote = new Camarote();
				camarote.setNumero(camaroteObj.getInt("numero"));
				camarote.setPiso(camaroteObj.getInt("piso"));
				camarote.setPesoMax(Double.parseDouble(camaroteObj.getString("pesoMax")));
				
				JsonArray arrayComodidades = camaroteObj.getJsonArray("comodidades");
				ArrayList<String> comodidades = new ArrayList<String>();
				for(JsonValue cVal : arrayComodidades) {
					comodidades.add(cVal.toString());
				}
				camarote.setComodidades(comodidades);
				camarotes.add(camarote);
			}			
		}catch(IOException err) {
			err.printStackTrace();
		}
		return camarotes;
	}
	
	public void escribirNormal(Camarote camarote) {
		try {
			File archivo = new File("camartoes.json");
			InputStream archivoEntrada = new FileInputStream(archivo);
			
			JsonReader reader = Json.createReader(archivoEntrada);
			JsonArray oldJsonArray = reader.readArray();
			reader.close();
			archivoEntrada.close();
			
			JsonArrayBuilder arrayComodidades = Json.createArrayBuilder();
			for(String c : camarote.getComodidades()) {
				arrayComodidades.add(c);
			}
			
			
			JsonObject camaroteObj = Json.createObjectBuilder()
					.add("numero", camarote.getNumero())
					.add("piso", camarote.getPiso())
					.add("pesoMax", camarote.getPesoMax())
					.add("comodidades", arrayComodidades)
					.build();
			
			JsonArray newJsonArray = Json.createArrayBuilder(oldJsonArray)
				.add(camaroteObj)
				.build();
			
			OutputStream archivoSalida = new FileOutputStream(archivo);
			JsonWriter writer = Json.createWriter(archivoSalida);
			writer.write(newJsonArray);
			writer.close();
			archivoSalida.close();
		}catch(IOException err) {
			err.printStackTrace();
		}
	}
	
	
	
}
