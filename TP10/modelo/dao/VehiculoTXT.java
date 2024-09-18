package modelo.dao;
import modelo.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import controlador.ClienteControlador;
import controlador.RepuestosControlador;

public class VehiculoTXT {
    private static ClienteControlador clienteControlador= new ClienteControlador();
    private static final String ARCHIVO_VEHICULOS = "vehiculos.txt";
    private static SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
    private static final String ARCHIVO_REPARCIONES = "reparaciones.txt";
    public static ArrayList<Vehiculo> leerVehiculos() {
        ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
        File archivoVehiculos = new File(ARCHIVO_VEHICULOS);

        try (Scanner scanner = new Scanner(archivoVehiculos)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                Vehiculo vehiculo = parsearLineaAVehiculo(linea);

                File archivoReparaciones = new File(ARCHIVO_REPARCIONES);
                Scanner reparacionesScanner = new Scanner(archivoReparaciones);

                while(reparacionesScanner.hasNextLine()) {
                    String reparacionLinea = reparacionesScanner.nextLine();
                    String[] reparacionPartes = reparacionLinea.split(";");
                    String patente = reparacionPartes[0];
                    if(patente.equals(vehiculo.getPatente())){
                        Calendar fechaIngreso = Calendar.getInstance();
                        Calendar fechaEgreso = Calendar.getInstance();
                        int cod = Integer.parseInt(reparacionPartes[1]);
                        String descripcion = reparacionPartes[2];
                        double costo = Double.parseDouble(reparacionPartes[3]);
                        fechaIngreso.setTime(dateF.parse(reparacionPartes[4]));
                        fechaEgreso.setTime(dateF.parse(reparacionPartes[5]));
                        boolean lavado = Boolean.parseBoolean(reparacionPartes[6]);
                        boolean entregaRapida = Boolean.parseBoolean(reparacionPartes[7]);
                        ArrayList<Repuesto> repuestos = new ArrayList<>();
                        if (!reparacionPartes[8].isEmpty()) {
                            String[] repuestoCodes = reparacionPartes[8].split(",");
                            for (String code : repuestoCodes) {
                                repuestos.add( new RepuestosControlador().buscarRepuesto(Integer.parseInt(code)) );
                            }
                        }
                        vehiculo.agregarReparación(cod, descripcion, costo, fechaIngreso, fechaEgreso, repuestos, lavado, entregaRapida);
                    }
                }
                listaVehiculos.add(vehiculo);
            }
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
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