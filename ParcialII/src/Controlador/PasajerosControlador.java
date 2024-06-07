package Controlador;
import java.util.*;

import modelo.*;
import modeloDAO.*;

public class PasajerosControlador {
	private int id;
	CamarotesControlador cc = new CamarotesControlador();
	public int registrarPasajero(String nombre, String apellido, int numeroCochera, HashMap<String, Double> equipajes) throws PesoExcedidoException {
		Calendar fechaActual = Calendar.getInstance();
		Camarote camarote = cc.buscarCamarote(numeroCochera);
		
		if(camarote == null)
			return -1;
		
		if(...) {
			throws new PesoExcedidoException("Mensaje...");
		}
		
		
		return 0;
	}
}
