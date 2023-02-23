CREATE DATABASE `empleados`;
USE `empleados`;

CREATE TABLE `departamento`(
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `nombre` varchar(50) NOT NULL,
    PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `empleado` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `nombre` varchar(50) NOT NULL,
    `apellidos` varchar(50) NOT NULL,
    `email` varchar(255) NOT NULL,
    `salario` float NOT NULL,
    `departamento` int(11) NOT NULL DEFAULT 1,
    `creado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `modificado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `idx_empleado_email` (`email`),
    CONSTRAINT `fk_empleado_depto` FOREIGN KEY (`departamento`)
      REFERENCES `departamento`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

delimiter //
CREATE TRIGGER `empleado_actualizado` BEFORE UPDATE ON `empleado`
    for each row begin
    set NEW.modificado=current_timestamp;
END; //
delimiter ;

CREATE VIEW empleado_depto_view AS
    SELECT e.id, e.nombre,e.apellidos, e.email, e.salario, d.nombre AS departamento
    FROM empleado e INNER JOIN departamento d
        ON e.departamento = d.id;

INSERT INTO departamento(id, nombre) VALUES (1,'ADMINISTRACIÓN');
INSERT INTO departamento(id, nombre) VALUES (2,'SISTEMAS');
INSERT INTO departamento(id, nombre) VALUES (3,'MARKETING');
INSERT INTO departamento(id, nombre) VALUES (4,'CONTABILIDAD');

INSERT INTO empleado(nombre, apellidos, email, salario, departamento)
VALUES ('Tony', 'Stark', 't.stark@test.com', 35000.00, 2);
INSERT INTO empleado(nombre, apellidos, email, salario, departamento)
VALUES ('Bruce', 'Wayne', 'b.wayne@test.com', 23500.00, 1);
INSERT INTO empleado(nombre, apellidos, email, salario, departamento)
VALUES ('Clark', 'Kent', 'c.kent@test.com', 42500.00, 3);
INSERT INTO empleado(nombre, apellidos, email, salario, departamento)
VALUES ('Bruce', 'Banner', 'b.banner@test.com', 27600.00, 3);
INSERT INTO empleado(nombre, apellidos, email, salario, departamento)
VALUES ('Steve', 'Rogers', 's.rogers@test.com', 15000.00, 2);
INSERT INTO empleado(nombre, apellidos, email, salario, departamento)
VALUES ('Benito', 'Bodoque', 'b.bodoque@test.com', 75000.00, 1);

