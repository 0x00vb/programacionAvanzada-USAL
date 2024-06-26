package controlador;
import java.util.*;

import modelo.*;

public class Controlador {
	ArrayList<Tecnico> tecnicos = new ArrayList<Tecnico>();
	ArrayList<Ascensor> ascensores = new ArrayList<Ascensor>();
	ArrayList<Reparacion> reparaciones = new ArrayList<>();
	private String arg;
	
	public Controlador() {}
	
	public Controlador(String arg) {
		this.arg = arg;
	}
	
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
	
	public void direcciones() {
		Random random = new Random();
		int numRand = random.nextInt(100);
		Set<String> direcciones = new TreeSet<>();
		for(Ascensor a : ascensores) {
			for(Reparacion r : a.getReparaciones()) {
				if(r.isMejoraEstructura() && r.getCosto() > numRand) {
					direcciones.add(a.getDireccionEdificio());
				}
			}			
		}
	}
	
	//a) El número y la dirección de los ascensores manuales con el mayor costo total de reparaciones realizadas, haciendo uso de recorridos inversos.
	public void puntoA() {
		ArrayList<Ascensor> ascensoresManuales = new ArrayList<Ascensor>();
		ListIterator<Ascensor> iteradorInverso = ascensores.listIterator(ascensores.size());
		while(iteradorInverso.hasPrevious()) {
			Ascensor a = iteradorInverso.previous();
			if(a.getTipo() == 'm') ascensoresManuales.add(a);
		}
		
		ascensoresManuales.sort(
				(a,b) -> Integer.compare(b, a)
		)
		
		
		
		
		
		
		
//		ArrayList<Ascensor> ascensoresCostoMayor = new ArrayList<Ascensor>();
//		double costoMayor = 0.0;
//		
//		
//		ListIterator<Ascensor> iterador = ascensores.listIterator(ascensores.size());
//		while(iterador.hasPrevious()) {
//			Ascensor a = iterador.previous();
//			if(a.getTipo() == 'm') {
//				double costoTotalReparaciones = 0.0;
//				for(Reparacion r : a.getReparaciones()) {
//					costoTotalReparaciones += r.getCosto();
//				}
//				if(costoTotalReparaciones > costoMayor){
//					costoMayor = costoTotalReparaciones;
//					ascensoresCostoMayor.add(a);
//				}
//			}
		
			
			
		}
		
		
		
		
		
		
		
//		List<Ascensor> ascensoresReverser = new ArrayList<Ascensor>(ascensores);
//		Collections.reverse(ascensoresReverser);

		
//		LinkedList<Ascensor> ascensoresL = new LinkedList<>(ascensores);
//		Iterator<Ascensor> iteradorInverso = ascensoresL.descendingIterator();
//		while(iteradorInverso.hasNext()) {
//			Ascensor a = iteradorInverso.next();
//			if(a.getTipo() == 'm') {
//				double costoTotalR = 0.0;
//				for(Reparacion r : a.getReparaciones()) {
//					costoTotalR += r.getCosto();
//				}
//				if(costoTotalR > costoMayor)
//					costoMayor = costoTotalR;
//				ascensor = a;
//			}
//		}
//		
		System.out.printf("num: %d, dir: %s", ascensor.getCodigo(), ascensor.getDireccionEdificio());
		
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
