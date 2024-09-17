package modelo.dao;

import modelo.*;
import java.sql.*;
import java.util.*;

import controlador.ReparacionControlador;
import controlador.VehiculoControlador;

public class ConsultaMasivaSQL {
    private ConexionSQL conexionDB = new ConexionSQL();
    private Connection conn = conexionDB.Conexion();

    public void actualizarCampos(Reparacion reparacion){
        try{
            String query = "UPDATE reparaciones SET tipoReparacion = ?, costo = ?, repuestos = ? WHERE codigoReparacion = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, reparacion.getTipoReparacion());
            statement.setDouble(2, reparacion.getCosto());
            statement.setString(3, reparacion.getRepuestos().toString());
            statement.setInt(4, reparacion.getCodigoReparacion());
            statement.executeUpdate();
        }catch(SQLException err){
            err.printStackTrace();
        }
    }

}
