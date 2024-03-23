import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Sucursal[] sucursales = new Sucursal[10];
        Prepaga[] prepagas = new Prepaga[15];

        // int opcion:
        // do{
        //     System.out.print("Elija una opcion: \n" + 
        //                     "1. \n" +
        //                     "2. \n" +
        //                     "3. \n"
        //     );
        //     switch (opcion) {
        //         case 1:
                    
        //             break;
                
        //         default:
        //             break;
        //     }

        // }while(opcion > 5);


        //Ingreso datos estetica
        for(int i = 0; i < 10; i++){
            System.out.printf("(Sucursal %d)\n", i + 1);
            String nombre;
            while(true){
                System.out.println("Nombre de la filial: ");
                nombre = scanner.nextLine();
                if(Validaciones.buscarSucursal(sucursales, nombre) == null){
                    break;
                }else{
                    System.out.println("Esta sucursal ya fue ingresada anteriormente!");
                }
            }

            System.out.println("Localidad: ");
            String localidad = scanner.nextLine();

            System.out.println("Provincia: ");
            String provincia = scanner.nextLine();

            sucursales[i] = new Sucursal(i, nombre, localidad, provincia);
        }

        for(int i = 0; i < 15; i++){
            System.out.printf("(Prepaga %d)", i + 1);
            String nombre;
            while(true){
                System.out.println("Nombre: ");
                nombre = scanner.nextLine();
                if(Validaciones.buscarPrepaga(prepagas, nombre) == null){
                    break;
                }else{
                    System.out.println("Esta prepaga ya fue cargada anteriormente!");
                }
            }

            System.out.println("Cantidad de planes: ");
            int cantPlanes = Validaciones.validarInt();

            String[] planes = new String[cantPlanes];
            for(int j = 0; j < cantPlanes; j++){
                System.out.printf("Plan %d: \n", cantPlanes + 1);
                String plan = scanner.nextLine();
                planes[j] = plan;
            }

            System.out.println("Tope maximo de reintegro: ");
            int topeReintegro = Validaciones.validarInt();

            prepagas[i] = new Prepaga(nombre, planes, topeReintegro);
        }

        System.out.println("Cantidad de Tratamientos ofrecidos: ");
        int cantidadTratamientosOfrecidos = Validaciones.validarInt();
        for(int i = 0; i < cantidadTratamientosOfrecidos; i++){
            System.out.printf("Tratamiento %d.", i + 1);
            System.out.println("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.println("Es inyectable: ");
            boolean inyectable = Validaciones.validarBoolean();

            System.out.println("Precio por sesion: ");
            double precioSesion = Validaciones.validarDouble();

            System.out.println("Cantidad maxima de sesiones: ");
            int cantMaxSesiones = Validaciones.validarInt();

            System.out.println("Tipo tratamiento.");
            char tipoTratamiento;
            do{
                System.out.println("f = facial, c = corporal, s = salud: ");
                tipoTratamiento = scanner.nextLine().toLowerCase().charAt(0);
            }while(tipoTratamiento != 'f' && tipoTratamiento != 'c' && tipoTratamiento != 's');
        }


        // punto c



    }
}
