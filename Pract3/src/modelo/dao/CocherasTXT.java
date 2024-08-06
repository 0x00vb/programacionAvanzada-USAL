package modelo.dao;
import java.util.*;
import java.io.*;
import modelo.*;

public class CocherasTXT {
	public ArrayList<Cochera> leerCocheras() throws FileNotFoundException{
		ArrayList<Cochera> cocheras = new ArrayList<>();
		File archivo = new File("cocheras.txt");
		Scanner archivoEntrada = new Scanner(archivo);
		
		while(archivoEntrada.hasNext()) {
			String l = archivoEntrada.nextLine();
			
			int num = Integer.parseInt(l.substring(0,10).trim());
			char tipo = l.substring(10,12).charAt(0);
			
			
		}
		
		
		return cocheras;
	}
}
