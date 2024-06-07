package controlador;
import java.util.*;

import modelo.*;

public class EstadiaControlador {
	private CocheraControlador controladorCochera = new CocheraControlador();
	private ArrayList<Estadia> estadias = new ArrayList<>();
	private ArrayList<Cochera> cocheras = CocheraTXT.leerCocheras();

	public double registrarEstadia(int patente, String tipo, int numeroCochera, int cantidadHoras, boolean llaves, boolean pagoAdelantado) {
		Cochera cochera = controladorCochera.buscarCochera(numeroCochera);
		if(cochera == null)
			return -1;
		
		if(!cochera.getTarifa().getTipo().equals(tipo)) 
			return -2;
		
		Calendar fecha = Calendar.getInstance();
		Vehiculo vehiculo = new Vehiculo(patente, tipo);
		Estadia nuevaEstadia = new Estadia(fecha, vehiculo, cochera, cantidadHoras, pagoAdelantado, llaves);
		estadias.add(nuevaEstadia);
		EstadiaTXT.escribirEstadia(nuevaEstadia);
		
		return nuevaEstadia.calcularCostoTotal();
	}
	
	public void mostrarHorasUtilizadasPorCochera() {
		Map<Integer, Integer> horasPorCochera = new HashMap<>();
		Iterator<Estadia> iterador = estadias.iterator();
		
		while(iterador.hasNext()) {
			Estadia estadia = iterador.next();
			if(estadia.getCochera().isDisponible()) {
				int numeroCochera = estadia.getCochera().getNumero();
				int horasEstimadas = estadia.getCantHoras();
				horasPorCochera.put(numeroCochera, horasEstimadas);
			}
		}
		
		for(Map.Entry<Integer, Integer> entry : horasPorCochera.entrySet()) {
            System.out.println("Cochera número " + entry.getKey() + ": " + entry.getValue() + " horas utilizadas");
		}
	}
	
	
	public void puntob() {
		List<Estadia> estadiasOrdenadas = new ArrayList<>(estadias);
		
        estadiasOrdenadas.sort(Comparator.comparing(estadia -> estadia.getVehiculo().getPatente(), Comparator.reverseOrder()));		
        Iterator<Estadia> iterador = estadiasOrdenadas.iterator();
		
		while(iterador.hasNext()) {
			Estadia estadia = iterador.next();
            Vehiculo vehiculo = estadia.getVehiculo();
            Cochera cochera = estadia.getCochera();

			if(estadia.isPagoAdelantado()) {
	            System.out.println("Patente: " + vehiculo.getPatente());
	            System.out.println("Número de Cochera: " + cochera.getNumero());
	            System.out.println("Ubicación: " + cochera.getUbicacion());
	            System.out.println("Tipo de Vehículo: " + vehiculo.getTipo());
	            System.out.println("Valor total: " + estadia.calcularCostoTotal());
	            System.out.println("------------------------------------------------");
			}
		}
	}
	
	public void puntoc() {
		int mayorTiempoOcupacion = 0;
		for(Estadia estadia : estadias) {
			if(estadia.getCantHoras() > mayorTiempoOcupacion) {
				mayorTiempoOcupacion = estadia.getCantHoras();
			}
		}
	}
	
	public void puntod(int numeroCochera) {
		Map<Integer, Integer> ocupaciones = new HashMap<>();
		int cantidadVecesX = 0;
		Calendar fechaActual = Calendar.getInstance();
		int mesActual = fechaActual.get(Calendar.MONTH);
		for(Estadia estadia : estadias) {
			if(estadia.getCochera().getNumero() == numeroCochera && estadia.getFecha().get(Calendar.MONTH) == mesActual) {
				int patente = estadia.getVehiculo().getPatente();
				ocupaciones.put(patente, ocupaciones.getOrDefault(patente, 0) + 1);
			}
				cantidadVecesX++;
		}
		for(Map.Entry<Integer, Integer> entry : ocupaciones.entrySet()) {
            System.out.println("Cochera número " + entry.getKey() + ": " + entry.getValue() + " cantidad de veces utilizada por el mismo auto en este mes");
		}

	}
}
