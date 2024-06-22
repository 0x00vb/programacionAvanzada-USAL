package controlador;
import java.util.*;

import modelo.*;

public class Controlador {
	ArrayList<Tecnico> tecnicos = new ArrayList<Tecnico>();
	ArrayList<Ascensor> ascensores = new ArrayList<Ascensor>();
	ArrayList<Reparacion> reparaciones = new ArrayList<>();
	
	public int cargarReparacion(int codigoAscensor, boolean cambioLuces, boolean cambioBotonera, boolean mejoraEstructura, int dniTecnico) {
		
		return 0;
	}
	
	public Ascensor buscarAscensor(int codigo) {
		for(Ascensor a : ascensores) {
			if(a.getCodigo() == codigo) {
				return a;
			}
		}
		return null;
	}
	
	public Tecnico buscarTecnico(int dni) {
		for(Tecnico t : tecnicos) {
			if(t.getDni() == dni) {
				return t;
			}
		}
		return null;
	}
	
	public void puntoA() {
		
	}
	
	public void puntoB() {
		ArrayList<Tecnico> t = new ArrayList<>(tecnicos);
		HashSet<Tecnico> tecnicosEliminar = new HashSet<>();
		Calendar fecha6Meses = Calendar.getInstance();
		fecha6Meses.add(Calendar.MONTH, -6);
		for(Reparacion r : reparaciones) {
			if(r.getFecha().after(fecha6Meses) && r.isCambioBotonera()){
				tecnicosEliminar.add(r.getTecnico());
			}
		}
		t.removeAll(tecnicosEliminar);
		
		for(Tecnico k : t) {
			System.out.print(k.getNombre());
		}
	}
	
}
