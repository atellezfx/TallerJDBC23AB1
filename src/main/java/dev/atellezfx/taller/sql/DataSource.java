package dev.atellezfx.taller.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    public static Connection obtenerConexion() throws SQLException {
        String dburl = "jdbc:mysql://localhost:3306/empleados";
        String dbuser = "root", dbpass = "12345";
        return DriverManager.getConnection(dburl, dbuser, dbpass);
    }

}
