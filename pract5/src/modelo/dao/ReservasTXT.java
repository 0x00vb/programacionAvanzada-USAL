package modelo.dao;

import modelo.*;
import java.util.*;
import java.io.*;
import controlador.Controlador;

public class ReservasTXT {
	private Controlador c = new Controlador();
	public void escribirReserva(Reserva r) {
		File archivo = null;
		PrintWriter archivoSalida = null;
		try {
			archivo = new File("reservas.txt");
			archivoSalida = new PrintWriter( new FileWriter(archivo, true) );
			
			archivoSalida.printf("%-10d %-12s %-3d", r.getCliente().getDni(), r.getEvento().getNombre(), r.getCantEntradas());
			
		}catch(IOException err) {
			err.printStackTrace();
		}finally {
			if(archivoSalida != null) {
				archivoSalida.close();
			}
		}
		
	}
	public ArrayList<Reserva> leerReservas() {
		ArrayList<Reserva> reservas = new ArrayList<>();
		File archivo = null;
		Scanner archivoEntrada = null;
		
		try {
			archivo = new File("reservas.txt");
			if(!archivo.exists()) {
				throw new FileNotFoundException();
			}
			archivoEntrada = new Scanner(archivo);
			
			while(archivoEntrada.hasNextLine()) {
				String lineaActual = archivoEntrada.nextLine();
				String[] partes = lineaActual.split("\t");
				
				Cliente cliente = c.buscarCliente(Integer.parseInt(partes[0]));
				Evento evento = c.buscarEvento(partes[1]);
				int cantEnt = Integer.parseInt(partes[2]);
				
				reservas.add( new Reserva(cliente, evento, cantEnt) );
			}
		}catch(IOException err) {
			err.printStackTrace();
		}
		
		return reservas;
	}
	
}
