package modelo.dao;
import java.io.*;
import java.util.*;
import modelo.*;
import java.text.SimpleDateFormat;


public class ReparacionesTXT {
    private static final String ARCHIVO_REPARACIONES = "reparaciones.txt";
    private static final String ARCHIVO_TEMPORAL = "reparaciones_temp.txt";

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

    public void modificarReparacion(Reparacion reparacion, String patente) throws IOException {
        File archivoOriginal = new File(ARCHIVO_REPARACIONES);
        File archivoTemporal = new File(ARCHIVO_TEMPORAL);

        try (Scanner scanner = new Scanner(archivoOriginal);
             FileWriter fileWriter = new FileWriter(archivoTemporal);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            int codigoReparacion = reparacion.getCodigoReparacion();
            String repuestos = "";
            for (Repuesto repuesto : reparacion.getRepuestos()) {
                repuestos += repuesto.getCodigo() + ",";
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