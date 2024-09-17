package modelo.dao;

import java.sql.*;
import java.util.*;
import controlador.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import modelo.Auto;
import modelo.Cliente;
import modelo.Moto;
import modelo.Vehiculo;

public class VehiculoSQL {
    private ConexionSQL conexionDB = new ConexionSQL();
    private Connection conn = conexionDB.Conexion();
    private static ClienteControlador clienteControlador= new ClienteControlador();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public ArrayList<Vehiculo> leerVehiculos(){
        
        ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();

        try {
            Statement instruccion = conn.createStatement();
            ResultSet resultado = instruccion.executeQuery("SELECT * FROM Vehiculos");
            while(resultado.next()){
                int dniCliente = resultado.getInt("dniCliente");
                String patente = resultado.getString("patente");
                String marca = resultado.getString("marca");
                String modelo = resultado.getString("modelo");
                int anio = resultado.getInt("anio");
                char tipo = resultado.getString("tipo").charAt(0);
                Cliente cliente = clienteControlador.buscarCliente(dniCliente);
                Calendar fechaCarga = Calendar.getInstance();
                fechaCarga.setTime( dateFormat.parse(resultado.getString("fechaCarga")) );

                Vehiculo v;

                if(tipo == 'M'){
                    int cilindrada = resultado.getInt("cilindrada");
                    boolean tieneSidecar = resultado.getBoolean("tieneSidecar");
                    v = new Moto(patente, marca, modelo, anio, cilindrada, tieneSidecar, cliente, fechaCarga);
                }else{
                    int numeroPuertas = resultado.getInt("numeroPuertas");
                    String tipoCombustible = resultado.getString("tipoCombustible");
                    v = new Auto(patente, marca, modelo, anio, numeroPuertas, tipoCombustible, cliente, fechaCarga);
                }
                vehiculos.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return vehiculos;
    }
}
