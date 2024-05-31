package controlador;

import java.util.ArrayList;
import java.util.Calendar;

import modelo.*;
import modeloDAO.*;

public class AutoControlador {
	private static ArrayList<Auto> autos = null;
	
	public AutoControlador() {
		autos = AutoTXT.leerAutos();
		for(Auto a : autos) {
			MantenimientoTXT.leerMantenimientos(a);
		}
	}
	
	public static int cargarAuto(String patente, int anio, double precio) {
		int anioPatente = Integer.parseInt(patente.substring(2,6));

		if(anioPatente != anio) 
			return -1;
		
		if(buscarAuto(patente) != null)
				return -2;
		
		Auto auto = new Auto(patente, anio, precio);
		autos.add(auto);
		AutoTXT.cargarAuto(auto);
		
		return 0;
	}	
	
	public static String obtenerDatosAuto(String patente) {
		Auto auto = buscarAuto(patente);
		if(auto == null)
			return null;
		
		Mantenimiento mantenimientoMayor = MantenimientoControlador.mantenimientoDeMayorImporte(auto);
		if(mantenimientoMayor == null) {
			System.out.println("xyz");
		}
				
		String respuesta = String.format(
				"Patente: %s\n"
				+ "Anio patentamiento: %d\n"
				+ "Precio de compra: %.2f\n"
				+ "Costo total (precio + mantenimientos): %.2f\n",
				patente, auto.getAnio(), auto.getPrecioCompra(), auto.calcularCostoTotal()
		);		
		
		if(mantenimientoMayor != null) {
			String tipo = "";
			tipo = mantenimientoMayor.getTipo() == 'c' ? "Control preventivo" : "Reparacion";
			Calendar fecha = mantenimientoMayor.getFecha();
			String fechaString = String.format("%04d/%02d/%02d", fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), fecha.get(Calendar.DAY_OF_MONTH));
			
			respuesta += String.format(
					"Mantenimiento de mayor importe:\n"
					+ "Fecha: %s\n"
					+ "Tipo: %s\n"
					+ "Costo: %.2f", fechaString, tipo, mantenimientoMayor.getCosto()
			);
		}
		
		return respuesta;
	}
	
	public static Auto buscarAuto(String patente) {
		for(Auto auto : autos) {
			if(auto.getPatente().equals(patente)) {
				return auto;
			}
		}
		return null;
	}
	
}
