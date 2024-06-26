package controlador;
import modelo.*;
import modeloDAO.MesasTXT;

import java.util.*;


public class Controlador {
	private MesasTXT mesasTXT = new MesasTXT();
	private ArrayList<Mesa> mesas = mesasTXT.leerMesas();
	private ArrayList<Provincia> provincias = null;
	private ArrayList<Localidad> localidades = null;
	private String arg;

	
	public Provincia buscarProvincia(String nombre) {
		for(Provincia p : provincias) {
			if(p.getNombre() == nombre) {
				return p;
			}
		}
		return null;
	}
	
	public Localidad buscarLocalidad(String nomLoc) {
		for(Localidad l : localidades) {
			if(l.getNombre() == nomLoc) {
				return l;
			}
		}
		return null;
	}
	
	public ArrayList<String> getNombresProv(){
		ArrayList<String> nomProvs = new ArrayList<>();
		provincias.sort((a,b) -> a.getNombre().compareTo(b.getNombre()));
		for(Provincia p : provincias) {
			nomProvs.add(p.getNombre());
		}
		return nomProvs;
	}
	
	public ArrayList<String> getNombresLoc(String nomProv){
		ArrayList<String> nomsLoc = new ArrayList<>();
		
		for(Localidad l : localidades) {
			if(l.getProvincia().getNombre().equals(nomProv)) {
				nomsLoc.add(l.getNombre());
			}
		}
		
		return nomsLoc;
	}
	
	public int cargarMesa(int num, String nomLoc, int cantVotA, int cantVotB, char tipo) {
		Localidad l = buscarLocalidad(nomLoc);
		Mesa nMesa = new Mesa(num, l, cantVotA, cantVotB, tipo, Calendar.getInstance());
		mesas.add(nMesa);
		
		return 0;
	}
	
	public char getPartidoGanador() {
		char g = ' ';
		int votosA = 0;
		int votosB = 0;
		for(Mesa m : mesas) {
			votosA += m.getCantVotosA();
			votosB += m.getCantVotosB();
		}
		if(votosA > votosB) {
			g = 'a';
		}else if(votosA == votosB) {
			g = 'e';
		}else {
			g = 'b';
		}
		return g;
	}
	
	public double getPorcentajeGanador() {
		int votosA = 0;
		int votosB = 0;
		int votos = 0;
		for(Mesa m : mesas) {
			votosA += m.getCantVotosA();
			votosB += m.getCantVotosB();
			votos += m.getCantVotosA() + m.getCantVotosB();
		}

		double p = 0.0;
		if(votosA > votosB) {
			p = votosA * votos / 100;
		}else {
			p = votosB * votos / 100;
		}
		return p;
	}
	
	public int cantTotVot() {
		int v = 0;
		Iterator<Mesa> i = mesas.iterator();
		while(i.hasNext()) {
			Mesa m = i.next();
			v += m.getCantVotosA() + m.getCantVotosB();
		}
		return v;
	}
	
	public void puntoA(String arg) {
		int i = 0;
		int a = Integer.parseInt(arg);
		for(Mesa m : mesas) {
			if(m.getCantVotosA() == a) {
				i++;
			}
		}
		System.out.print(i);
	}
	
	public ArrayList<String> puntoB() {
		ArrayList<String> nomProvs = new ArrayList<String>();
		for(Provincia p : provincias) {
			nomProvs.add(p.getNombre());
		}
		
		for(Mesa m : mesas) {
			if(0 != m.getCantVotosA() && m.getCantVotosB() != 0) {
				nomProvs.remove(m.getLocalidad().getProvincia().getNombre());
			}
		}
		
		return null;
	}
	
	public ArrayList<Mesa> puntoC() {
		ArrayList<Mesa> mesasOrd = new ArrayList<Mesa>(mesas);
		mesasOrd.sort((a,b) -> Integer.compare(b.getCantVotosA() + b.getCantVotosB(), a.getCantVotosA() + a.getCantVotosB()));
		mesasOrd.stream().limit(3).toArray();
		mesasOrd.sort((a,b) -> Integer.compare(b.getNumero(), a.getCantVotosA()));
		return mesasOrd;
	}
	
	public Map<Integer, Integer> puntoD(){
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(Mesa m : mesas) {
			int totVot = m.getCantVotosA() + m.getCantVotosB();
			map.put(m.getNumero(), totVot);
		}
		return map;
	}
	
	
}
