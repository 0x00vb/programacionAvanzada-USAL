package controlador;

import modelo.*;
import modelo.dao.*;

import java.text.ParseException;
import java.util.*;

public class Controlador {
	private EstadiasJSON estJson = new EstadiasJSON();
	private ArrayList<Cochera> cocheras = null;
	private ArrayList<Vehiculo> vehiculos = null;
	private ArrayList<Estadia> estadias = estJson.leerE();


	public Controlador()  {

	}
	
	public int cargar(int pat, char tipo, int num, boolean llaves, boolean pago, int horas) {
		Vehiculo v = new Vehiculo(pat, tipo);
		vehiculos.add(v);
		Cochera c = buscarCochera(num);
		
		if(horas > c.getMaxHoras()) {
			return -1;
		}
		
		Calendar fecha = Calendar.getInstance();
		Estadia e = new Estadia(c, v, fecha, horas, llaves, pago);
		estadias.add(e);
		estJson.escribirE(e);
		return 0;
	}
	
	public void puntoA() {
		for(Cochera c : cocheras) {
			if(c.isDisponible()) {
				int x = 0;
				for(Estadia e : estadias) {
					if(e.getCochera().getNum() == c.getNum()) {
						x += e.getHorasE();
					}
				}
				System.out.printf("cochera %d, horas: %d", c.getNum(), x);
			}
		}
	}
	
	public void puntoB() {
		ArrayList<Estadia> x = new ArrayList<>();
		for(Estadia e : estadias) {
			if(e.isPagoAd()) {
				x.add(e);
			}
		}
		Collections.sort(x, (a,b) -> Integer.compare(b.getVehiculo().getPatente(), a.getVehiculo().getPatente()));
		for(Estadia e : x) {
			System.out.printf("%d %d %d", e.getVehiculo().getPatente(), e.getCochera().getNum());			
		}
	}
	
	public void puntoC() {
		int x = 0;
		for(Estadia e : estadias) {
			if(e.getHorasE() > x) {
				x = e.getHorasE();
			}
		}
	}
	
	public void puntoD(int a) {
		Calendar fechaA = Calendar.getInstance();
		int mesAct = fechaA.get(Calendar.MONTH);
		
		for(Estadia e : estadias) {
			for(Vehiculo v : vehiculos) {
				if(e.getCochera().getNum() == a) {
					
				}				
			}
		}
	}
	
	public Vehiculo buscarVehiculo(int p) {
		for(Vehiculo v : vehiculos) {
			if(v.getPatente() == p) {
				return v;
			}
		}
		return null;
	}
	
	public Cochera buscarCochera(int n) {
		for(Cochera c : cocheras) {
			if(c.getNum() == n) {
				return c;
			}
		}
		return null;
	}
	
	public ArrayList<Integer> buscarNCocheras(char t) {
		ArrayList<Integer> x = new ArrayList<>();
		for(Cochera c : cocheras) {
			if(c.getTipo() == t) {
				x.add(c.getNum());
			}
		}
		return x;
	}
	
	
	
}
