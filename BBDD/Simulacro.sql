CREATE DATABASE IF NOT EXISTS VueltaCiclista;
USE VueltaCiclista;
CREATE TABLE IF NOT EXISTS Equipo (
    Nombre_equipo VARCHAR(100) PRIMARY KEY,
    Nacionalidad VARCHAR(50),
    Patrocinador VARCHAR(100),
    Director VARCHAR(100)
);
CREATE TABLE IF NOT EXISTS Ciclista (
    Dorsal INTEGER PRIMARY KEY,
    Nombre_equipo VARCHAR(100),
    Nacionalidad VARCHAR(50),
    FOREIGN KEY (Nombre_equipo) REFERENCES Equipo(Nombre_equipo)
);
CREATE TABLE IF NOT EXISTS Clasificacion (
    Dorsal INTEGER,
    Clasificacion VARCHAR(255),
    Lugar VARCHAR(100),
    PRIMARY KEY (Dorsal, Clasificacion),
    FOREIGN KEY (Dorsal) REFERENCES Ciclista(Dorsal)
);
CREATE TABLE IF NOT EXISTS Etapa (
    Num_etapa INTEGER PRIMARY KEY,
    Fecha DATE,
    Lugar_salida VARCHAR(100),
    Lugar_llegada VARCHAR(100),
    Kilometros NUMERIC(5, 2),
    Hora_salida TIME,
    Hora_llegada TIME,
    Tipo VARCHAR(50)
);
CREATE TABLE IF NOT EXISTS Puerto (
    Nombre_puerto VARCHAR(100) PRIMARY KEY,
    Categoria VARCHAR(20)
);
CREATE TABLE IF NOT EXISTS Entrevista (
    Id_entrevista INT PRIMARY KEY AUTO_INCREMENT,
    Dorsal INTEGER,
    Num_etapa INTEGER,
    Cadena VARCHAR(100),
    Canon NUMERIC(8, 2),
    FOREIGN KEY (Dorsal) REFERENCES Ciclista(Dorsal),
    FOREIGN KEY (Num_etapa) REFERENCES Etapa(Num_etapa)
);