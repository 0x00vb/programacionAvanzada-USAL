package modelo.modeloDAO;

import java.io.FileWriter;
import java.io.PrintWriter;

public class ArchivoTXT {
	
	public static void guardarEnTxt(String a) {
		try (FileWriter fw = new FileWriter("archivo.txt", true);
	             PrintWriter pw = new PrintWriter(fw)) {
	            pw.println(a);
	        } catch (Exception e) {
	            System.err.println("Error al escribir en el archivo: " + e.getMessage());
	        }
	}

	
}
