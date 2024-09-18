package modelo.dao;
import java.io.*;
import java.util.*;

import controlador.RepuestosControlador;
import controlador.VehiculoControlador;
import modelo.*;
import java.text.SimpleDateFormat;


public class ReparacionesTXT {
    private static final String ARCHIVO_REPARACIONES = "reparaciones.txt";
    private static final String ARCHIVO_TEMPORAL = "reparaciones_temp.txt";
      

    public static ArrayList<Reparacion> leerReparaciones() {
        ArrayList<Reparacion> reparaciones = new ArrayList<>();
        File archivo = new File(ARCHIVO_REPARACIONES);

        if (!archivo.exists()) {
            try {
                throw new FileNotFoundException("El archivo de reparaciones no existe.");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                String[] partes = linea.split(";");

                String patente = partes[0];
                Vehiculo vehiculo = new VehiculoControlador().buscarVehiculo(patente);
                System.out.println(vehiculo.getAño());
                
                int codigoReparacion = Integer.parseInt(partes[1]);
                String tipoReparacion = partes[2];
                double costo = Double.parseDouble(partes[3]);

                Calendar fechaIngreso = Calendar.getInstance();
                Calendar fechaEntrega = Calendar.getInstance();
                fechaIngreso.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(partes[4]));
                fechaEntrega.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(partes[5]));

                boolean lavado = Boolean.parseBoolean(partes[6]);
                boolean entregaRapida = Boolean.parseBoolean(partes[7]);

                String[] repuestosCodigos = partes[8].split(",");
                ArrayList<Repuesto> repuestos = new ArrayList<>();
                for (String codigoRepuesto : repuestosCodigos) {
                    if (!codigoRepuesto.isEmpty()) {
                        repuestos.add(new RepuestosControlador().buscarRepuesto(Integer.parseInt(codigoRepuesto)));
                    }
                }

                Reparacion reparacion = new Reparacion(codigoReparacion, tipoReparacion, costo, fechaIngreso, fechaEntrega, repuestos, lavado, entregaRapida);
                reparaciones.add(reparacion);
                vehiculo.agregarReparación(tipoReparacion, costo, fechaIngreso, fechaEntrega, repuestos, lavado, entregaRapida);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reparaciones;
    }

    public static void eliminarReparacion(int codigoReparacion) throws IOException {
        File archivoOriginal = new File(ARCHIVO_REPARACIONES);
        File archivoTemporal = new File(ARCHIVO_TEMPORAL);

        try (FileReader fileReader = new FileReader(archivoOriginal);
             BufferedReader bufferedReader = new BufferedReader(fileReader);
             FileWriter fileWriter = new FileWriter(archivoTemporal);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                if (!debeEliminar(linea, codigoReparacion)) {
                    printWriter.println(linea);
                }
            }
        }

        if (!archivoOriginal.delete()) {
            throw new IOException("No se pudo eliminar el archivo original");
        }
        if (!archivoTemporal.renameTo(archivoOriginal)) {
            throw new IOException("No se pudo renombrar el archivo temporal");
        }
    }

    private static boolean debeEliminar(String linea, int codigoReparacion) {
        String[] partes = linea.split(";");
        int codigo = Integer.parseInt(partes[0]);
        return codigo == codigoReparacion;
    }  

    public static void escribirReparacion(Reparacion reparacion, String patente) throws IOException {
        File archivo = new File(ARCHIVO_REPARACIONES);
        FileWriter fileWriter = new FileWriter(archivo, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        String repuestos = "";
        for (Repuesto repuesto : reparacion.getRepuestos()) {
            repuestos += repuesto.getCodigo() + ",";
        }
        String linea = patente + ";" + reparacion.getCodigoReparacion() + reparacion.getTipoReparacion() + ";" + reparacion.getCosto() + ";" + reparacion.getFechaIngreso() + ";" + reparacion.getFechaEntrega() + ";" + reparacion.getLavado() + ";" + reparacion.getEntregaRapida() + ";" + repuestos;
        printWriter.println(linea);
    }

    public static void modificarReparacion(Reparacion reparacion, String patente) throws IOException {
        File archivoOriginal = new File(ARCHIVO_REPARACIONES);
        File archivoTemporal = new File(ARCHIVO_TEMPORAL);

        try (Scanner scanner = new Scanner(archivoOriginal);
             FileWriter fileWriter = new FileWriter(archivoTemporal);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            int codigoReparacion = reparacion.getCodigoReparacion();
            String repuestos = "";
            for (Repuesto repuesto : reparacion.getRepuestos()) {
                if(repuesto != null) {
                    repuestos += repuesto.getCodigo() + ",";
                }
            }

            String nuevaLinea = patente + ";" + codigoReparacion + ";" + reparacion.getTipoReparacion() + ";" +
                                reparacion.getCosto() + ";" + new SimpleDateFormat("dd/MM/yyyy").format(reparacion.getFechaIngreso().getTime()) + ";" +
                                new SimpleDateFormat("dd/MM/yyyy").format(reparacion.getFechaEntrega().getTime()) + ";" +
                                reparacion.getLavado() + ";" + reparacion.getEntregaRapida() + ";" + repuestos;

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] partes = linea.split(";");
                int codigoActual = Integer.parseInt(partes[1]);

                if (codigoActual == codigoReparacion) {
                    printWriter.println(nuevaLinea);
                } else {
                    printWriter.println(linea);
                }
            }
        }

        if (!archivoOriginal.delete()) {
            throw new IOException("No se pudo eliminar el archivo original.");
        }

        if (!archivoTemporal.renameTo(archivoOriginal)) {
            throw new IOException("No se pudo renombrar el archivo temporal.");
        }
    }


}