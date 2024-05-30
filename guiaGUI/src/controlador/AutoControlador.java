package controlador;

import java.util.ArrayList;

import modelo.*;
import modeloDAO.*;

public class AutoControlador {
	private static ArrayList<Auto> autos = null;
	
	public AutoControlador() {
		autos = AutoTXT.leerAutos();
		for(Auto auto : autos) {
			System.out.println(auto.getPatente());		
			System.out.println(auto.getAnio());
			System.out.println(auto.getPrecioCompra());	
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
	
	public static Auto buscarAuto(String patente) {
		for(Auto auto : autos) {
			if(auto.getPatente().equals(patente)) {
				return auto;
			}
		}
		return null;
	}
	
}
