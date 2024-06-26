package Controlador;
import java.util.*;

import modelo.*;
import modeloDAO.*;

public class CamarotesControlador {
	ArrayList<Camarote> camarotes = CamarotesJSON.leerCamarotes();
	PasajerosControlador pc = new PasajerosControlador();
	
	public void camNoUtilizados() {
		TreeSet<Camarote> camarotesA = new TreeSet<Camarote>(new Comparator<Camarote>() {
			public int compare(Camarote c0, Camarote c1) {
				if(c0.getPiso() == c1.getPiso()) {
					return 0;
				}
			
				return Integer.compare(c1.getNumero(), c0.getNumero());
			}
		});
		Calendar fechaHace2Meses = Calendar.getInstance();
		fechaHace2Meses.add(Calendar.MONTH, -2);
		for(Pasajero pasajero : pc.getPasajeros()) {
			if(pasajero.getFechaRegistro().before(fechaHace2Meses)) {
				camarotesA.add(pasajero.getCamarote());
			}
		}
			
		Iterator<Camarote> iterador = camarotesA.descendingIterator();
		while(iterador.hasNext()) {
			Camarote c = iterador.next();
			System.out.print(c.getNumero() + " " + c.getPiso());
		}	
	}	
	
	public Camarote buscarCamarote(int numeroCamarote) {
		for(Camarote c : camarotes) {
			if(c.getNumero() == numeroCamarote) {
				return c;
			}
		}
		return null;
	}
}
