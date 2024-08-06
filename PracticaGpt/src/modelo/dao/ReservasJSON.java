package modelo.dao;
import modelo.*;
import java.util.*;
import javax.json.*;
import java.io.*;
import java.text.SimpleDateFormat;
public class ReservasJSON {
	public void escribirReservas(Reserva r) throws IOException {
		File archivo = null;
		InputStream archivoEntrada = null;
		OutputStream archivoSalida = null;
		try {
			archivo = new File("reservas.json");
			archivoEntrada = new FileInputStream(archivo);
			JsonReader jr = Json.createReader(archivoEntrada);
			JsonArray arrayOriginal = jr.readArray();
			archivoEntrada.close();
			
			archivoSalida = new FileOutputStream(archivo);
			SimpleDateFormat f = new SimpleDateFormat("dd7mm/yyyy");
			String fechaInicio = f.format(r.getFechaInicio());
			String fechaFin = f.format(r.getFechaFin());
			
			JsonObject newObj = Json.createObjectBuilder()
					.add("numReserva", r.getNumReserva())
					.add("nombre", r.getCliente().getNombre())
					.add("apellido", r.getCliente().getApellido())
					.add("inicio", fechaInicio)
					.add("fin", fechaFin)
					.add("gps", r.getVehiculo().isGps())
					.add("sillaBebes", r.getVehiculo().isSillaBebes())
					.build();
			
			JsonArray nuevoArray = Json.createArrayBuilder(arrayOriginal)
					.add(newObj)
					.build();
			
			JsonWriter jw = Json.createWriter(archivoSalida);
			jw.write(nuevoArray);
			
		}catch(IOException err) {
			err.printStackTrace();
		}finally {
			if(archivoSalida != null) {
				archivoSalida.close();
			}
		}
	}
}
