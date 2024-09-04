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
        String patente = partes[0];
        String marca = partes[1];
        String modelo = partes[2];
        int año = Integer.parseInt(partes[3]);
        char tipo = partes[4].charAt(0);
        String cliente = partes[7];
        Cliente c = clienteControlador.buscarCliente(Integer.parseInt(cliente));
        Calendar fechaCarga = Calendar.getInstance();
        fechaCarga.setTime(dateF.parse(partes[8]));
        Vehiculo v;

        if(tipo == 'M'){
            int cilindrada = Integer.parseInt(partes[5]);
            boolean tieneSidecar = Boolean.parseBoolean(partes[6]);
            v = new Moto(patente, marca, modelo, año, cilindrada, tieneSidecar, c, fechaCarga);
        }else{
            int numeroPuertas = Integer.parseInt(partes[5]);
            String tipoCombustible = partes[6];
            v = new Auto(patente, marca, modelo, año, numeroPuertas, tipoCombustible, c, fechaCarga);
        }

        return v;
    }
}