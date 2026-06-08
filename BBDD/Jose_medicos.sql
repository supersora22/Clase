CREATE DATABASE IF NOT EXISTS Medicos;
USE Medicos;
CREATE TABLE IF NOT EXISTS Medico(
    id_medico INT auto_increment,
    datos_personales VARCHAR(100) NOT NULL,
    anio_colegiado YEAR,
    PRIMARY KEY(id_medico) 
);

CREATE TABLE IF NOT EXISTS Paciente(
    id_paciente INT auto_increment,
    datos_personales VARCHAR(100),
    id_medico INT,
    PRIMARY KEY(id_paciente),
    FOREIGN KEY(id_medico) REFERENCES Medico(id_medico)
);

CREATE TABLE IF NOT EXISTS Sala(
    id_sala INT,
    ubicacion VARCHAR(50),
    PRIMARY KEY(id_sala)
);

CREATE TABLE IF NOT EXISTS Pasa_Consulta(
    id_medico INT,
    id_paciente INT,
    id_sala INT,
    PRIMARY KEY(id_medico, id_sala),
    FOREIGN KEY(id_medico) REFERENCES Medico(id_medico),
    FOREIGN KEY(id_sala) REFERENCES Sala(id_sala)
);

INSERT INTO Medico (id_medico, datos_personales, anio_colegiado) VALUES
(1, 'Dr. Alberto Ruiz, Cardiología - NIF 12345678A', 2010),
(2, 'Dra. Marta Gómez, Pediatría - NIF 23456789B', 2015),
(3, 'Dr. Ricardo Fernández, Medicina General - NIF 34567890C', 2005),
(4, 'Dra. Elena Santos, Neurología - NIF 45678901D', 2018),
(5, 'Dr. Miguel Álvarez, Traumatología - NIF 56789012E', 2000);

INSERT INTO Paciente (id_paciente, datos_personales, id_medico) VALUES
(1, 'Lucía Pérez, DNI 98765432L, nac. 1990-04-12', 2),
(2, 'Carlos Moreno, DNI 87654321M, nac. 1985-09-30', 1),
(3, 'Ana Torres, DNI 76543210N, nac. 2002-11-05', 3),
(4, 'Joaquín Ruiz, DNI 65432109P, nac. 1978-01-22', 5),
(5, 'Marina Gil, DNI 54321098Q, nac. 1995-06-17', 4);

INSERT INTO Sala (id_sala, ubicacion) VALUES
(1, 'Planta 1 - Consultorio A'),
(2, 'Planta 1 - Consultorio B'),
(3, 'Planta 2 - Consultorio C'),
(4, 'Planta Baja - Urgencias'),
(5, 'Planta 3 - Consultorio D');

INSERT INTO Pasa_Consulta (id_medico, id_paciente, id_sala) VALUES
(1, 2, 1),
(2, 1, 2),
(3, 3, 3),
(4, 5, 4),
(5, 4, 5);
