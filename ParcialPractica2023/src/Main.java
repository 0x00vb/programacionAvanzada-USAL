import controlador.Controlador;
import vista.Pantalla;

public class Main {
	public static void main(String[] args) {
		Controlador controlador = new Controlador(args[0]);
		Pantalla pantalla = new Pantalla();
	}
}
