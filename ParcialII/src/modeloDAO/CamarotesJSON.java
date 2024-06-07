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
}
