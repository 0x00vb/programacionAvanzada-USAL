package modeloDAO;

import modelo.Auto;
import java.io.*;
import java.util.*;

public class AutoTXT {
	public static ArrayList<Auto> leerAutos(){
		ArrayList<Auto> autos = new ArrayList<Auto>();
		File archivo = null;
		Scanner archivoEntrada = null;
		try {
			archivo = new File("./auto.txt");
			if(archivo.exists()) {
				archivoEntrada = new Scanner(archivo);
			}else {
				throw new IOException();
			}
			
			while(archivoEntrada.hasNext()) {
				String lineaActual = archivoEntrada.nextLine();
				String patente = lineaActual.substring(0,8).trim();
				int anioPatentamiento = Integer.parseInt(lineaActual.substring(9,13).trim());
				double precio = Double.parseDouble(lineaActual.substring(14,lineaActual.length()).trim());
				
				autos.add(new Auto(patente, anioPatentamiento, precio));
			}
		}catch(IOException err) {
			err.printStackTrace();
		}finally {
			if(archivoEntrada != null) {
				archivoEntrada.close();
			}
		}
		return autos;
	}
	
	public static void cargarAuto(Auto auto) {
		File archivo = null;
		PrintWriter archivoSalida = null;
		try {
			archivo = new File("./auto.txt");
			archivoSalida = new PrintWriter(new FileWriter(archivo, true));
			
			archivoSalida.printf("%-7s %-4s %-8s%n", auto.getPatente(), auto.getAnio(), auto.getPrecioCompra());
			
		}catch(IOException err) {
			err.printStackTrace();
		}finally {
			if(archivoSalida != null) {
				archivoSalida.close();
			}
		}
	}
}
