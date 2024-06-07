package Controlador;
import java.util.ArrayList;

import modelo.*;
import modeloDAO.*;

public class CamarotesControlador {
	ArrayList<Camarote> camarotes = CamarotesJSON.leerCamarotes();
	
	public Camarote buscarCamarote(int numeroCamarote) {
		for(Camarote c : camarotes) {
			if(c.getNumero() == numeroCamarote) {
				return c;
			}
		}
		return null;
	}
}
