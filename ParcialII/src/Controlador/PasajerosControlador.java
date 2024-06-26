package Controlador;
import java.util.*;

import modelo.*;
import modeloDAO.*;

public class PasajerosControlador {
	private int id;
	private CamarotesControlador cc = new CamarotesControlador();
	private ArrayList<Pasajero> pasajeros = PasajerosTXT.leerPasajeros();
	
	public double registrarPasajero(String nombre, String apellido, int numeroCamarote, HashMap<String, Double> equipajes) throws PesoExcedidoException {
		Calendar fechaActual = Calendar.getInstance();
		Camarote camarote = cc.buscarCamarote(numeroCamarote);
		
		if(camarote == null)
			return -1;
		Pasajero pasajero = new Pasajero(nombre, apellido, camarote);
		
		double costoAdicional = pasajero.calcularCostoAdicional();
		try {
			if(costoAdicional != 0.0) throw new PesoExcedidoException("");
		}catch(PesoExcedidoException err) {
			return costoAdicional;
		}
		
		return 0;
	}
	
	public void pasajeroRandom() {
		Random random = new Random();
		int indexRandom = random.nextInt(50);

		Pasajero p = pasajeros.get(indexRandom);
		boolean viajoMasDeUnaVez = false;
		for(Pasajero pasajero : pasajeros) {
			if(p.getApellido().equals(pasajero.getApellido()) && pasajeros.indexOf(pasajero) != indexAleatorio) {
				viajoMasDeUnaVez = true;
			}
		}
		
	}
	
	public ArrayList<Pasajero> getPasajeros(){
		return this.pasajeros;
	}
	
}
