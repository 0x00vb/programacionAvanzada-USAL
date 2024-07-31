package modelo.dao;
import java.util.*;

import javax.json.*;

import java.io.*;
import java.text.*;

import modelo.*;
public class LibrosJSON {
	File archivo = null;
	InputStream archivoEntrada = null;
	public ArrayList<Libro> leerLibros() throws ParseException {
		ArrayList<Libro> libros = new ArrayList<>();
		try {
			archivo = new File("Libros.json");
			if(!archivo.exists()) {
				throw new FileNotFoundException();
			}
			archivoEntrada = new FileInputStream(archivo);
			JsonReader jsonReader = Json.createReader(archivoEntrada);
			
			JsonArray librosArr = jsonReader.readArray();
			for(JsonValue value : librosArr) {
				JsonObject libroObj = value.asJsonObject();
				Calendar fecha = Calendar.getInstance();
				String[] fechaStr = libroObj.getString("fechaPublicacion").split("/");
				int año = Integer.parseInt(fechaStr[2]);
				int mes = Integer.parseInt(fechaStr[1]);
				int dia = Integer.parseInt(fechaStr[0]);
				fecha.set(año, mes - 1, dia);
				
				Libro libro = new Libro(
						libroObj.getInt("codigo"),
						libroObj.getString("titulo"),
						libroObj.getString("autor"),
						fecha
				);
				
				JsonArray prestArr = libroObj.getJsonArray("prestamos");
				for(JsonValue val : prestArr) {
					JsonObject prestObj = val.asJsonObject();
			        Calendar fecha1 = Calendar.getInstance();
			        Calendar fecha2 = Calendar.getInstance();
			        
			        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			        Date f1 = format.parse(prestObj.getString("fechaEmision"));
			        Date f2 = format.parse(prestObj.getString("fechaRegreso"));
			        
			        fecha1.setTime(f1);
			        fecha2.setTime(f2);					
					libro.addPrestamo(
							prestObj.getInt("dni"),
							fecha1,
							fecha2
							);
				}
				libros.add(libro);
			}
			
			
			
		}catch(IOException err) {
			err.printStackTrace();
		}
		
		
		return libros;
	}
}
