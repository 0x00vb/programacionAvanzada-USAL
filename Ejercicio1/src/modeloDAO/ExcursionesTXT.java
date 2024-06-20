package modeloDAO;

import java.io.*;
import java.util.*;

import modelo.Excursion;

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
				
			}
			
			
			
		}catch(IOException err) {
			err.printStackTrace();
		}finally {
			if(archivoEntrada != null) {
				archivoEntrada.close();
			}
		}
		
		return null;
	}
}
