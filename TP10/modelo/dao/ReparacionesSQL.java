package modelo.dao;
import java.sql.*;
import java.util.*;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import modelo.*;

public class ReparacionesSQL {
    private ConexionSQL conexionDB = new ConexionSQL();
    private Connection conn = conexionDB.Conexion();

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
