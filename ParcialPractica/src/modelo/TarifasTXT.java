package modelo;

import java.io.*;
import java.util.*;

public class TarifasTXT {
	
	public static ArrayList<Tarifa> leerTarifas() {
		ArrayList<Tarifa> tarifas = new ArrayList<>();
		File archivo = null;
		Scanner archivoEntrada = null;
		try {
			archivo = new File("tarifas.txt");
			archivoEntrada = new Scanner(archivo);
			
			while(archivoEntrada.hasNext()) {
				String lineaActual = archivoEntrada.nextLine();
				String[] partes = lineaActual.split("\t");
				
				
				
				
			}
			
		}catch(IOException err) {
			err.printStackTrace();
		}
		return tarifas;
	}
}
