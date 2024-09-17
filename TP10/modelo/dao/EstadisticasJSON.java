package modelo.dao;

import java.text.*;
import javax.json.*;
import javax.json.stream.*;
import java.io.*;
import java.util.Calendar;


public class EstadisticasJSON {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public static void escribirJson(String resp, Calendar fecha) {
        File archivo = new File("estadisticas.json");
        File tempFile = new File("estadisticas_temp.json");
        String[] data = resp.split(" ");
        boolean datosExistentes = false;

        try (OutputStream archivoSalida = new FileOutputStream(tempFile);
             JsonGenerator jsonGenerator = Json.createGenerator(archivoSalida)) {

            jsonGenerator.writeStartObject(); 

            if (archivo.exists()) {
                try (InputStream archivoEntrada = new FileInputStream(archivo);
                     JsonParser parser = Json.createParser(archivoEntrada)) {

                    while (parser.hasNext()) {
                        JsonParser.Event event = parser.next();

                        if (event == JsonParser.Event.KEY_NAME && parser.getString().equals("datos")) {
                            jsonGenerator.writeStartArray("datos");
                            datosExistentes = true;

                            while (parser.hasNext()) {
                                event = parser.next();
                                if (event == JsonParser.Event.START_OBJECT) {
                                    jsonGenerator.writeStartObject();
                                } else if (event == JsonParser.Event.END_OBJECT) {
                                    jsonGenerator.write("fecha", sdf.format(fecha.getTime()));
                                    jsonGenerator.writeEnd();
                                } else if (event == JsonParser.Event.KEY_NAME) {
                                    String key = parser.getString();
                                    parser.next();
                                    jsonGenerator.write(key, parser.getString());
                                } else if (event == JsonParser.Event.END_ARRAY) {
                                    break; 
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (!datosExistentes) {
                jsonGenerator.writeStartArray("datos");
            }

            jsonGenerator.writeStartObject()
                    .add("1", data[0])
                    .add("2", data[1])
                    .add("3", data[2])
                    .add("4", data[3])
                    .add("fecha", sdf.format(fecha.getTime()))
                    .writeEnd(); 

            jsonGenerator.writeEnd(); 
            jsonGenerator.writeEnd(); 

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (archivo.delete()) {
            tempFile.renameTo(archivo);
        }
    }
}
