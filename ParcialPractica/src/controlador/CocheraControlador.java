package controlador;
import modelo.*;
import java.util.*;

import modelo.CocheraTXT;

public class CocheraControlador {
	private ArrayList<Cochera> cocheras = CocheraTXT.leerCocheras();
	Tarifa t1 = new Tarifa("auto", 10, 100.0);
	Cochera c1 = new Cochera(1, true, "norte", t1);
	
	public CocheraControlador() {
		this.cocheras.add(c1);
	}
	
	public Cochera buscarCochera(int numeroCochera) {
		for(Cochera c : cocheras) {
			if(c.getNumero() == numeroCochera){
				return c;
			}
		}
		return null;
	}
	
	public ArrayList<Integer> buscarCochera(String tipo) {

		ArrayList<Integer> numerosCocherasValidas = new ArrayList<Integer>();
		for(Cochera c : cocheras) {
			if(c.getTarifa().getTipo().equals(tipo)){
				numerosCocherasValidas.add(c.getNumero());
			}
		}
		return numerosCocherasValidas;
	}
	
}
