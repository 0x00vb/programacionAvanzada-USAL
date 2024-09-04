package modelo.dao;
import java.io.*;

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
                // Leer cada línea y verificar si el código de reparación coincide
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
}
