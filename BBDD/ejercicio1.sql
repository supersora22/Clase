CREATE DATABASE IF NOT EXISTS examen1;
USE examen1;

CREATE TABLE IF NOT EXISTS Personas (
    id_persona INT PRIMARY KEY,
    nombre_completo VARCHAR(200) NOT NULL,
    pais VARCHAR(50)
);
CREATE TABLE IF NOT EXISTS Equipos (
    id_equipo INT PRIMARY KEY,
    nombre_equipo VARCHAR(100)
);
CREATE TABLE IF NOT EXISTS Jefe_Equipo (
    id_jefe INT PRIMARY KEY,
    id_equipo INT UNIQUE,
    CONSTRAINT fk_jefe_persona FOREIGN KEY (id_jefe) REFERENCES Personas(id_persona),
    CONSTRAINT fk_jefe_equipo FOREIGN KEY (id_equipo) REFERENCES Equipos(id_equipo)
);
CREATE TABLE IF NOT EXISTS Pilotos (
    id_piloto INT PRIMARY KEY,
    dorsal INT UNIQUE NOT NULL,
    apodo VARCHAR(50),
    id_equipo INT,
    CONSTRAINT fk_piloto_persona FOREIGN KEY (id_piloto) REFERENCES Personas(id_persona),
    CONSTRAINT fk_piloto_equipo FOREIGN KEY (id_equipo) REFERENCES Equipos(id_equipo)
);
CREATE TABLE IF NOT EXISTS Coches (
    id_equipo INT,
    num_serie INT,
    motor VARCHAR(50),
    PRIMARY KEY (id_equipo, num_serie),
    CONSTRAINT fk_coche_equipo FOREIGN KEY (id_equipo) REFERENCES Equipos(id_equipo)
);
CREATE TABLE IF NOT EXISTS Carreras (
    id_carrera INT PRIMARY KEY,
    ciudad VARCHAR(50),
    fecha DATE,
    num_carrera INT,
    id_equipo_coche INT,
    num_serie_coche INT,
    CONSTRAINT fk_carrera_coche FOREIGN KEY (id_equipo_coche, num_serie_coche) 
    REFERENCES Coches(id_equipo, num_serie)
);
CREATE TABLE IF NOT EXISTS Participacion (
    id_carrera INT,
    dorsal_piloto INT,
    hora TIME,
    pos_final INT,
    PRIMARY KEY (id_carrera, dorsal_piloto),
    CONSTRAINT fk_participacion_carrera FOREIGN KEY (id_carrera) REFERENCES Carreras(id_carrera),
    CONSTRAINT fk_participacion_dorsal FOREIGN KEY (dorsal_piloto) REFERENCES Pilotos(dorsal)
);