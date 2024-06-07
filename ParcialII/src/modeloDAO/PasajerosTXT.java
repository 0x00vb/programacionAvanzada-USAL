package modeloDAO;
import java.util.*;

import modelo.*;

import java.io.*;
import java.text.SimpleDateFormat;

public class PasajerosTXT {
	public ArrayList<Pasajero> leerPasajeros(){
		ArrayList<Pasajero> pasajeros = new ArrayList<Pasajero>();
		File archivo = null;
		
		
		return pasajeros;
	}
	
	public void escribirPasajero(Pasajero pasajero) {
		File archivo = null;
		PrintWriter archivoSalida = null;
		try {
			archivo = new File("./Pasajeros.txt");
			archivoSalida = new PrintWriter(new FileWriter(archivo));
			
			int numCamarote = pasajero.getCamarote().getNumero();
			String nombre = pasajero.getNombre();
			String apellido = pasajero.getApellido();	
			Calendar fecha = pasajero.getFechaRegistro();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
			String fechaFormateada = dateFormat.format(fecha);
			ArrayList<Equipaje> equipaje = pasajero.getEquipaje();
			String infoEquipajes = "";
			for(Equipaje e : equipaje) {
				infoEquipajes += e.getTipo() + " ";
				infoEquipajes += e.getPeso() + " ";
				
			}
			int codigoID = pasajero.getCodigoID();
			
			archivoSalida.printf("%-10d %-15s %-15s %-10s %-100s %-10d", numCamarote, nombre, apellido, fechaFormateada, infoEquipajes, codigoID);
			
		}catch(IOException err) {
			err.printStackTrace();
		}finally {
			if(archivoSalida != null)
				archivoSalida.close();
		}
	}
}
