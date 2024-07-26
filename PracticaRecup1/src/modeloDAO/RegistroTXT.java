package modeloDAO;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import modelo.*;

public class RegistroTXT {
	public void escribirEstadia(Estadia estadia) {
		File archivo = null;
		PrintWriter archivoSalida = null;
		try {
			archivo = new File("Registro.txt");
			archivoSalida = new PrintWriter( new FileWriter(archivo) );
			
			SimpleDateFormat formater = new SimpleDateFormat("dd/mm/yyyy");
			String dateStr = formater.format(estadia.getFechaRegistro());
			String equipajeStr = "";
			for(Equipaje e : estadia.getEquipaje()) {
				equipajeStr += e.getTipo();
				equipajeStr += e.getPeso();
			}
			archivoSalida.print(estadia.getCamarote().getNum() + " " + estadia.getPasajero().getNombre() + " " + estadia.getPasajero().getApellido() + " " + dateStr + " " + equipajeStr + " " + estadia.getId());
		
		}catch(IOException err) {
			err.printStackTrace();
		}
	}
}
