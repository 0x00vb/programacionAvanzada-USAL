package modelo.dao;

import java.sql.*;
import java.util.*;
import controlador.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import modelo.Auto;
import modelo.Cliente;
import modelo.Moto;
import modelo.Repuesto;
import modelo.Vehiculo;

public class VehiculoSQL {
    private ConexionSQL conexionDB = new ConexionSQL();
    private Connection conn = conexionDB.Conexion();
    private static ClienteControlador clienteControlador= new ClienteControlador();
    private static RepuestosControlador repuestosControlador = new RepuestosControlador();
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

                PreparedStatement instruccionReparaciones = conn.prepareStatement("SELECT * FROM Reparaciones WHERE patente = ?");
                instruccionReparaciones.setString(1, patente);
                ResultSet rs = instruccionReparaciones.executeQuery();
                while (rs.next()) {
                    int codigoReparacion = rs.getInt("codigoReparacion");
                    String descripcion = rs.getString("descripcion");
                    double costo = rs.getDouble("costo");
                    Calendar fechaIngreso = Calendar.getInstance();
                    Calendar fechaEgreso = Calendar.getInstance();
                    fechaIngreso.setTime(dateFormat.parse(rs.getString("fechaIngreso")));
                    fechaEgreso.setTime(dateFormat.parse(rs.getString("fechaEgreso")));
                    boolean lavado = rs.getBoolean("lavado");
                    boolean entregaRapida = rs.getBoolean("entregaRapida");

                    ArrayList<Repuesto> repuestos = new ArrayList<>();
                    String repuestosCodes = rs.getString("repuestos");
                    if (!repuestosCodes.isEmpty()) {
                        String[] repuestoIds = repuestosCodes.split(",");
                        for (String id : repuestoIds) {
                            Repuesto repuesto = repuestosControlador.buscarRepuesto(Integer.parseInt(id.trim()));
                            repuestos.add(repuesto);
                        }
                    }
                    v.agregarReparación(codigoReparacion, descripcion, costo, fechaIngreso, fechaEgreso, repuestos, lavado, entregaRapida);
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
