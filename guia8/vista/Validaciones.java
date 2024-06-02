package vista;

import java.util.Calendar;
import java.util.Scanner;

import controlador.AlojamientoControlador;
import controlador.HuespedControlador;
import modelo.Alojamiento;
import modelo.Huesped;

public class Validaciones {
    private static AlojamientoControlador alojamientoControlador = new AlojamientoControlador();
    private static HuespedControlador huespedControlador = new HuespedControlador();
    static Scanner scanner = new Scanner(System.in);
    public static int validarInt(){
        int num;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Entrada no válida. Por favor, ingrese un número entero.");
                scanner.next();
            }
            num = scanner.nextInt();
        } while (num <= 0);
        return num;
    }

    public static int validarInt(int min, int max) {
        int input;
        do {
            input = validarInt();
            if (input < min || input > max) {
                System.out.println("El valor debe estar entre " + min + " y " + max + ". Intente de nuevo.");
            }
        } while (input < min || input > max);
        return input;
    }

    public static String obtenerFechaFormateada(Calendar fecha) {
        // Obtener día, mes y año de la fecha
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int mes = fecha.get(Calendar.MONTH) + 1; // Los meses comienzan desde 0, sumamos 1 para obtener el valor correcto
        int año = fecha.get(Calendar.YEAR);

        // Crear una cadena con el formato deseado
        String fechaFormateada = String.format("%02d/%02d/%d", dia, mes, año);

        return fechaFormateada;
    }

    public static boolean validarBoolean(){
        boolean bool;
        char userInput;
        do{
            System.out.println("s = si, n = no: ");
            userInput = scanner.nextLine().toLowerCase().charAt(0);
        }while(userInput != 's' && userInput != 'n');
        bool = userInput == 's' ? true : false;
        return bool;
    }

    public static int validarAlojamiento() {
        Alojamiento alojamientoExiste;
        int idAlojamiento;

        do {
            idAlojamiento = Validaciones.validarInt(0, 99);
            alojamientoExiste = alojamientoControlador.buscarAlojamiento(idAlojamiento);
            if (alojamientoExiste == null) {
                System.out.println("El alojamiento no existe. Intente nuevamente.");
            }
        } while (alojamientoExiste == null);

        return idAlojamiento;
    }

    public static int validarHuesped() {
        Huesped huespedExiste;
        int dniSolicitante;

        do {
            dniSolicitante = Validaciones.validarInt(0, 50000000);
            huespedExiste = huespedControlador.buscarHuesped(dniSolicitante);
            if (huespedExiste == null) {
                System.out.println("El huésped no existe. Intente nuevamente.");
            }
        } while (huespedExiste == null);

        return dniSolicitante;
    }

}
