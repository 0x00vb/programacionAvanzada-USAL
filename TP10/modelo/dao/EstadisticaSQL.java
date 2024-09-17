package modelo.dao;

import  java.sql.*;
import java.util.*;

public class EstadisticaSQL {
    private ConexionSQL conexionDB = new ConexionSQL();
    private Connection conn = conexionDB.Conexion();

    public void escribirJson(String resp, Calendar fecha){
        String[] slplitResp = resp.split(" ");
        try{        
            CallableStatement instruccion = conn.prepareCall("{call proc_estadisticas(?,?,?,?,?)}");
            
            instruccion.setString(1, slplitResp[0]);
            instruccion.setString(2, slplitResp[1]);
            instruccion.setString(3, slplitResp[2]);
            instruccion.setInt(4, Integer.parseInt(slplitResp[3]));
            instruccion.setDate(5, new java.sql.Date(fecha.getTimeInMillis()));

            instruccion.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
