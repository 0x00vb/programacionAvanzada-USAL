package modelo.dao;
import modelo.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import controlador.ClienteControlador;
import modelo.Vehiculo;

public class VehiculoTXT {
    private static ClienteControlador clienteControlador= new ClienteControlador();
    private static final String ARCHIVO_VEHICULOS = "vehiculos.txt";
    private static SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
    
    public static ArrayList<Vehiculo> leerVehiculos() throws FileNotFoundException, ParseException {
        ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
        File archivoVehiculos = new File(ARCHIVO_VEHICULOS);

        try (Scanner scanner = new Scanner(archivoVehiculos)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                Vehiculo vehiculo = parsearLineaAVehiculo(linea);
                listaVehiculos.add(vehiculo);
            }
        }

        return listaVehiculos;
    }

    private static Vehiculo parsearLineaAVehiculo(String linea) throws ParseException {
        String[] partes = linea.split(";");
        String cliente = partes[0];
        String patente = partes[1];
        String marca = partes[2];
        String modelo = partes[3];
        int año = Integer.parseInt(partes[4]);
        char tipo = partes[5].charAt(0);
        Cliente c = clienteControlador.buscarCliente(Integer.parseInt(cliente));
        Calendar fechaCarga = Calendar.getInstance();
        fechaCarga.setTime(dateF.parse(partes[8]));
        Vehiculo v;

        if(tipo == 'M'){
            int cilindrada = Integer.parseInt(partes[6]);
            boolean tieneSidecar = Boolean.parseBoolean(partes[7]);
            v = new Moto(patente, marca, modelo, año, cilindrada, tieneSidecar, c, fechaCarga);
        }else{
            int numeroPuertas = Integer.parseInt(partes[6]);
            String tipoCombustible = partes[7];
            v = new Auto(patente, marca, modelo, año, numeroPuertas, tipoCombustible, c, fechaCarga);
        }

        return v;
    }
}