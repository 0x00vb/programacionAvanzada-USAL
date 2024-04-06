import java.util.Scanner;
import java.util.Calendar;

public class Validaciones {
    public static int validarInt(){
        Scanner scanner = new Scanner(System.in);
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

    public static boolean validarBoolean(){
        Scanner scanner = new Scanner(System.in);
        boolean bool;
        char userInput;
        do{
            System.out.println("s = si, n = no: ");
            userInput = scanner.nextLine().toLowerCase().charAt(0);
        }while(userInput != 's' && userInput != 'n');
        bool = userInput == 's' ? true : false;
        return bool;
    }

    
    public static double validarDouble() {
        Scanner scanner = new Scanner(System.in);
        double numero = 0;
        boolean entradaValida = false;

        do {
            System.out.print("Ingrese un número double: ");
            if (scanner.hasNextDouble()) {
                numero = scanner.nextDouble();
                entradaValida = true;
            } else {
                System.out.println("Entrada no válida. Intente de nuevo.");
                scanner.next();
            }
        } while (!entradaValida);
        return numero;
    }


    public static Sucursal buscarSucursal(Sucursal[] listSucursales, String nombreSucursal){
        for(Sucursal sucursal : listSucursales){
            if(sucursal != null &&  sucursal.getNombre() == nombreSucursal){
                return sucursal;
            }
        }
        return null;
    }
    
    public static Prepaga buscarPrepaga(Prepaga[] listaPrepagas, String nombrePrepaga){
        for(Prepaga prepaga : listaPrepagas){
            if(prepaga != null &&  prepaga.getNombre() == nombrePrepaga){
                return prepaga;
            }
        }
        return null;
    }

    public static Calendar validarFecha() {
        Scanner scanner = new Scanner(System.in);
        int year, month, day;
    
        System.out.println("Ingrese el año: ");
        year = validarIntConLimites(scanner, 0, 2024);
    
        System.out.println("Ingrese el mes (1-12): ");
        month = validarIntConLimites(scanner, 1, 12);
    
        System.out.println("Ingrese el día: ");
        day = validarIntConLimites(scanner, 1, 31);
    
        Calendar fecha = Calendar.getInstance();
        fecha.set(year, month - 1, day); // Adjust month value for Calendar's zero-based months
        return fecha;
    }

    private static int validarIntConLimites(Scanner scanner, int min, int max) {
        int input;
        do {
            input = validarInt();
            if (input < min || input > max) {
                System.out.println("El valor debe estar entre " + min + " y " + max + ". Intente de nuevo.");
            }
        } while (input < min || input > max);
        return input;
    }
}