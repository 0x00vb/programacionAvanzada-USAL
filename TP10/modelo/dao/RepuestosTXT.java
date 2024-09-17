package modelo.dao;

import modelo.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RepuestosTXT {
    public static ArrayList<Repuesto> leerRepuestos() {
        ArrayList<Repuesto> repuestos = new ArrayList<>();
        File archivo = new File("repuestos.txt");

        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                
                String nombre = linea.substring(0, 20).trim();
                int codigo = Integer.parseInt(linea.substring(20, 25).trim());
                double costo = Double.parseDouble(linea.substring(25, 30).trim());
                String marca = linea.substring(30).trim();

                Repuesto repuesto = new Repuesto(nombre, codigo, costo, marca);
                repuestos.add(repuesto);
            }
        }catch(FileNotFoundException e){
            System.out.println("Error al leer el archivo de repuestos");
        }

        return repuestos;
    }}
