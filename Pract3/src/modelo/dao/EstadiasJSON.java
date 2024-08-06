package modelo.dao;
import modelo.*;
import java.util.*;
import javax.json.*;
import javax.json.stream.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import controlador.Controlador;

public class EstadiasJSON {
	private  Controlador c = new Controlador();
	private  SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
	public  void escribirE(Estadia e) {
		File arch = null;
		InputStream archEnt = null;
		OutputStream archSal = null;
		try {
			arch = new File("esradias.json");
			if(arch.exists()) {
				archEnt = new FileInputStream(arch);
			}
			JsonReader jr = Json.createReader(archEnt);
			JsonArray ja = jr.readArray();
			archEnt.close();			
			
			JsonObject estObj = Json.createObjectBuilder()
					.add("num", e.getCochera().getNum())
					.add("pat", e.getVehiculo().getPatente())
					.add("tipo", e.getVehiculo().getTipo())
					.add("llaves", e.isDejaLlaves())
					.add("pago", e.isPagoAd())
					.add("fecha", format.format(e.getFecha()))
					.build();
					
			JsonArray arrayNuevo = Json.createArrayBuilder(ja)
				.add(estObj)
				.build();
			
			archSal = new FileOutputStream(arch);
			JsonWriter jw = Json.createWriter(archSal);
			jw.writeArray(arrayNuevo);
			archSal.close();
			
		}catch(IOException err) {
			err.printStackTrace();
		}
	}
	
	
	public  ArrayList<Estadia> leerE() {
		ArrayList<Estadia> est = new ArrayList<>();
		File arch = null;
		InputStream archEnt = null;
		try {
			arch = new File("estadias.json");
			archEnt = new FileInputStream(arch);
			JsonReader jr = Json.createReader(archEnt);
			JsonArray ja = jr.readArray();
			for(JsonValue v : ja) {
				JsonObject estO = v.asJsonObject();
				String[] fechaStr= estO.getString("fecha").split("/");
				Calendar f = Calendar.getInstance();
				f.set(
						Integer.parseInt(fechaStr[2]),
						Integer.parseInt(fechaStr[1]),
						Integer.parseInt(fechaStr[0])
				);
				
				Estadia e = new Estadia(
						c.buscarCochera(estO.getInt("num")),
						c.buscarVehiculo(estO.getInt("pat")),
						f,
						estO.getInt("horasE"),
						estO.getBoolean("llaves"),
						estO.getBoolean("pago")
				);
				
				est.add(e);
			}		
		}catch(IOException err) {
			err.printStackTrace();
		}
		
		return est;
	}
	
	
	
}
