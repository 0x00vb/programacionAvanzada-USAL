package modelo.dao;
import javax.json.*;
import java.io.*;
import java.util.*;
import controlador.Controlador;
import modelo.*;
public class EstadiasJSON {
	public void escribirEstadia(Estadia e){
		File archivo = null;
		InputStream archivoEntrada = null;
		OutputStream archivoSalida = null;
		try {
			archivo = new File("estadias.json");
			archivoEntrada = new FileInputStream(archivo);
			JsonReader jr = Json.createReader(archivoEntrada);
			JsonArray arrayViejo = jr.readArray();
			archivoEntrada.close();
			
			JsonObjectBuilder obj = Json.createObjectBuilder()
					.add("numero", e.getCamarote().getNumero())
					.add("nombre", e.getPasajero().getNombre())
					.add("apellido", e.getPasajero().getApellido());
			
			JsonArrayBuilder arrayEq = Json.createArrayBuilder();
			
			for(Equipaje eq : e.getEquipaje()) {
				JsonObject eqObj = Json.createObjectBuilder()
						.add("codigo", eq.getCodigo())
						.add("tipo", eq.getTipo())
						.add("peso", eq.getPeso())
						.build();
				arrayEq.add(eqObj);
			}
			
			obj.add("Equipaje", arrayEq);
			
			JsonArrayBuilder nuevoArray = Json.createArrayBuilder(arrayViejo);
			nuevoArray.add(obj);
			
			archivoSalida = new FileOutputStream(archivo);
			JsonWriter jw = Json.createWriter(archivoSalida);
			jw.write( nuevoArray.build() );
			
			archivoSalida.close();
			
			
		}catch(IOException err) {
			err.printStackTrace();
		}
		
	}
}
