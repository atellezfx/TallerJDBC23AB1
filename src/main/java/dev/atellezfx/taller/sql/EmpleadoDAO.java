package dev.atellezfx.taller.sql;

import dev.atellezfx.taller.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmpleadoDAO implements EmpleadoDML {

    public List<Empleado> lista() {
        ArrayList<Empleado> empleados = new ArrayList<>();
        try( Connection conn = DataSource.obtenerConexion() ) {
            PreparedStatement pstm = conn.prepareStatement(SQL_SELECT_EMPLEADOS);
            ResultSet rs = pstm.executeQuery();
            while( rs.next() ) {
                Empleado emp = crearEmpleado(rs);
                empleados.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados;
    }

    public Optional<Empleado> obtener(int id) {
        Empleado emp = null;
        try( Connection conn = DataSource.obtenerConexion() ) {
            PreparedStatement pstm = conn.prepareStatement(SQL_SELECT_EMPLEADO_POR_ID);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if( rs.next() ) return Optional.of( crearEmpleado(rs) );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public int actualizar(Empleado emp) {
        try( Connection conn = DataSource.obtenerConexion() ) {
            PreparedStatement pstm = conn.prepareStatement(SQL_UPDATE_EMPLEADO);
            pstm.setString(1, emp.getNombre());
            pstm.setString(2, emp.getApellidos());
            pstm.setString(3, emp.getEmail());
            pstm.setFloat(4, emp.getSalario());
            pstm.setInt(5, emp.getDepartamento());
            pstm.setInt(6, emp.getId());
            return pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int insertar(Empleado emp) {
        try( Connection conn = DataSource.obtenerConexion() ) {
            PreparedStatement pstm = conn.prepareStatement(SQL_INSERT_EMPLEADO);
            pstm.setString(1, emp.getNombre());
            pstm.setString(2, emp.getApellidos());
            pstm.setString(3, emp.getEmail());
            pstm.setFloat(4, emp.getSalario());
            pstm.setInt(5, emp.getDepartamento());
            return pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int eliminar(int id) {
        try( Connection conn = DataSource.obtenerConexion() ) {
            PreparedStatement pstm = conn.prepareStatement(SQL_DELETE_EMPLEADO);
            pstm.setInt(1, id);
            return pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    private Empleado crearEmpleado(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String apellidos = rs.getString("apellidos");
        String email = rs.getString("email");
        float salario = rs.getFloat("salario");
        int depto = rs.getInt("departamento");
        return new Empleado(id, nombre, apellidos, email, salario, depto);
    }

}
