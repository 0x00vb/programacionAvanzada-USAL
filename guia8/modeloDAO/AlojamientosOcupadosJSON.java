package modeloDAO;

import java.io.*;
import javax.json.*;

import modelo.Alojamiento;
import modelo.Estadia;
import modelo.Huesped;
import vista.Validaciones;

public class AlojamientosOcupadosJSON {
    public static void escribirInformacion(Estadia estadia){
        File archivo = null;
        FileOutputStream archivoSalida = null;
        try{
            archivo = new File("../alojamientosOcupados.java");
            archivoSalida = new FileOutputStream(archivo);
            JsonWriter jsonWriter = Json.createWriter(archivoSalida);
            
            Alojamiento alojamiento = estadia.getAlojamiento();

            JsonObjectBuilder contructorObjetoAlojamientoOcupado = Json.createObjectBuilder()
                .add("Numero", alojamiento.getId())
                .add("area", alojamiento.getArea())
                .add("categoria", alojamiento.getTarifaDiaria().getCategoria())
                .add("tarifa", alojamiento.getTarifaDiaria().getTarifa())
                .add("fechaIngreso", Validaciones.obtenerFechaFormateada(estadia.getFechaIngreso()));
                
            JsonArrayBuilder arrayHuespedes = Json.createArrayBuilder();
            for(Huesped huesped : estadia.getHuespedes()){
                JsonObjectBuilder objetoHuesped = Json.createObjectBuilder()
                    .add("nombre", huesped.getNombre())
                    .add("apellido", huesped.getApellido());
                arrayHuespedes.add(objetoHuesped);
            }
            contructorObjetoAlojamientoOcupado.add("huespedes", arrayHuespedes);
            JsonObject objetoAlojamientoOcupado = contructorObjetoAlojamientoOcupado.build();

            jsonWriter.writeObject(objetoAlojamientoOcupado);
        }catch(IOException err){
            err.printStackTrace();
        }finally{
            try{
                if(archivoSalida != null){archivoSalida.close();}
            }catch(Exception err){
                err.printStackTrace();
            }
        }
    }   
}
