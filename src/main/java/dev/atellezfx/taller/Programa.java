package dev.atellezfx.taller;

import dev.atellezfx.taller.sql.DataSource;
import dev.atellezfx.taller.sql.EmpleadoDML;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Programa {

    public static void main(String[] args) {
        try( Connection conn = DataSource.obtenerConexion() ) {
            PreparedStatement pstm = conn.prepareStatement(EmpleadoDML.SQL_SELECT_EMPLEADOS);
            ResultSet rs = pstm.executeQuery();
            while( rs.next() ) {
                System.out.println(rs.getString("nombre") + " " + rs.getString("apellidos"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
