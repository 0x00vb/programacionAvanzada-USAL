package controlador;
import modeloDAO.*;
import vista.*;
import modelo.*;
import java.util.*;

public class ExcursionControlador {
	private HuespedControlador huespedControlador = new HuespedControlador();
	private EstadiaControlador estadiaControlador = new EstadiaControlador();
	private ExcursionesTXT excursionesTXT = new ExcursionesTXT();
	private ExcursionesJSON excursionesJSON = new ExcursionesJSON();
	private ArrayList<Excursion> excursiones = excursionesTXT.leerExcurciones();
	private ArrayList<Estadia> estadias = estadiaControlador.getEstadias();
	
	public void inscribirHuesped() {
		CLIVista.mostrarTexto("Ingresar dni del huesped a inscribir: ");
		int dniIngreso = HuespedVista.ingresarDni();
		Huesped huesped = huespedControlador.buscarHuesped(dniIngreso);
		if(huesped == null) {
			CLIVista.mostrarTexto("Huesped invalido.");
			return;
		}
		String nombreExcursion = CLIVista.ingresar("Ingresar nombre de la excursion: ");
		Excursion excursion = buscarExcursion(nombreExcursion);
		if(excursion == null) {
			CLIVista.mostrarTexto("Excursion invalida.");
			return;
		}
		
		if(excursion.getCantMax() )
		
		
		Calendar fechaActual = Calendar.getInstance();
		if(excursion.getDiaSemana() - fechaActual.get(Calendar.DAY_OF_WEEK) >= 1) {
			
		}else {
			CLIVista.mostrarTexto("La inscripcion solo se puede hacer hasta un dia antes de la excursion.");

		}
	}
	
	public void generarFichaExcursion() {
		Calendar fechaActual = Calendar.getInstance();
		int diaSemanaHoy = fechaActual.get(Calendar.DAY_OF_WEEK);
		for(Excursion e : excursiones) {
			if(e.getDiaSemana() == diaSemanaHoy) {
				HashMap<Integer, String> participantes = new HashMap<Integer, String>();
				for(Estadia estadia : estadias) {
					if(estadia.getAlojamiento() instanceof Cabaña) {
						for(Servicios s : estadia.getServicios()) {
							if(s instanceof InscripcionExcursiones) {
								participantes.put(estadia.getAlojamiento().getId(), s.getHuesped().getNombre());
							}
						}
					}
					excursionesJSON.escribirExcursion(null);
				}
				
				
				
				
				
			}
		}
	}
	
	public Excursion buscarExcursion(String nombre) {
		for(Excursion e : excursiones) {
			if(e.getNombre().equals(nombre)) {
				return e;
			}
		}
		return null;
	}
	
}
