package modelo.dao;
import java.sql.*;
import java.util.*;
import controlador.*;
import modelo.Repuesto;


public class RepuestosSQL {
    private ConexionSQL conexionDB = new ConexionSQL();
    private Connection conn = conexionDB.Conexion();

    public ArrayList<Repuesto> leerRepuestos() throws SQLException {
        ArrayList<Repuesto> repuestos = new ArrayList<>();

        try{
            Statement instruccion = conn.createStatement();
            ResultSet resultado = instruccion.executeQuery("SELECT * FROM Repuestos");

            while (resultado.next()) {
                String nombre = resultado.getString("nombre");
                int codigo = resultado.getInt("codigo");
                double costo = resultado.getDouble("costo");
                String marca = resultado.getString("marca");
                Repuesto repuesto = new Repuesto(nombre, codigo, costo, marca);
                repuestos.add(repuesto);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return repuestos;
    }
}
