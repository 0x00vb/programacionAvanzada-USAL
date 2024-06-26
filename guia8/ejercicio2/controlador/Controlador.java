package controlador;
import modelo.*;
import modelo.modeloDAO.ArchivoTXT;
import vista.*;
import java.io.*;
import java.util.*;

public class Controlador{
    public void method1() throws NotFoundException{
        GestionTratamiento gestion = new GestionTratamiento(10, new Vector<Tratamiento>());

        print("Resultados de los métodos:");
        print("----------------------------------");

        print("Valor Maximo: " + gestion.valorMaximo());

        print("Quitar objeto: ");
        System.out.print("Lista antes: ");
        for(Tratamiento tratamiento : gestion.getTratamientos()){
            System.out.print("" + tratamiento.getNombre());
        }
        print("Lista despues: ");
        for(Tratamiento tratamiento : gestion.getTratamientos()){
            System.out.print("" + tratamiento.getNombre());
        }

        print("Ordenar descendentemente: ");
        ArrayList<Tratamiento> tratamientos = gestion.ordenDescendente();
        for(Tratamiento tratamiento : tratamientos){
            System.out.print("" + tratamiento.getPrecio());
        }

        print("Hay repetidos: ");
        print(gestion.hayRepetidos() ? "S" : "N");

        List<Integer> array = new ArrayList<Integer>();
        array.add(2);
        array.add(3);
        array.add(4);
        for(Integer i : array){
            System.out.print(" "+ i);
        }
        print("Hay valores iguales: ");
        print(gestion.igualValores(array) ? "S" : "N");


        print("Agregar lista al final");
        List<Tratamiento> nuevosTratamientos = new ArrayList<Tratamiento>();
        nuevosTratamientos.add(new Tratamiento("tratamient1", 100.00, false, 3, 's'));
        gestion.agregar(nuevosTratamientos);
        print("Lista actualizada.");
        for(Tratamiento tratamiento : gestion.getTratamientos()){
            System.out.print(" " + tratamiento.getNombre());
        }

        print("Busqueda multiple: (busco unmero 1000)");
        print("Apariencias del nuemero 1090:" + gestion.busquedaMultiple(1000));

        print("Distintos valores: ");
        for(Tratamiento tratamiento : gestion.distintosValores(tratamientos)){
            System.out.print("" + tratamiento.getNombre());
        }

        print("copiar lista:");
        
        print("Nueva lista: ");
        for(Integer i : gestion.copiarLista(0, 3)){
            System.out.print(" " + i);
        }

    }

    public void print(String a){
        CLIvista.mostrarTexto(a);
        ArchivoTXT.guardarEnTxt(a);
    }
}