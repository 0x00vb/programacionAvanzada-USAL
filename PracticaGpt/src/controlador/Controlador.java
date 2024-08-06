package controlador;

import modelo.*;
import modelo.dao.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Controlador {
	private SimpleDateFormat f = new SimpleDateFormat("dd/mm/yyyy");
	private VehiculosJSON vjson = new VehiculosJSON();
	private ReservasJSON rjson = new ReservasJSON();
	private ArrayList<Vehiculo> vehiculos = vjson.leerVehiculos();
	private ArrayList<Reserva> reservas = null;
	private ArrayList<Cliente> clientes = null;
	public int cargarReserva(int num, String nom, String ap, char tipo, String fechI, String fechF, boolean gps, boolean sillaB) throws ParseException, IOException {
		Vehiculo v = buscarVehiculo(tipo, gps, sillaB);
		Cliente c = buscarCliente(nom, ap);
		
		Calendar fechaInicio = Calendar.getInstance();
		fechaInicio.setTime(f.parse(fechI));
		
		Calendar fechaFin = Calendar.getInstance();
		fechaFin.setTime(f.parse(fechF));
		
		Reserva r = new Reserva(num, v,c, fechaInicio, fechaFin);
		reservas.add(r);
		rjson.escribirReservas(r);
		
		return 0;
	}
	
	public void puntoA() {
		int c = 0;
		Calendar fecha6meses = Calendar.getInstance();
		fecha6meses.add(Calendar.MONTH, -6);
		for(Reserva r : reservas) {
			if(r.getFechaInicio().after(fecha6meses)) {
				c++;
			}
		}
		System.out.print(c);
	}
	
	public ArrayList<Vehiculo> puntoB() {
		ArrayList<Vehiculo> x = new ArrayList<>();
		Calendar fecha6meses = Calendar.getInstance();
		fecha6meses.add(Calendar.MONTH, -6);

		for(Vehiculo v : vehiculos) {
			boolean res = false;
			for(Reserva r : reservas) {
				if(r.getVehiculo().getId() == v.getId() && r.getFechaInicio().after(fecha6meses))){
					res = true;
				}
			}
			if(!res) {
				x.add(v);
			}
		}
		
		return x;
	}
	
	public Reserva puntoC() {
		int dur = 0;
		Reserva x = null;
		
		reservas.sort( (a,b) -> Integer.compare(b.getNumReserva(), a.getNumReserva()) );
		
		for(Reserva r : reservas) {
			long milis1 = r.getFechaInicio().getTimeInMillis();
			long milis2 = r.getFechaFin().getTimeInMillis();
			
			long milis3 = milis2 - milis1;
			
			int duracion = (int) TimeUnit.MILLISECONDS.toDays(milis3);
			if(duracion > dur) {
				dur = duracion;
				x = r;
			}
		}
		
		return x;
	}
	
	public Map<Integer, String> puntoD(){
		HashMap<Integer, String> x = new HashMap<>();
		
		for(Reserva r : reservas) {
			x.put(
				r.getNumReserva(),
				r.getCliente().getNombre()
			);
		}
		
		for(Entry<Integer, String> e : x.entrySet()) {
			System.out.printf("%d, %s", e.getKey(), e.getValue());
		}
		
		return x;
	}
	
	public Vehiculo buscarVehiculo(char tipo, boolean gps, boolean sillaB) {
		for(Vehiculo v : vehiculos) {
			if(v.getTipo() == tipo && v.isGps() == gps && v.isSillaBebes() == sillaB) {
				boolean disp = true;
				Calendar fechaActual = Calendar.getInstance();
				for(Reserva r : reservas) {
					if(r.getVehiculo().getId() == v.getId() && r.getFechaFin().after(fechaActual)) {
						disp = false;
					}
				}
				if(disp) {
					return v;
				}
			}
		}
		return null;
	}
	
	public Cliente buscarCliente(String nombre, String apellido) {
		for(Cliente c : clientes) {
			if(c.getNombre().equals(nombre) && c.getApellido().equals(apellido)) {
				return c;
			}
		}
		return null;
	}
}
