package dev.atellezfx.taller;

import dev.atellezfx.taller.sql.EmpleadoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Optional;

public class EmpleadoController {

    @FXML public TextField cmpId;
    @FXML public TextField cmpNombre;
    @FXML public TextField cmpApellidos;
    @FXML public TextField cmpEmail;
    @FXML public ComboBox cmpDepto;
    @FXML public TextField cmpSalario;

    private final EmpleadoDAO dao = new EmpleadoDAO();
    private Empleado actual;

    @FXML
    public void buscar(ActionEvent actionEvent) {
        int id = Integer.parseInt(cmpId.getText());
        Optional<Empleado> opt = dao.obtener(id);
        opt.ifPresentOrElse(
            emp -> cargarDatos(emp),
            () -> System.out.println("No existe el registro!!!!")  );

    }

    private void cargarDatos(Empleado emp) {
        cmpId.setText(String.valueOf( emp.getId() ));
        cmpNombre.setText( emp.getNombre() );
        cmpApellidos.setText( emp.getApellidos() );
        cmpEmail.setText( emp.getEmail() );
        cmpSalario.setText( String.valueOf( emp.getSalario() )  );
        cmpDepto.getSelectionModel().select( emp.getDepartamento() - 1 );
        actual = emp;
    }

}