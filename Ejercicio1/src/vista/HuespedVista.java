package vista;

import java.util.Scanner;

public class HuespedVista {
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


    public static int ingresarDni(){
        return Validaciones.validarInt(0, 50000000);
    }
}