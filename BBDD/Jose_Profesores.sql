CREATE DATABASE IF NOT EXISTS Profesores;
USE Profesores;
CREATE TABLE IF NOT EXISTS Profesor(
    DNI INT auto_increment,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(100),
    telefono VARCHAR(9),
    PRIMARY KEY(DNI)
);

CREATE TABLE IF NOT EXISTS Modulo(
    codigo INT auto_increment,
    nombre VARCHAR(50),
    DNI INT,
    PRIMARY KEY(codigo),
    FOREIGN KEY(DNI) REFERENCES Profesor(DNI)
);

CREATE TABLE IF NOT EXISTS Alumno(
    num_expediente INT auto_increment,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE,
    PRIMARY KEY(num_expediente)
);

CREATE TABLE IF NOT EXISTS Grupo (
    id_grupo INT auto_increment,
    nombre VARCHAR(50) NOT NULL,
    nivel VARCHAR(20),
    expediente_delegado INT,
    PRIMARY KEY(id_grupo),
    FOREIGN KEY(expediente_delegado) REFERENCES Alumno(num_expediente)
);

INSERT INTO Profesor (DNI, nombre, direccion, telefono) VALUES
(43123456, 'María López', 'C/ Alcalá 23, Madrid', '612345789'),
(27654321, 'José García', 'Av. Diagonal 10, Barcelona', '698765432'),
(50234567, 'Lucía Martínez', 'C/ Mayor 5, Sevilla', '611223344'),
(37890123, 'Carlos Sánchez', 'Plaza Mayor 2, Valladolid', '622334455'),
(44987612, 'Ana Fernández', 'C/ Real 18, Valencia', '633445566');

INSERT INTO Modulo (nombre, DNI) VALUES
('Bases de Datos', 43123456),
('Programación', 27654321),
('Sistemas Operativos', 50234567),
('Matemáticas Aplicadas', 37890123),
('Redes y Comunicaciones', 44987612);

INSERT INTO Alumno (num_expediente, nombre, apellidos, fecha_nacimiento) VALUES
(1001, 'Marcos', 'Ruiz Fernández', '2003-04-12'),
(1002, 'Elena', 'Gómez Castillo', '2002-11-30'),
(1003, 'Pablo', 'Herrera López', '2004-01-20'),
(1004, 'Sara', 'Vega Morales', '2003-07-05'),
(1005, 'David', 'Núñez Pérez', '2002-03-17');

INSERT INTO Grupo (nombre, nivel, expediente_delegado) VALUES
('1A', 'FP Grado Superior', 1001),
('1B', 'FP Grado Medio', 1002),
('2A', 'FP Grado Superior', 1003),
('2B', 'FP Grado Medio', 1004),
('3A', 'FP Básico', 1005);