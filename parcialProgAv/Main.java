import controlador.Controlador;
import vista.Pantalla;

public class Main {
    public static void main(String[] args) {
        Controlador controlador = new Controlador();
        controlador.asociarLocalidadesAProvincias();
        Pantalla pantalla = new Pantalla(controlador);
    }


}