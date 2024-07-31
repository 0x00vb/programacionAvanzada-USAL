import modelo.*;
import vista.Pantalla;
import controlador.Controlador;
public class Main {
	public static void main(String[] args) {
		new Controlador(Integer.parseInt(args[0]));
		new Pantalla();		
	}
}
