package modeloDAO;
import javax.json.*;
import javax.json.stream.*;
import java.io.*;
import java.util.*;
import modelo.*;

public class CamarotesJSON {
	public ArrayList<Camarote> leerCamarotes() {
		ArrayList<Camarote> camarotes = new ArrayList<Camarote>();
		File archivo = null;
		InputStream archivoEntrada = null;
		try {
			archivo = new File("camarotes.json");
			if(archivo.exists()) {
				archivoEntrada = new FileInputStream(archivo);
			}
			
			JsonReader jsonReader = Json.createReader(archivoEntrada);
			JsonArray arrayCamarotes = jsonReader.readArray();
			
			for(JsonValue camaroteValue : arrayCamarotes) {
				JsonObject camaroteObj = camaroteValue.asJsonObject();
				Camarote camarote = new Camarote();
				ArrayList<String> comodidades = new ArrayList<>();
				camarote.setNum(camaroteObj.getInt("numero"));
				camarote.setPiso(camaroteObj.getInt("piso"));
				camarote.setPesoMax(camaroteObj.getJsonNumber("pesoMax").doubleValue());
				for(JsonValue c : camaroteObj.getJsonArray("comodidades")) {
					comodidades.add(c.toString());
				}
				camarote.setComodidades(comodidades);
				
				camarotes.add(camarote);
			}
			
			
			
		}catch(IOException err) {
			err.printStackTrace();
		}
		
		
		return camarotes;
	}
}
