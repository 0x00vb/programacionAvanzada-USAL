package modeloDAO;

import java.io.*;
import java.util.*;

import modelo.*;

public class ExcursionesTXT {
	public ArrayList<Excursion> leerExcurciones(){
		ArrayList<Excursion> excursiones = new ArrayList<Excursion>();
		File archivo = null;
		Scanner archivoEntrada = null;
		try {
			archivo = new File("./Excursiones.txt");
			if(!archivo.exists())
				throw new FileNotFoundException();
			
			archivoEntrada = new Scanner(archivo);
			
			while(archivoEntrada.hasNext()) {
				String lineaActual = archivoEntrada.nextLine();
				String[] contenidoLineaActual = lineaActual.split(",");
				
				String nombre = contenidoLineaActual[0];
				int diaSemana = Integer.parseInt(contenidoLineaActual[1]);
				String nombreResponsable = contenidoLineaActual[2];
				String emailResponsable = contenidoLineaActual[3];
				EncargadoExcursion encargado = new EncargadoExcursion(nombreResponsable, emailResponsable);
				int duracion = Integer.parseInt(contenidoLineaActual[4]);
				double costo = Double.parseDouble(contenidoLineaActual[5]);
				int cantMin = Integer.parseInt(contenidoLineaActual[6]);
				int cantMax = Integer.parseInt(contenidoLineaActual[7]);

				excursiones.add(new Excursion(nombre, diaSemana, encargado, duracion, costo, cantMin, cantMax));	
			}			
			
		}catch(IOException err) {
			err.printStackTrace();
		}finally {
			if(archivoEntrada != null) {
				archivoEntrada.close();
			}
		}
		
		return excursiones;
	}
}
