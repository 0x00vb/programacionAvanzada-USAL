package controlador;

import java.util.*;

import modelo.*;
import java.text.*;
import modelo.dao.*;

public class Controlador {
	private MesasJSON mjson = new MesasJSON();
	private ArrayList<Provincia> provincias = null;
	private ArrayList<Localidad> localidades = null;
	private ArrayList<Mesa> mesas = mjson.leerMesas();
	private SimpleDateFormat f = new SimpleDateFormat("dd/mm/yyyy");
	
	public int cargarMesa(int numero, String nombreProv, String nombreLoc, int votA, int votB, char tipo) {
		if(numero < 1 || numero > 9999) {
			throw new ExcepcionPropia("Numero de mesa invalido");
		}
		
		Localidad localidad = buscarLoc(nombreLoc, nombreProv);
		Calendar fecha = Calendar.getInstance();
		
		Mesa mesa = new Mesa(numero, localidad, votA, votB, tipo, fecha);
		mesas.add(mesa);
		mjson.escribirMesa(mesa);
		
		return 0;
	}
	
	public String calcResult() {
		int totalVotos = 0;
		int votosA = 0;
		int votosB = 0;
		String ganador = "";
		double porc = 0.0;
		Iterator<Mesa> iteradorMesas = mesas.iterator();
		while(iteradorMesas.hasNext()) {
			Mesa m = iteradorMesas.next();
			votosA += m.getVotosA();
			votosB += m.getVotosB();
			totalVotos += m.getVotosA() + m.getVotosB();
		}
		
		if(votosA > votosB) {
			porc = votosA / totalVotos * 100;
			ganador = "A " + String.format("%.2f", porc);
		}else if(votosB > votosA) {
			porc = votosB / totalVotos * 100;
			ganador = "B " + String.format("%.2f", porc);
		}else {
			ganador = "empate";
		}
			
		return ganador;
	}
	
	public void puntoA(int arg) {
		int cant = 0;
		for(Mesa m : mesas) {
			if(m.getVotosA() == arg) {
				cant++;
			}
		}
		System.out.print(cant);
	}
	
	public ArrayList<String> puntoB() {
		ArrayList<String> x = new ArrayList<>();
		for(Localidad l : localidades) {
			boolean escrutada = false;
			for(Mesa m : mesas) {
				if(m.getVotosA() + m.getVotosB() != 0) {
					escrutada = true;
				}
			}
			if(!escrutada) {
				x.add(l.getProv().getNombre());
			}
		}
		x.sort( (a,b) -> b.compareTo(a) );
		
		return x;
	}
	
	public Map<Integer, Integer> puntoD() {
		HashMap<Integer, Integer> x = new HashMap<>();
		
		for(Mesa m : mesas) {
			x.put(m.getNum(), m.getVotosA() + m.getVotosB());
		}
		return x;
	}
	
	public Provincia buscarProv(String nombre) {
		for(Provincia p: provincias) {
			if(p.getNombre().equals(nombre)) {
				return p;
			}
		}
		return null;
	}
	
	public Localidad buscarLoc(String nombreL, String nombreP) {
		for(Localidad l : localidades) {
			if(l.getNombre().equals(nombreL) && l.getProv().getNombre().equals(nombreP)) {
				return l;
			}
		}
		return null;
	}
	
	public ArrayList<String> getNombresProvincias() {
		ArrayList<String> x = new ArrayList<>();
		for(Provincia p : provincias){
			x.add(p.getNombre());
		}
		x.sort( (a,b) -> a.compareTo(b) );
		return x;
	}
	
	public ArrayList<String> getNombresLoc(String nomP){
		ArrayList<String> x = new ArrayList<>();
		for(Localidad l : localidades) {
			if(l.getProv().getNombre().equals(nomP)) {
				x.add(l.getNombre());
			}
		}
		return x;
	}
	
	
}
