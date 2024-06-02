package ejercicio2;

import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args){
        GestionTratamiento gestion = new GestionTratamiento(10, new Vector<Tratamiento>());

        System.out.println("Resultados de los métodos:");
        System.out.println("----------------------------------");

        File archivo = null;
        PrintWriter archivoSalida = null;
        try{
            archivo = new File("./resultados.txt");
            archivoSalida = new PrintWriter(new FileOutputStream(archivo));

            System.setOut(new PrintStream(archivo));

            System.out.println("Valor Maximo: " + gestion.valorMaximo());

            System.out.println("Quitar objeto: ");
            System.out.print("Lista antes: ");
            for(Tratamiento tratamiento : gestion.getTratamientos()){
                System.out.print("" + tratamiento.getNombre());
            }
            System.out.println("Lista despues: ");
            for(Tratamiento tratamiento : gestion.getTratamientos()){
                System.out.print("" + tratamiento.getNombre());
            }

            System.out.println("Ordenar descendentemente: ");
            ArrayList<Tratamiento> tratamientos = gestion.ordenDescendente();
            for(Tratamiento tratamiento : tratamientos){
                System.out.print("" + tratamiento.getPrecio());
            }

            System.out.println("Hay repetidos: ");
            System.out.println(gestion.hayRepetidos() ? "S" : "N");

            List<Integer> array = new ArrayList<Integer>();
            array.add(2);
            array.add(3);
            array.add(4);
            for(Integer i : array){
                System.out.print(" "+ i);
            }
            System.out.println("Hay valores iguales: ");
            System.out.println(gestion.igualValores(array) ? "S" : "N");


            System.out.println("Agregar lista al final");
            List<Tratamiento> nuevosTratamientos = new ArrayList<Tratamiento>();
            nuevosTratamientos.add(new Tratamiento("tratamient1", 100.00, false, 3, 's'));
            gestion.agregar(nuevosTratamientos);
            System.out.println("Lista actualizada.");
            for(Tratamiento tratamiento : gestion.getTratamientos()){
                System.out.print(" " + tratamiento.getNombre());
            }

            System.out.println("Busqueda multiple: (busco unmero 1000)");
            System.out.println("Apariencias del nuemero 1090:" + gestion.busquedaMultiple(1000));

            System.out.println("Distintos valores: ");
            for(Tratamiento tratamiento : gestion.distintosValores(tratamientos)){
                System.out.print("" + tratamiento.getNombre());
            }

            System.out.println("copiar lista:");
            
            System.out.println("Nueva lista: ");
            for(Integer i : gestion.copiarLista(0, 3)){
                System.out.print(" " + i);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(archivoSalida != null){
                archivoSalida.close();
            }
        }




    }
}