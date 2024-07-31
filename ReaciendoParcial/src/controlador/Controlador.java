package controlador;
import modelo.*;
import modelo.dao.*;
import java.util.*;


public class Controlador {
	private ArrayList<Provincia> provincias = new ArrayList<>();
	private ArrayList<Localidad> localidades = new ArrayList<>();
	private ArrayList<Mesa> mesas = new ArrayList<>();
	private MesasJSON mj = new MesasJSON();
	private int arg;
	
	public Controlador() {}
	public Controlador(int arg) {
		this.arg = arg;
	}
	
	public void cargarMesa(int num, String p, String l, int va, int vb, char t) throws EceptionP {
		Localidad loc = buscarLoc(l, p);
		if(num < 1 || num > 9999) {
			throw new EceptionP();
		}
		Calendar fecha = Calendar.getInstance();
		Mesa m = new Mesa(num, loc, va, vb, t, fecha);
		mj.escribirMesa(m);
	}
	
	public String partGanador() {
		int totalVotos = 0;
		int a = 0;
		int b = 0;
		double p = 0.00F;
		String ganador = "";
		Iterator<Mesa> iterador = mesas.iterator();
		while(iterador.hasNext()) {
			Mesa m = iterador.next();
			totalVotos += m.getCantVotosA() + m.getCantVotosB();
			a += m.getCantVotosA();
			b += m.getCantVotosB();
		}
		ganador = a > b ? "a" : "b";
		if(a > b) {
			p = a / totalVotos;
		}else {
			p = b / totalVotos;
		}
		
		ganador += " " + p;
 		return ganador;
	}
	
	
	
	public ArrayList<String> getNombresProvs(){
		ArrayList<String> n = new ArrayList<>();
		for(Provincia p : provincias) {
			n.add(p.getNombre());
		}
		
		Collections.sort(n);
		return n;
	}
	
	public ArrayList<String> getNombresLoc(String nomP){
		ArrayList<String> n = new ArrayList<String>();
		for(Localidad l : localidades) {
			if(l.getProv().getNombre().equals(nomP)) {
				n.add(l.getNombre());
			}
		}
		return n;
	}
	
	public Localidad buscarLoc(String loc) {
		for(Localidad l : localidades) {
			if(l.getNombre().equals(loc)) {
				return l;
			}
		}
		return null;
	}
	
	public Localidad buscarLoc(String loc, String prov) {
		for(Localidad l : localidades) {
			if(l.getNombre().equals(loc) && l.getProv().getNombre().equals(prov)) {
				return l;
			}
		}
		return null;
	}
	
	public void puntoA() {
		int a = 0;
		for(Mesa m : mesas) {
			if(m.getCantVotosA() == arg) {
				a++;
			}
		}
		System.out.print(a);
	}
	
	public ArrayList<String> puntoB() {
		ArrayList<String> n = new ArrayList<>();
		for(Provincia p : provincias) {
			for(Mesa m : mesas) {
				if(m.getCantVotosA() + m.getCantVotosB() == 0 && m.getLoc().getProv().getCod() == p.getCod()) {
					n.add(p.getNombre());
				}
			}
		}
		return n;
	}
	
	public ArrayList<Mesa> puntoC(){
		ArrayList<Mesa> x = new ArrayList<>();
		mesas.sort((a,b) -> Integer.compare(b.getCantVotosA() + b.getCantVotosB(), a.getCantVotosA() + a.getCantVotosB()));
		for(int i = 0; i<3; i++) {
			x.add(mesas.get(i));
		}
		
		return x;
	}
	
	public Map<Integer, Integer> puntoD(){
		HashMap<Integer, Integer> x = new HashMap<>();
		
		for(Mesa m : mesas) {
			x.put(m.getNumero(), m.getCantVotosA() + m.getCantVotosB());
		}
		
		return x;
	}
	
}
