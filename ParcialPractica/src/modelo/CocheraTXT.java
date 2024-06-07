package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CocheraTXT {
	public static ArrayList<Cochera> leerCocheras(){
		ArrayList<Cochera> cocheras = new ArrayList<>();
		ArrayList<Tarifa> tarifas = TarifasTXT.leerTarifas();
		File archivo = null;
		Scanner archivoEntrada = null;
		try {
			archivo = new File("cocheras.txt");
			if(archivo.exists()) {
				archivoEntrada = new Scanner(archivo);
			}else {
				throw new IOException();
			}
			
			while(archivoEntrada.hasNext()) {
				String lineaActual = archivoEntrada.nextLine();
				int numero = Integer.parseInt(lineaActual.substring(0, 5).trim());
				String tipo = lineaActual.substring(5, 15).trim();
				char disp = lineaActual.substring(15,16).charAt(0);
				String ubicacion = lineaActual.substring(16,25).trim();
				
				boolean disponible = disp == 's' ? true : false;
				
				Tarifa tarifa = new Tarifa();
				for(Tarifa t : tarifas) {
					if(t.getTipo() == tipo)
						tarifa = t;
				}
				
				cocheras.add(new Cochera(numero, disponible, ubicacion, tarifa));				
			}
			
		}catch(IOException err) {
			err.printStackTrace();
		}finally {
			if(archivoEntrada != null)
				archivoEntrada.close();
		}
		return cocheras;
	}
}
