package controlador;

import java.util.*;
import java.util.Map.Entry;

import modelo.*;
import modeloDAO.*;
public class controlador {
	private ArrayList<Pasajero> pasajeros = null;
	private ArrayList<Camarote> camarotes = null;
	private ArrayList<Estadia> estadias = null;
	private RegistroTXT txt = new RegistroTXT();

	public double Registrar(int num, String nom, String ap, HashMap<Character, Double> equipaje) {
		Camarote camarote = buscarCamarote(num);
		Pasajero p = new Pasajero(nom, ap);
		ArrayList<Equipaje> equi= new ArrayList<>();
		
		for(Entry<Character, Double> entry: equipaje.entrySet()) {
			Equipaje e = new Equipaje(entry.getKey(), entry.getValue());
			equi.add(e);
		}
		Estadia e = new Estadia(p, camarote, equi);
		
		if(e.calcularCostoAdicional() != 0.0) {
			return e.calcularCostoAdicional();
		}
		
		txt.escribirEstadia(e);
		return 0;
	}
	
	public Camarote buscarCamarote(int n) {
		for(Camarote c : camarotes) {
			if(c.getNum() == n) {
				return c
			}
		}
		return null;
	}
	
	public void consigna() {
		ArrayList<Camarote> cam = new ArrayList<Camarote>();
		for(Camarote c : camarotes) {
			boolean usado = false;
			for(Estadia e : estadias) {
				if(e.getCamarote().getNum() == c.getNum()) {
					Calendar fechaActual = Calendar.getInstance();
					fechaActual.add(Calendar.MONTH, -2);
					if(e.getFechaRegistro().after(fechaActual)) {
						usado = true;
					}
				}
			}
			if(!usado) {
				cam.add(c);
			}
		}
		cam.sort((c0, c1) -> Double.compare(c1.getPesoMax(), c0.getPesoMax()));
		TreeSet<Camarote> camarotess = new TreeSet<Camarote>((c0, c1) -> Integer.compare(c0.getPiso(), c1.getPiso()));
		for(Camarote c : cam) {
			camarotess.add(c);
		}

		Iterator<Camarote> iteradorReverso = camarotess.descendingIterator();
		while(iteradorReverso.hasNext()) {
			Camarote c = iteradorReverso.next();
			System.out.print(c.getPiso());
		}
	}
	
	public void consigna2() {
		Random r = new Random();
		int indice = r.nextInt(pasajeros.size());
		Pasajero p = pasajeros.get(indice);
		int i = 0;
		for(Estadia e : estadias) {
			if(e.getPasajero().getApellido().equals(p.getApellido())) {
				i++;
			}
		}
		if(i > 1) {
			System.out.print("participo mas de una vez");
		}
	}

}
