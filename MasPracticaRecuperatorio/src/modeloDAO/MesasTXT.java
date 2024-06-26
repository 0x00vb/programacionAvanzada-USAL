package modeloDAO;
import modelo.*;
import controlador.*;

import java.io.*;
import java.text.*;
import java.util.*;

import controlador.ExcepcionPropia;

public class MesasTXT {
	private  Controlador c = new Controlador();
	
	public  ArrayList<Mesa> leerMesas() throws ExcepcionPropia{
		ArrayList<Mesa> mesas = new ArrayList<>();
		File archivo = null;
		Scanner archivoEntrada = null;
		try {
			archivo = new File("./mesas.txt");
			archivoEntrada = new Scanner(archivo);
			
			while(archivoEntrada.hasNext()) {
				String lineaActual = archivoEntrada.nextLine();
				
				String numStr = lineaActual.substring(0,5).trim();
				int num = 0;
				try {
					num = Integer.parseInt(numStr);
				}catch(NumberFormatException err) {
					throw new ExcepcionPropia("");					
				}
				String nomProv = lineaActual.substring(5,15).trim();
				String nomLoc = lineaActual.substring(15, 25).trim();
				int cantVotA = Integer.parseInt(lineaActual.substring(25, 28).trim());
				int cantVotB = Integer.parseInt(lineaActual.substring(28, 30).trim());
				char tipo = lineaActual.substring(30,32).trim().charAt(0);
				String fechaStr = lineaActual.substring(32, 40).trim();
				
	            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
	            Calendar fecha = Calendar.getInstance();
				fecha.setTime(dateFormat.parse(fechaStr));
				
				Mesa mesa = new Mesa(num, c.buscarLocalidad(nomLoc), cantVotA, cantVotB, tipo, fecha);
				
				mesas.add(mesa);
			}
			
		}catch(IOException | ParseException err) {
			err.printStackTrace();
		}
		
		
		return mesas;
	}
	
	public void escribirMesas(Mesa mesa) {
		File archivo = null;
		PrintWriter archivoSalida = null;
		try {
			archivo = new File("./mesas.txt");
			archivoSalida = new PrintWriter(new FileWriter(archivo, true));
			
			SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
			String fecha = format.format(mesa.getFecha());
			
			archivoSalida.printf("\"%-4d %-15s %-15s %-3d %-3d %-10s %-10s", 
					mesa.getNumero(),
					mesa.getLocalidad().getProvincia().getNombre(),
					mesa.getLocalidad().getNombre(),
					mesa.getCantVotosA(),
					mesa.getCantVotosB(),
					mesa.getTipo(),
					fecha
					);
			
		}catch(IOException err) {
			err.printStackTrace();
		}finally {
			if(archivoSalida != null) {
				archivoSalida.close();
			}
		}
	}
	
	
	
}
