CREATE DATABASE IF NOT EXISTS Instituto;
USE Instituto;

CREATE TABLE IF NOT EXISTS Instituto(
id_nacion INT,
nombre VARCHAR(50) NOT NULL,
PRIMARY KEY(id_nacion)
);
 
CREATE TABLE IF NOT EXISTS Profesor(
id_profesor INT,
nombre VARCHAR(50) NOT NULL,
nacionalidad VARCHAR(50),
PRIMARY KEY(id_profesor)
);

CREATE TABLE IF NOT EXISTS Grupo(
id_grupo INT,
nivel VARCHAR(20) NOT NULL,
id_profesor INT,
PRIMARY KEY(id_grupo),
FOREIGN KEY(id_profesor) REFERENCES Profesor(id_profesor)
);

CREATE TABLE IF NOT EXISTS Alumno(
id_alumno INT,
nombre VARCHAR(50) NOT NULL,
id_grupo INT,
PRIMARY KEY(id_alumno),
FOREIGN KEY(id_grupo) REFERENCES Grupo(id_grupo)
);

INSERT INTO Instituto
(id_nacion, nombre) VALUES
(1, 'IES Cervantes'),
(2, 'IES Galileo'),
(3, 'IES Santa María'),
(4, 'IES Ramón y Cajal'),
(5, 'IES San José');

INSERT INTO Profesor (id_profesor, nombre, nacionalidad) VALUES
(1, 'María López', 'España'),
(2, 'Jorge Ramírez', 'Argentina'),
(3, 'Lucía Fernández', 'España'),
(4, 'Hussein Alí', 'Marruecos'),
(5, 'Anna Kowalska', 'Polonia');

INSERT INTO Grupo (id_grupo, nivel, id_profesor) VALUES
(1, '1º ESO', 1),
(2, '2º ESO', 2),
(3, '1º Bachillerato', 3),
(4, '2º Bachillerato', 4),
(5, 'FP Grado Medio', 5);

INSERT INTO Alumno (id_alumno, nombre, id_grupo) VALUES
(1, 'Carlos Martínez', 1),
(2, 'Sara Gómez', 2),
(3, 'Pablo Ruiz', 3),
(4, 'Lucía Navarro', 4),
(5, 'Miguel Torres', 5);
