package vista;

import java.util.Scanner;

public class CLIVista {
    static Scanner scanner = new Scanner(System.in);
    public static void mostrarTexto(String texto){System.out.println(texto);}

    public static String ingresar(String texto){ 
        System.out.println("Ingrese " + texto);
        String a = "";
        while (a.equals("")){
            a = scanner.nextLine();
        }
        return a;
    }
}
