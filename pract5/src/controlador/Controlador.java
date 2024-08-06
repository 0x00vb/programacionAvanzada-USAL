package controlador;
import modelo.*;
import modelo.dao.*;
import java.util.*;
import java.util.Map.Entry;

public class Controlador {
	private ArrayList<Evento> eventos = null;
	private ArrayList<Reserva> reservas = null;
	private ArrayList<Cliente> clientes = null;

	public int cargarEvento() {
		
		return 0;
	}

	public int cargarReserva(int dni, String nomEv, int cantE) {
		
		Cliente c = buscarCliente(dni);
		Evento e = buscarEvento(nomEv);
		reservas.add( new Reserva(c, e, cantE) );
		
		return 0;
	}
	
	
	public void puntoA() {
		HashMap<String, Integer> x = new HashMap<>();
		
		for(Evento e : eventos) {
			int cantE = 0;
			for(Reserva r : reservas) {
				if(e.getNombre().equals(r.getEvento().getNombre())) {
					cantE += r.getCantEntradas();
				}
				x.put(e.getNombre(), cantE);
			}
		}
		
		for(Entry<String, Integer> entry : x.entrySet()) {
			System.out.print("" + entry.getKey() + " " + entry.getValue());
		}	
	}
	
	public void puntoC() {
		int cant = 0;
		Evento ev = null;
		for(Evento e : eventos) {
			int cantR = 0;
			if(e.getFecha().before(Calendar.getInstance())){
				for(Reserva r : reservas) {
					if(r.getEvento().getNombre().equals(e.getNombre())) {
						cantR += r.getCantEntradas();						
					}
				}
			}
			if(cantR > cant) {
				cant = cantR;
				ev = e;
			}
		}
	}
	
	
	public Cliente buscarCliente(int i) {
		for(Cliente c : clientes) {
			if(c.getDni() == i) {
				return c;
			}
		}
		return null;
	}
	
	public Evento buscarEvento(String n) {
		for(Evento e : eventos) {
			if(e.getNombre().equals(n)) {
				return e;
			}
		}
		return null;
	}
	
	public ArrayList<String> getNombresEventos(){
		ArrayList<String> n = new ArrayList<>();
		for(Evento e : eventos) {
			n.add(e.getNombre());
		}
		return n;
	}
	
	public ArrayList<Integer> getCantEntradas(String nom){
		ArrayList<Integer> n = new ArrayList<>();
		Evento e = buscarEvento(nom);
		int entradasDisp = e.getLimiteEntradas();
		
		for(Reserva r : reservas) {
			if(r.getEvento().getNombre().equals(e.getNombre())) {
				entradasDisp -= r.getCantEntradas();
			}
		}	
		
		for(int i = 1; i <= entradasDisp; i++) {
			n.add(i);
		}
		
		return n;
	}

}
