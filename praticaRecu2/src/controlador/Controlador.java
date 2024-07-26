package controlador;

import modelo.*;
import modelo.modeloDAO.*;
import java.util.*;

public class Controlador {
	ArrayList<Tecnico> tecnicos = null;
	ArrayList<Ascensor> ascensores = null;

	ReparacionesTXT repTxt = new ReparacionesTXT();
	
	public void cargarRep(int numA, int dni, boolean cambioLuces, boolean cambioBotonera, boolean mejoraInfraestructura) {
		Ascensor a = buscarAscensor(numA);
		Tecnico t = buscarTecnico(dni);
		Calendar fecha = Calendar.getInstance();
		a.addReparacion(t, fecha, cambioLuces, cambioBotonera, mejoraInfraestructura);
		repTxt.cargarDatos(numA, dni, cambioLuces, cambioBotonera, mejoraInfraestructura);
		
	}
	
	public TreeSet<String> metodo(){
		TreeSet<String> x = null;
		Random r = new Random();
		int num =r.nextInt();
		
		ArrayList<String> aux = new ArrayList<>();
		for(Ascensor a : ascensores) {
			for(Reparacion rep : a.getReparaciones()) {
				if(rep.isMejoraInfraestructura()) {
					aux.add(a.getDireccionEdificio());
				}
			}
		}
		Collections.sort(aux);
		
		x = new TreeSet<>(aux);
		
		return x;
	}
	
	public Ascensor buscarAscensor(int n) {
		for(Ascensor a : ascensores) {
			if(a.getCodigo() == n) {
				return a;
			}
		}
		return null;

	}
	
	public void puntoB() {
		ArrayList<String> nom = new ArrayList<>();
		
		Calendar fecha6meses = Calendar.getInstance();
		fecha6meses.add(Calendar.MONTH, -6);
	
		for(Tecnico t : tecnicos) {
			boolean trabajo = false;
			for(Ascensor a : ascensores) {
				for(Reparacion r : a.getReparaciones()) {
					if(r.getTecnico().getDni() == t.getDni() && r.getFecha().after(fecha6meses)) {
						trabajo = true;
					}
				}
			}
			if(!trabajo) {
				nom.add(t.getNombre());
			}
		}
		Collections.sort(nom);
		ListIterator<String> iterador = nom.listIterator();
		while(iterador.hasPrevious()) {
			String n = iterador.previous();
			System.out.print(n);
		}
	}
	
	public Tecnico buscarTecnico(int dni) {
		for(Tecnico t : tecnicos) {
			if(t.getDni() == dni) {
				return t;
			}
		}
		return null;
	}
	
}
