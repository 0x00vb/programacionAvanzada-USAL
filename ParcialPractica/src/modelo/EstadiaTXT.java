package modelo;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class EstadiaTXT {
	public static void escribirEstadia(Estadia estadia) {
		File archivo = null;
		PrintWriter archivoSalida = null;
		try {
			archivo = new File("estadias.txt");
			archivoSalida = new PrintWriter(new FileWriter(archivo));
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
			String fecha = dateFormat.format(estadia.getFecha());
			archivoSalida.printf("%-10s %-3d %-8d %-2d %-5s", fecha, estadia.getCochera().getNumero(), estadia.getVehiculo().getPatente(), estadia.getCantHoras(), estadia.isPagoAdelantado(), estadia.isDejaLlaves());
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(archivoSalida != null)
				archivoSalida.close();
		}
	}
}
