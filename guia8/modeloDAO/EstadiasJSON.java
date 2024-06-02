package modeloDAO;

import javax.json.*;
import java.io.*;
import java.util.Calendar;

import modelo.*;
import controlador.*;

public class EstadiasJSON {
    private LimpiezaTXT limpiezaTXT = new LimpiezaTXT();
    private ServicioBarTXT servicioBarTXT = new ServicioBarTXT();
    private HuespedControlador huespedControlador = new HuespedControlador();
    private AlojamientoControlador alojamientoControlador = new AlojamientoControlador();
    
    public Estadia[] leerEstadias(){
        Estadia[] estadias = new Estadia[100];
        File archivo = null;
        FileInputStream archivoEntrada = null;
        try{
            archivo = new File("../estadias.json");
            if(archivo.exists()){
                archivoEntrada = new FileInputStream(archivo);
            }else{
                throw new IOException();
            }

            JsonReader jsonReader = Json.createReader(archivoEntrada);
            JsonArray estadiasArray = jsonReader.readArray();

            int contador = 0;
            for(JsonObject jsonObject : estadiasArray.getValuesAs(JsonObject.class)){
                long fechaIngresoMilis = jsonObject.getJsonNumber("fechaIngreso").longValue();
                int cantidadDias = jsonObject.getInt("cantidadDias");
                int idAlojamiento = jsonObject.getInt("idAlojamiento");
                JsonArray documentosArray = jsonObject.getJsonArray("documentosHuespedes");
                
                Alojamiento alojamiento = alojamientoControlador.buscarAlojamiento(idAlojamiento);

                Huesped[] huespedes = new Huesped[documentosArray.size()];
                for(int i = 0; i < documentosArray.size(); i++){
                    Huesped huesped = huespedControlador.buscarHuesped(documentosArray.getInt(i));
                    huespedes[i] = huesped;
                }

                Calendar fechaIngreso = Calendar.getInstance();
                fechaIngreso.setTimeInMillis(fechaIngresoMilis);

                estadias[contador] = new Estadia(fechaIngreso, cantidadDias, alojamiento, huespedes);
                
                Calendar fechaSalida = fechaIngreso;
                fechaSalida.add(Calendar.DAY_OF_MONTH, cantidadDias);
                
                servicioBarTXT.leerConsumos(estadias[contador]);
                limpiezaTXT.leerLimpiezaExtra(estadias[contador]);

                contador++;
            }
        }catch(IOException err){
            err.printStackTrace();
        }

        return estadias;
    }

    public void escribirEstadia(Estadia estadia){
        File archivo = null;
        FileOutputStream salidaArchivo = null;
        try{
            archivo = new File("../estadias.json");
            JsonArray estadias;
            try (JsonReader reader = Json.createReader(new FileReader("estancias.json"))) {
                estadias = reader.readArray();
            }

            salidaArchivo = new FileOutputStream(archivo);
            JsonWriter jsonWriter = Json.createWriter(salidaArchivo);

            JsonArrayBuilder dniHuespedes = Json.createArrayBuilder();
            for(Huesped h : estadia.getHuespedes()){
                dniHuespedes.add(h.getDocumento());
            }

            JsonObject objetoEstadias = Json.createObjectBuilder()
                .add("idEstadia", estadia.getId())
                .add("fechaIngreso", estadia.getFechaIngreso().getTimeInMillis())
                .add("cantidadDias", estadia.getCantidadDias())
                .add("idAlojamiento", estadia.getAlojamiento().getId())
                .add("documentosHuespedes", dniHuespedes)
                .build();

            JsonArrayBuilder estadiasArrayBuilder = Json.createArrayBuilder(estadias);
            estadiasArrayBuilder.add(objetoEstadias);

            jsonWriter.writeArray(estadiasArrayBuilder.build());
            jsonWriter.close();

        }catch(IOException err){
            err.printStackTrace();
        }finally{
            try{
                if(salidaArchivo != null){salidaArchivo.close();}
            }catch(Exception err){
                err.printStackTrace();
            }
        }
    }    
}