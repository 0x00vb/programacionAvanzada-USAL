package modeloDAO;

import modelo.*;

import java.io.*;
import java.util.*;

public class MantenimientoTXT {
	public void leerMantenimientos(Auto auto){
		File archivo = null;
		Scanner archivoEntrada = null;
		try {
			archivo = new File("./mantenimiento.txt");
			if(archivo.exists()) {
				archivoEntrada = new Scanner(archivo);
			}else {
				throw new IOException();
			}
			
			while(archivoEntrada.hasNext()) {
				String lineaActual = archivoEntrada.nextLine();
				String[] partes = lineaActual.split(";");
				
				String patente = partes[0];
				char tipo = partes[1].charAt(0);
				String fechaStr = partes[2];
				int anio = Integer.parseInt(fechaStr.substring(0,4));
				int mes = Integer.parseInt(fechaStr.substring(4, 6));
				int dia = Integer.parseInt(fechaStr.substring(6, 8));
				Calendar fecha = Calendar.getInstance();
				fecha.set(anio, mes, dia);
				double costo = Double.parseDouble(partes[3]);
				
				if(patente.equals(auto.getPatente())) {
					auto.agregarMantenimiento(tipo, fecha, costo);
				}
			}
			
		}catch(IOException err) {
			err.printStackTrace();
		}finally {
			if(archivoEntrada != null) {
				archivoEntrada.close();
			}
		}
	}
}
