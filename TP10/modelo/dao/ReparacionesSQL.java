package modelo.dao;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import controlador.RepuestosControlador;
import modelo.*;

public class ReparacionesSQL {
    private ConexionSQL conexionDB = new ConexionSQL();
    private Connection conn = conexionDB.Conexion();
    private static RepuestosControlador repuestosControlador = new RepuestosControlador();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public ArrayList<Reparacion> leerReparaciones(){
        ArrayList<Reparacion> reparaciones = new ArrayList<>();
        try{
            Statement instruccion = conn.createStatement();
            ResultSet resultado = instruccion.executeQuery("SELECT * FROM Reparaciones");
            while (resultado.next()) {
                int codigoReparacion = resultado.getInt("codigoReparacion");
                String tipoReparacion = resultado.getString("descripcion");
                double costo = resultado.getDouble("costo");

                Calendar fechaIngreso = Calendar.getInstance();
                Calendar fechaEntrega = Calendar.getInstance();
                fechaIngreso.setTime(dateFormat.parse(resultado.getString("fechaIngreso")));
                fechaEntrega.setTime(dateFormat.parse(resultado.getString("fechaEgreso")));

                boolean lavado = resultado.getBoolean("lavado");
                boolean entregaRapida = resultado.getBoolean("entregaRapida");

                // Retrieve the spare parts related to this reparacion (assuming it's a comma-separated string of repuesto IDs)
                ArrayList<Repuesto> repuestos = new ArrayList<>();
                String repuestosCodigos = resultado.getString("repuestos");

                if (!repuestosCodigos.isEmpty()) {
                    String[] repuestoIds = repuestosCodigos.split(",");
                    for (String codigoRepuesto : repuestoIds) {
                        if (!codigoRepuesto.trim().isEmpty()) {
                            Repuesto repuesto = repuestosControlador.buscarRepuesto(Integer.parseInt(codigoRepuesto.trim()));
                            repuestos.add(repuesto);
                        }
                    }
                }

                // Create and add the reparacion object
                Reparacion reparacion = new Reparacion(codigoReparacion, tipoReparacion, costo, fechaIngreso, fechaEntrega, repuestos, lavado, entregaRapida);
                reparaciones.add(reparacion);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return reparaciones;
    }


    public void ingresarReparacion(Reparacion reparacion, String patente){
        try{
            CallableStatement instruccion = conn.prepareCall("{call proc_insertReparacion(?, ?, ?, ?, ?, ?, ?, ?, ?)}");

            String repuestos = "";
            for(Repuesto repuesto : reparacion.getRepuestos()){
                repuestos += repuesto.getNombre() + ", ";
            }

            instruccion.setString(1, patente);
            instruccion.setInt(2, reparacion.getCodigoReparacion());
            instruccion.setString(3, reparacion.getTipoReparacion());
            instruccion.setDouble(4, reparacion.getCosto());
            instruccion.setDate(5, new java.sql.Date(reparacion.getFechaIngreso().getTimeInMillis()));
            instruccion.setDate(6, new java.sql.Date(reparacion.getFechaEntrega().getTimeInMillis()));
            instruccion.setString(7, repuestos);
            instruccion.setBoolean(8, reparacion.isLavado());
            instruccion.setBoolean(9, reparacion.getEntregaRapida());

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void eliminarReparacion(int codigoReparacion){
        try{
            CallableStatement instruccion = conn.prepareCall("{call proc_deleteReparacion(?)}");
            instruccion.setInt(1, codigoReparacion);
            instruccion.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void modificarReparacion(Reparacion reparacion, String patente){
        try{
            String query = "UPDATE Reparaciones SET tipo_reparacion = ?, costo = ?, fecha_ingreso = ?, fecha_entrega = ?, " +
                        "lavado = ?, entrega_rapida = ?, WHERE codigo_reparacion = ? AND patente = ?";
            PreparedStatement instruccion = conn.prepareStatement(query);
            instruccion.setString(1, reparacion.getTipoReparacion());
            instruccion.setDouble(2, reparacion.getCosto());
            instruccion.setDate(3, new java.sql.Date(reparacion.getFechaIngreso().getTimeInMillis()));
            instruccion.setDate(4, new java.sql.Date(reparacion.getFechaEntrega().getTimeInMillis()));
            instruccion.setBoolean(5, reparacion.getLavado());
            instruccion.setBoolean(6, reparacion.getEntregaRapida());

            instruccion.setInt(7, reparacion.getCodigoReparacion());
            instruccion.setString(8, patente);

            instruccion.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
