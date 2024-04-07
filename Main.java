import java.util.Scanner;
import java.util.Calendar;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Sucursal[] sucursales = new Sucursal[10];
        Prepaga[] prepagas = new Prepaga[15];

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
            double topeReintegro = Validaciones.validarDouble();

            prepagas[i] = new Prepaga(nombre, planes, topeReintegro);
        }

        System.out.println("Cantidad de Tratamientos ofrecidos: ");
        int cantidadTratamientosOfrecidos = Validaciones.validarInt();
        Tratamiento[] tratamientos = new Tratamiento[cantidadTratamientosOfrecidos]; 
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
            
            tratamientos[i] = new Tratamiento(nombre, precioSesion, inyectable, cantMaxSesiones, tipoTratamiento);
        }

        // Ingreso datos clientes completo (Punto c)
        System.out.println("[*] Informacion nomina clientes.");
        System.out.println("Cantidad de Clientes: ");
        int cantidadClientes = Validaciones.validarInt();
        Cliente[] clientes = new Cliente[cantidadClientes];
        for(int i = 0; i < cantidadClientes; i++){
            System.out.println("Nombre: ");
            String nombreCliente = scanner.nextLine();

            System.out.println("Numero Dni: ");
            int numeroDni = Validaciones.validarInt();

            System.out.println("Fecha de Nacimiento");
            Calendar fechaNacimiento = Validaciones.validarFecha();

            System.out.println("Objetivo: ");
            String objetivo = scanner.nextLine();
            
            System.out.println("Sucursales donde puede ser atendido: ");
            for(int j = 0; j < sucursales.length; j++){
                System.out.printf("%d. %s", j + 1, sucursales[j].getNombre());
            }
            System.out.print("Cantidad de sucursales en las que quiere ser atendido: ");
            int cantidadSucursalesCliente = Validaciones.validarInt();
            Sucursal[] sucursalesCliente = new Sucursal[cantidadSucursalesCliente];

            for(int j = 0; j < cantidadSucursalesCliente; j++){
                while (true) {
                    System.out.printf("Nombre de Sucursal %d: \n", j + 1);
                    String nombreSucursal = scanner.nextLine();
                    if(Validaciones.buscarSucursal(sucursales, nombreSucursal) != null){
                        if(Validaciones.buscarSucursal(sucursalesCliente, nombreSucursal) == null){
                            sucursalesCliente[j] = Validaciones.buscarSucursal(sucursales, nombreSucursal);
                            break;
                        }else{
                            System.out.println("Esa sucursal ya fue seleccionada! Intente de nuevo.");
                        }
                    }else{
                        System.out.println("Esa sucursal no existe! Intente de nuevo.");
                    }
                }
            }
            
            char formaPago;
            do{
                System.out.printf("Forma de pago: \ne. Efectivo. \nd. Tarjeta de debito. \nc. Tarjeta de credit\n");
                formaPago = scanner.nextLine().toLowerCase().charAt(0);
            }while(formaPago != 'e' && formaPago != 'd' && formaPago != 'c');
            
            System.out.println("Prepagas con las que opera el centro: ");
            for(Prepaga prepaga : prepagas){
                System.out.printf("%s |", prepaga);
            }

            String poseePrepagaValida;
            do {
                System.out.println("El cliente posee alguna de las siguientes prepagas: (s/n)");
                poseePrepagaValida = scanner.nextLine().toLowerCase(); 
            } while (!poseePrepagaValida.equals("s") && !poseePrepagaValida.equals("n"));
    
            if(poseePrepagaValida == "s"){
                String nombrePrepaga;
                Prepaga prepagaCliente;
                while(true){
                    System.out.println("Ingrese el nombre de la prepaga");
                    nombrePrepaga = scanner.nextLine();
                    if(Validaciones.buscarPrepaga(prepagas, nombrePrepaga) != null){
                        prepagaCliente = Validaciones.buscarPrepaga(prepagas, nombrePrepaga);
                        break;
                    }else{
                        System.out.println("Este centro no trabaja con esa prepaga!");
                    }
                }

                System.out.println("Ingrese el numero de afiliado: ");
                long numeroAfiliado = Validaciones.validarLong();

                clientes[i] = new ConPrepaga(nombreCliente, numeroDni, fechaNacimiento, objetivo, sucursalesCliente, formaPago, prepagaCliente, numeroAfiliado);
            }else{
                System.out.println("Se registrara al Cliente como particular. Ingrese porcentaje de descuento. (Si no tiene ingrese 0): ");
                double porcentajeDescuento = Validaciones.validarDouble();
                clientes[i] = new Particular(nombreCliente, numeroDni, fechaNacimiento, objetivo, sucursalesCliente, formaPago, porcentajeDescuento);
            }

            System.out.println("Cantidad de tratamientos: ");
            int cantidadTratamientosPersonales = Validaciones.validarIntConLimites(scanner, 1, 10);
            for(int j = 0; j < cantidadTratamientosPersonales; j++){
                System.out.printf("Tratamiento %d.\n", j + 1);
                System.out.println("Seleccione uno de los siguientes tratamientos escribiendo su nombre: ");
                for(int z = 0; z < tratamientos.length; z++){
                    System.out.printf("[#] %d.\n", tratamientos[z].getNombre());
                }
                while(true){
                    System.out.printf("Tratamiento %d: ", j + 1);
                    String nombreTratamientoSeleccionado = scanner.nextLine();
                    Tratamiento tratamientoSeleccionado;
                    if(Validaciones.buscaTratamiento(tratamientos, nombreTratamientoSeleccionado) != null){
                        for(TratamientosPersonales tratamientoPersonal : clientes[i].getTratamientosPersonales()){
                            if(tratamientoPersonal.getTratamiento().getNombre() == nombreTratamientoSeleccionado){
                                System.out.println("Este tratamiento ya ha sido asignado al cliente! Intente nuevamente.");
                                break;
                            }
                            tratamientoSeleccionado = Validaciones.buscaTratamiento(tratamientos, nombreTratamientoSeleccionado);
                            
                            System.out.printf("Ingrese la cantidad de sesiones. Debe ser menor a %d", tratamientoSeleccionado.getCantMaxSesiones());
                            int cantidadSesiones = Validaciones.validarIntConLimites(scanner, 1, tratamientoSeleccionado.getCantMaxSesiones());

                            if(tratamientoSeleccionado.getTipoTratamiento() == 's'){
                                System.out.println("Require consulta medica: ");
                                boolean consultaMedica = Validaciones.validarBoolean();


                            }
                        }

                    }else{
                        System.out.println("Ese tratamiento no existe. Intente nuevamente.");
                    }
                }
                
            }
        }

        // Menu
        int opcion;
        do{
            System.out.print("Elija una opcion: \n" + 
                            "1. Punto d\n" +
                            "2. Punto e\n" +
                            "3. Punto f\n" +
                            "4. Punto g\n" +
                            // Punto h en clase Particulares
                            "5. Punto i\n" +
                            "6. Punto j\n" +
                            "7. Punto k\n" + 
                            "0. Finalizar programa."
            );
            opcion = Validaciones.validarIntConLimites(scanner, 0, 7);
            switch (opcion) {
                case 1:
                    // Punto d
                    System.out.println("Ingrese el nombre de cliente");
                    String nombreCliente = scanner.nextLine();
                    Cliente[] resultadosBusquedaCliente = new Cliente[5];
                    resultadosBusquedaCliente = Validaciones.buscarClientePorNombre(clientes, nombreCliente);
                    int cantidadResultados = 0;
                    Cliente clienteAModificar;
                    for(Cliente cliente : resultadosBusquedaCliente){
                        if(cliente != null){
                            cantidadResultados++;
                        }
                    }

                    if(cantidadResultados > 1){
                        System.out.println("Clientes con el nombre ingresado: ");
                        int indiceCliente = 0;
                        for(Cliente cliente : resultadosBusquedaCliente){
                            if(cliente != null){
                                System.out.printf("%d. %s, dni: %d\n", indiceCliente, cliente.getNombre(), cliente.getNumeroDni());
                                indiceCliente++;
                            }
                        }
                        System.out.printf("Ingrese el indice del cliente quiere modificar (0 - %d): \n", cantidadResultados);
                        int seleccionCliente = Validaciones.validarInt();
                        clienteAModificar = resultadosBusquedaCliente[seleccionCliente];
                    }else if(cantidadResultados == 1){
                        clienteAModificar = resultadosBusquedaCliente[0];
                    }else{
                        System.out.println("Ese cliente no existe!");
                        clienteAModificar = null;
                    }

                    System.out.println("Tratamientos del cliente: ");
                    for(TratamientosPersonales tratamientoP : clienteAModificar.getTratamientosPersonales()){
                        System.out.printf("Tratamiento: %d\nCantidad de sesiones: %d\n", tratamientoP.getTratamiento().getNombre(), tratamientoP.getCantidadSesiones());
                        System.out.println("Desea cambiar la cantidad de sesiones: ");
                        boolean modificarCantSesiones = Validaciones.validarBoolean();
                        if(modificarCantSesiones){
                            System.out.printf("Ingrese nueva cantidad de sesiones. Deben ser menores a %d", tratamientoP.getTratamiento().getCantMaxSesiones());
                            int nuevaCantidadSesiones = Validaciones.validarIntConLimites(scanner, 1, tratamientoP.getTratamiento().getCantMaxSesiones());
                            tratamientoP.setCantidadSesiones(nuevaCantidadSesiones);
                        }else if(tratamientoP instanceof Salud){
                            boolean consultaClinicaVieja = ((Salud)tratamientoP).getConsultaClinica();
                            System.out.printf("La modificar la necesidad de consultaClinica? actualmente %s es necesaria.", (consultaClinicaVieja ? "si" : "no"));
                            boolean cambiarConsultaMedica = Validaciones.validarBoolean();
                            if(cambiarConsultaMedica){
                                ((Salud)tratamientoP).setConsultaClinica(!consultaClinicaVieja);
                            }
                        }
                        // Mostrar precio actualizado
                        System.out.printf("El precio acutalizado es: %f", clienteAModificar.calcularCosto());
                    }
                    break;

                case 2:
                    // Punto e
                    for(Cliente cliente : clientes){
                        if(cliente instanceof ConPrepaga && cliente.calcularCosto() > ((ConPrepaga)cliente).getPrepaga().getTopeReintegro()){
                            System.out.println("Numero dni: : " + cliente.getNumeroDni());
                            System.out.println("Nombre: " + cliente.getNombre());
                            Calendar fechaNacimiento = cliente.getFechaNacimiento();
                            System.out.print("Fecha Nacimiento: ");
                            int year = fechaNacimiento.get(Calendar.YEAR);
                            int month = fechaNacimiento.get(Calendar.MONTH) + 1;
                            int day = fechaNacimiento.get(Calendar.DAY_OF_MONTH);
                            System.out.printf("%d/%d/%d\n", year, month, day);
                            System.out.println("Forma de pago: " + cliente.getFormaPago());
                            System.out.println("Nombre de prepaga: " + ((ConPrepaga)cliente).getPrepaga().getNombre());

                            double costoTotalSinReintegro = 0.0;
                            for(TratamientosPersonales tratamientoP : cliente.getTratamientosPersonales()){costoTotalSinReintegro += tratamientoP.calcularCostoTratamiento();}
                            System.out.println("Costo total: (sin contar reintegro)" + costoTotalSinReintegro);
                            System.out.println("Tope de reintegro: " + ((ConPrepaga)cliente).getPrepaga().getTopeReintegro());
                            System.out.println("Valor realmente a pagar: " + cliente.calcularCosto());
                        }
                    }
                    break;

                case 3:
                    // Punto f      
                    Calendar fechaHace3Meses = Calendar.getInstance();
                    fechaHace3Meses.add(Calendar.MONTH, -3);
                    for(Tratamiento tratamiento : tratamientos){
                        if(tratamiento.getInyectable() && tratamiento.getTipoTratamiento() == 'c'){
                            boolean utilizado = false;
                            for(Cliente cliente : clientes){
                                for(TratamientosPersonales tratamientoP : cliente.getTratamientosPersonales()){
                                    if(tratamientoP.getTratamiento().getNombre() == tratamiento.getNombre() && cliente.getFechaInicio().after(fechaHace3Meses)){
                                        utilizado = true;
                                        break;
                                    }
                                }
                            }
                            if(!utilizado){
                                System.out.println("El tratamiento no utilizado es: " + tratamiento.getNombre());
                            }
                        }
                    }
                    break;

                case 4:
                    // Punto g
                    int cantidadClientesSoloAtendidosEnBS = 0;
                    for(Cliente cliente : clientes){
                        boolean seAtiendeSoloEnBS = true;
                        for(Sucursal sucursal : cliente.getSucursales()){
                            seAtiendeSoloEnBS = (sucursal.getProvincia().toLowerCase() != "buenos aires") ? false : true; 
                        }
                        cantidadClientesSoloAtendidosEnBS += seAtiendeSoloEnBS ? 1 : 0;
                    }
                    System.out.printf("Clientes que solo se atienden en capital: %d", cantidadClientesSoloAtendidosEnBS);
                    break;

                case 5:
                    // Punto i
                    if(args.length == 0){
                        System.out.println("No se han asignado parametros a la aplicacion. Deteniendo el programa...");
                        return;
                    }
                    double valorParametroAplicacion;
                    try {
                        valorParametroAplicacion = Double.parseDouble(args[0]);
                    } catch (NumberFormatException e) {
                        System.err.println("Error: El parámetro ingresado no es un número válido.");
                        return;
                    }
                    Calendar fechaHace30Dias = Calendar.getInstance();
                    fechaHace30Dias.add(Calendar.DAY_OF_YEAR, -30);
                    for(Cliente cliente : clientes){
                        if(cliente.getFechaInicio().after(fechaHace30Dias) && cliente.getFormaPago() == 'd'){
                            System.out.println("Nombre: " + cliente.getNombre());
                            if(cliente instanceof ConPrepaga){
                                System.out.println("Nombre prepaga: " + ((ConPrepaga)cliente).getPrepaga().getNombre());
                                System.out.printf("Numero afiliado: %d\n" + ((ConPrepaga)cliente).getnumeroAfiliado());
                            }else{
                                double porcentajeDescuento = ((Particular)cliente).getPorcentajeDescuento();
                                if(cliente.getFormaPago() == 'e'){
                                    porcentajeDescuento += porcentajeDescuento * 0.05;
                                }
                                System.out.println("Porcentaje de descuento: " + porcentajeDescuento);
                            }

                            System.out.println("Tratamientos con costo menor al argumento de aplicacion: ");
                            for(TratamientosPersonales tratamientoP : cliente.getTratamientosPersonales()){
                                if(tratamientoP.calcularCostoTratamiento() < valorParametroAplicacion){
                                    System.out.println("Nombre: " + tratamientoP.getTratamiento().getNombre());
                                    System.out.println("Cantidad de Sesiones: " + tratamientoP.getCantidadSesiones());
                                    if(tratamientoP instanceof Salud){
                                        if(((Salud)tratamientoP).getConsultaClinica()){
                                            System.out.printf("Requiere de consulta medica. Valor adicional: %f", ((Salud)tratamientoP).getValorAdicional());
                                        }else{
                                            System.out.println("No requiere de consula medica extra.");
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;

                case 6:
                    // Punto j
                    for(Sucursal sucursal : sucursales){
                        int totalClientes = 0;
                        int clientesConPrepaga = 0;

                        for(Cliente cliente : clientes){
                            for(Sucursal sucursalCliente : cliente.getSucursales()){
                                if(sucursal.getNombre() == sucursalCliente.getNombre()){
                                    totalClientes++;
                                    if(cliente instanceof ConPrepaga){
                                        clientesConPrepaga++;
                                    }
                                }
                            }
                        }

                        int clientesParticulares = totalClientes - clientesConPrepaga;
                        double porcentajeConPrepaga = (double) clientesConPrepaga / totalClientes * 100;
                        double porcentajeParticulares = 100.0 - porcentajeConPrepaga;

                        System.out.printf("Cantidad clientes con prepaga: %d (%f%)", clientesConPrepaga, porcentajeConPrepaga);
                        System.out.printf("Cantidad clientes particulares: %d (%f%)", clientesParticulares, porcentajeParticulares);
                    }
                    break;

                case 7:
                    // Punto k
                    Random random = new Random();
                    // Generar un número aleatorio de dos dígitos entre 01 y 99
                    int numeroAleatorio = random.nextInt(99) + 1; // Genera un número entre 1 y 99
                    Calendar fechaActual = Calendar.getInstance();
                    int contadorClientes = 0;
                    for(Cliente cliente : clientes){
                        Calendar fechaNacimiento = cliente.getFechaNacimiento();
                        int edad = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);        
                        // Ajustar la edad si aún no ha pasado el cumpleaños este año
                        if (fechaActual.get(Calendar.DAY_OF_YEAR) < fechaNacimiento.get(Calendar.DAY_OF_YEAR)) {
                            edad--;
                        }

                        boolean requiereConsultaMedica = false;
                        for(TratamientosPersonales tratamientoP : cliente.getTratamientosPersonales()){
                            requiereConsultaMedica = ((Salud)tratamientoP).getConsultaClinica() && true;
                        }

                        if(edad > numeroAleatorio && requiereConsultaMedica){contadorClientes++;}
                    }
                    System.out.printf("%d clientes cumplen con las condiciones dadas en el punto k.", contadorClientes);                  
                    break;

                default:
                    break;
            }

        }while(opcion != 0);
    }
}
