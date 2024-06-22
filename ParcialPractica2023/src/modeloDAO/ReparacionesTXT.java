package modeloDAO;
import modelo.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReparacionesTXT {
	public void escribirReparacion(Reparacion reparacion, int numeroAscensor) {
		File archivo = null;
		PrintWriter archivoSalida = null;
		try {
			archivo = new File("reparaciones.txt");
			archivoSalida = new PrintWriter(new FileWriter(archivo, true));			
			
			Calendar fecha = reparacion.getFecha();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
			String fechaStr = dateFormat.format(fecha);
			
			archivoSalida.printf("%-10d %-10s %-10s %-10s %-10s %-10s", 
					numeroAscensor, 
					fechaStr,
					reparacion.getCosto(),
					reparacion.isCambioLuces(),
					reparacion.isCambioBotonera(),
					reparacion.isMejoraEstructura(),
					reparacion.getTecnico().getDni()
					);
			
		}catch(IOException err) {
			err.printStackTrace();
		}
		
	}
}
