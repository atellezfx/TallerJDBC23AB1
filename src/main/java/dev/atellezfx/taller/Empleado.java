package dev.atellezfx.taller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Empleado {

    private int id;
    private String nombre;
    private String apellidos;
    private String email;
    private float salario;
    private int departamento;

}
