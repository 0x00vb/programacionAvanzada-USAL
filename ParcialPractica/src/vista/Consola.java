package vista;
import controlador.*;
import java.util.Scanner;

public class Consola {
	private String[] args;
	private EstadiaControlador estadiaControlador = new EstadiaControlador();
	
	
	
	Scanner scanner = new Scanner(System.in);
	public static void mostrarTexto(String texto) {
		System.out.println(texto);
	}
	
	public void menu() {
		int opcion;
		do {
			System.out.println("Seleccionar opcion:");
			System.out.println("1. punto a");
			System.out.println("2. punto b");
			System.out.println("3. punto c");
			System.out.println("4. punto d");
			opcion = scanner.nextInt();
			
			switch(opcion) {
				case 1:
					estadiaControlador.mostrarHorasUtilizadasPorCochera();
					break;
				case 2:
					estadiaControlador.puntob();
					break;
				case 3:
					estadiaControlador.puntoc();
					break;
				case 4:
					estadiaControlador.puntod(Integer.parseInt(args[0]));
					break;
			}
		}while(opcion < 1 || opcion > 4);

	}
}
