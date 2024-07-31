package controlador;
import modelo.dao.*;
import modelo.*;

import java.text.ParseException;
import java.util.*;

public class Controlador {
	private LibrosJSON lJson = new LibrosJSON();
	private ArrayList<Libro> libros = null;
	Calendar date = Calendar.getInstance();
	public Controlador() {
		try {
			libros = lJson.leerLibros();		
		}catch(ParseException e) {
			e.printStackTrace();
		}		
	}
	
	public void cargarPrestamo(int dni, Calendar f1, Calendar f2) {
		
	}
	
	public ArrayList<Libro> a() {
		ArrayList<Libro> list = new ArrayList<>();
		
		for(Libro l : libros) {
			if(l.getPrestamos().size() > 5) {
				list.add(l);
			}
		}
		
		list.sort(());
		
		return list;
	}
	
	
	public Libro buscarLibro(int cod) {
		for(Libro l : libros) {
			if(l.getCod() == cod) {
				return l;
			}
		}
		return null;
	}
	
	
}
