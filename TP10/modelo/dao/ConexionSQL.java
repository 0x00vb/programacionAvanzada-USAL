package modelo.dao;
import java.sql.*;

public class ConexionSQL {
    public Connection Conexion(){
        Connection conn = null;
        String driver = "jdbc:sqlserver://localhost:1433;databaseName=ProgramacionAvanzada;encrypt=false";
        String user = "sa";
        String pass = "Valentino2004!";
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(driver, user, pass);
            System.out.println("[+] DB conectada!");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
