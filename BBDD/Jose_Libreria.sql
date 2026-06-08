CREATE DATABASE Libreria;

CREATE TABLE IF NOT EXISTS Libro (
    ISBN INT,
    nombre VARCHAR(30) NOT NULL,
    categoria VARCHAR(30) NOT NULL,
    autor VARCHAR(40),
   anio_publicacion YEAR,
    PRIMARY KEY (ISBN)
);

CREATE TABLE IF NOT EXISTS Usuario(
    DNI VARCHAR(9),
    telefono VARCHAR(14) NOT NULL,
    nombre VARCHAR(30) NOT NULL,
    PRIMARY KEY(DNI)
);

CREATE TABLE IF NOT EXISTS Prestamo (
    id_prestamo INT AUTO_INCREMENT,
    ISBN INT,
    DNI VARCHAR(9),
    fechaInicio DATE NOT NULL,
    fechaFin DATE NOT NULL,
    PRIMARY KEY (id_prestamo),
    FOREIGN KEY (ISBN) REFERENCES Libro(ISBN),
    FOREIGN KEY (DNI) REFERENCES Usuario(DNI)
);

INSERT INTO Libro (ISBN, nombre, categoria, autor, anio_publicacion) VALUES
(100000001, 'Cien años de soledad', 'Narrativa', 'Gabriel García Márquez', 1967),
(100000002, 'Don Quijote de la Mancha', 'Clásicos', 'Miguel de Cervantes', 1605),
(100000003, 'La sombra del viento', 'Misterio', 'Carlos Ruiz Zafón', 2001),
(100000004, 'El principito', 'Infantil', 'Antoine de Saint-Exupéry', 1943),
(100000005, 'Sapiens: De animales a dioses', 'Divulgación', 'Yuval Noah Harari', 2011);

INSERT INTO Usuario (DNI, telefono, nombre) VALUES
('12345678A', '+34 612345678', 'Luis Martínez'),
('23456789B', '+34 698765432', 'María González'),
('34567890C', '+34 611223344', 'Javier Pérez'),
('45678901D', '+34 622334455', 'Laura Ruiz'),
('56789012E', '+34 633445566', 'Ana Fernández');

INSERT INTO Prestamo (ISBN, DNI, fechaInicio, fechaFin) VALUES
(100000001, '12345678A', '2025-10-01', '2025-10-15'),
(100000002, '23456789B', '2025-09-20', '2025-10-04'),
(100000003, '34567890C', '2025-11-01', '2025-11-15'),
(100000004, '45678901D', '2025-08-10', '2025-08-24'),
(100000005, '56789012E', '2025-07-05', '2025-07-19');