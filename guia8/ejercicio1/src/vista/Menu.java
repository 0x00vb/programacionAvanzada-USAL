package vista;

import controlador.*;

public class Menu {
    private String[] args;
    private EstadiaControlador estadiaControlador = new EstadiaControlador();
    private ServiciosControlador serviciosControlador = new ServiciosControlador();
    private HuespedControlador huespedControlador = new HuespedControlador();
    private MenuControlador menuControlador = new MenuControlador();

    public Menu(String[] args){
        this.args = args;
    }

    public void mostrarMenu(){
        int opcion;
        do{
            System.out.println("Ingrese una opcion: ");
            System.out.println("1. Registrar estadia.");
            System.out.println("2. Registro servicio de limpieza extra.");
            System.out.println("3. Registro de consumos.");
            System.out.println("4. Nombre del plato principal cuyo precio supera el valor ingresado como argumento de la aplicación y nunca haya sido solicitado.");
            System.out.println("5. Número de la cabaña con hidromasaje que solo se haya utilizado en el transcurso del último mes.");
            System.out.println("6. Cantidad de huéspedes que hayan usado el complejo en más de 3 oportunidades. ");
            System.out.println("7.  Información  completa  de  las  unidades  de  alojamiento  ocupadas  incluyendo toda la informacion de sus ocupantes.");

            opcion = Validaciones.validarInt(0,6);
            switch (opcion) {
                case 1:
                    estadiaControlador.registrarEstadia();
                    break;
                case 2:
                    serviciosControlador.registrarServicioLimpieza();
                    break;
                case 3:
                    serviciosControlador.registroServicioBar();
                    break;
                case 4:
                    menuControlador.metodoAdicional1(args);
                    break;
                case 5:
                    estadiaControlador.metodoAdicional2();
                    break;
                case 6:
                    huespedControlador.metodoAdicional3();
                    break;
                case 7:
                    estadiaControlador.metodoAdicional4();
                    break;
                default:
                    break;
            }
        }while(true);
        
    }
}
