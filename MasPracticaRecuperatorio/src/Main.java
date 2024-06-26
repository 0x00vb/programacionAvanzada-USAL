import controlador.Controlador;
import vista.Pantalla;

public class Main {
	public static void main(String args[]) {
		Controlador c = new Controlador();
		c.puntoA(args[0]);
		new Pantalla();
	}
}
