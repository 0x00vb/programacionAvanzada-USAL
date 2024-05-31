package controlador;
import modelo.*;
import modeloDAO.*;

import java.util.Calendar;

public class MantenimientoControlador {
	public static int cargarMantenimiento(String patente, char tipo, double precio) {
		Calendar fechaActual = Calendar.getInstance();
		
		Auto auto = AutoControlador.buscarAuto(patente);
		
		if(auto == null)
			return -1;
	
		String fechaParseada = String.format("%04d%02d%02d", fechaActual.get(Calendar.YEAR), fechaActual.get(Calendar.MONTH) + 1, fechaActual.get(Calendar.DAY_OF_MONTH));
		
		auto.agregarMantenimiento(tipo, fechaActual, precio);
		MantenimientoTXT.cargarMantenimiento(patente, tipo, fechaParseada, precio);
		
		System.out.println(auto.getMantenimientos().size());
		
		return 0;
	}
	
	
	public static Mantenimiento mantenimientoDeMayorImporte(Auto auto) {
		Mantenimiento mantenimientoMayor = null;
		for(Mantenimiento m : auto.getMantenimientos()) {
			if(mantenimientoMayor == null) {
				mantenimientoMayor = m;
			}
			if(m != null && m.getCosto() > mantenimientoMayor.getCosto()) {
				mantenimientoMayor = m;
			}
		}
		
		return mantenimientoMayor;
	}
	
}
