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
		int cantidadHuespedes = Validaciones.validarInt(0, 6);
		ArrayList<Huesped> huespedes = new ArrayList<Huesped>();
		for(int i = 0; i < cantidadHuespedes; i++) {
			int dniIngreso;
			Huesped huesped;
			do {
				CLIVista.mostrarTexto("Ingresar dni del huesped a inscribir: ");
				dniIngreso = HuespedVista.ingresarDni();
				huesped = huespedControlador.buscarHuesped(dniIngreso);
				if(huesped == null) CLIVista.mostrarTexto("Huesped invalido.");
			}while(huesped != null);
	
			huespedes.add(huesped);
		}
		
		String nombreExcursion = CLIVista.ingresar("Ingresar nombre de la excursion: ");
		Excursion excursion = buscarExcursion(nombreExcursion);
		if(excursion == null) {
			CLIVista.mostrarTexto("Excursion invalida.");
			return;
		}
		
		int cantTotal = calcularCupoExcursion(cantidadHuespedes, excursion.getNombre());
		if(cantTotal >= excursion.getCantMax()) {
			CLIVista.mostrarTexto("La cantidad maxima de la excursion es superada!");
		}else if(cantTotal <= excursion.getCantMin()) {
			CLIVista.mostrarTexto("La cantidad minima de la excursion no es alcanzada!");
		}
		
		Calendar fechaActual = Calendar.getInstance();
		if(excursion.getDiaSemana() - fechaActual.get(Calendar.DAY_OF_WEEK) >= 1) {
			if(estadiaControlador.pertenecenMismaEstadia(huespedes)) {
				Estadia estadia = estadiaControlador.huespedEstadiaVigente(huespedes.get(0));
				estadia.addInscripcionExcursion(excursion, huespedes);
				CLIVista.mostrarTexto("Huespedes inscriptos exitosamente!");
			}else {
				CLIVista.mostrarTexto("Los huespedes no pertenecen a la misma estadia.");
			}
		}else {
			CLIVista.mostrarTexto("La inscripcion solo se puede hacer hasta un dia antes de la excursion.");

		}
	}
	
	public int calcularCupoExcursion(int cantidad, String nombreExcursion) {
		Excursion excursion = buscarExcursion(nombreExcursion);
		int cant = cantidad;
		for(Estadia estadia : estadiaControlador.getEstadias()) {
			if(estadia.getAlojamiento() instanceof Cabaña && estadiaControlador.estadiaEnCurso(estadia)) {
				for(Servicios s : estadia.getServicios()) {
					if(s instanceof InscripcionExcursiones && ((InscripcionExcursiones) s).getExcursion().getNombre().equals(excursion.getNombre())) {
						cant += ((InscripcionExcursiones) s).getHuespedes().size();
					}
				}
			}
		}
		
		return cant;
	}
	
	public void generarFichaExcursion() {
		Calendar fechaActual = Calendar.getInstance();
		int diaSemanaHoy = fechaActual.get(Calendar.DAY_OF_WEEK);
		Excursion excursionDiaHoy = null;
		for(Excursion e : excursiones) {
			if(e.getDiaSemana() == diaSemanaHoy) {
				excursionDiaHoy = e;
				for(Estadia estadia : estadias) {
					if(estadia.getAlojamiento() instanceof Cabaña) {
						for(Servicios s : estadia.getServicios()) {
							if(s instanceof InscripcionExcursiones) {
								excursionesJSON.escribirExcursion(excursionDiaHoy, estadia, ((InscripcionExcursiones)s));
							}
						}
					}
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
