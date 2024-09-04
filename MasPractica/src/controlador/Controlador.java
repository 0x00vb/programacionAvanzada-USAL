package controlador;

import java.util.*;

import modelo.*;
import modelo.dao.*;
import java.text.*;

public class Controlador {
	private CamarotesJSON cjson = new CamarotesJSON();
	private ArrayList<Camarote> camarotes = cjson.leerCamarotes();
	private ArrayList<Estadia> estadias = null;
	private SimpleDateFormat f = new SimpleDateFormat("dd/mm/yyyy");
	public void cargarInfo(int num, String nombre, String apellido, String fechaS, ArrayList<String[]> equipaje) throws ParseException {
		Pasajero p = new Pasajero(nombre, apellido);
		Camarote c = buscarCamarote(num);
		Calendar fecha = Calendar.getInstance();
		fecha.setTime(f.parse(fechaS));
		Estadia e = new Estadia( p, c, fecha );
		
		for(String[] list : equipaje) {
			e.addEquipaje(
					Integer.parseInt(list[0]),
					list[1].charAt(0),
					Double.parseDouble(list[2])
			);
		}
		estadias.add(e);
	}
	
	public Camarote buscarCamarote(int num) {
		for(Camarote c : camarotes) {
			if(c.getNumero() == num) {
				return c;
			}
		}
		return null;
	}
	
	public void punto() {
		ArrayList<Camarote> x = new ArrayList<Camarote>();
		Calendar fecha2Meses = Calendar.getInstance();
		fecha2Meses.add(Calendar.MONTH, -2);
		for(Camarote c : camarotes) {
			boolean usado = false;
			for(Estadia e : estadias) {
				if(e.getCamarote().getNumero() == c.getNumero() && e.getFechaRegistro().after(fecha2Meses)) {
					usado = true;
				}
			}
			if(!usado) {
				x.add(c);
			}
		}
		x.sort((a,b) -> Double.compare(b.getPesoMax(), a.getPesoMax()));
		TreeMap<Integer, Integer> info = new TreeMap<>();
		for(Camarote c : x) {
			System.out.printf("num %d, %piso %d", c.getNumero(), c.getPiso());
			info.put(c.getPiso(), c.getNumero());
		}
			
		for(Map.Entry<Integer, Integer> entry : info.descendingMap().entrySet()) {
			System.out.print("...");
		}
		
	}
	
	
}
