package modelo.dao;

import modelo.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RepuestosTXT {
    public static ArrayList<Repuesto> leerRepuestos() throws FileNotFoundException {
        ArrayList<Repuesto> repuestos = new ArrayList<>();
        File archivo = new File("repuestos.txt");

        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                
                // Suponiendo que el formato es: NombreDelRepuesto (20 chars) Código (5 chars) Costo (resto)
                String nombre = linea.substring(0, 20).trim();
                int codigo = Integer.parseInt(linea.substring(20, 25).trim());
                double costo = Double.parseDouble(linea.substring(25).trim());

                Repuesto repuesto = new Repuesto(nombre, codigo, costo);
                repuestos.add(repuesto);
            }
        }

        return repuestos;
    }}
