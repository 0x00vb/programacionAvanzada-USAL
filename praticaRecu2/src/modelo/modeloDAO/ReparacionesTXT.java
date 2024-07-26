package modelo.modeloDAO;

import java.util.*;
import java.io.*;
import modelo.*;

public class ReparacionesTXT {
	public void cargarDatos(int numA, int dni, boolean cambioLuces, boolean cambioBotonera, boolean mejoraInfraestructura) {
		File archivo = null;
		PrintWriter archivoSalida = null;
		try {
			archivo = new File("reparaciones.txt");
			archivoSalida = new PrintWriter( new FileWriter(archivo, true) );
			 
			archivoSalida.printf("%-10d %-10d %-5b %-5b %-5b", numA, dni, cambioLuces, cambioBotonera, mejoraInfraestructura);
			
			
		}catch(IOException err) {
			err.printStackTrace();
		}
	}
}
