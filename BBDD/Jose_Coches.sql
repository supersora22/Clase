CREATE DATABASE IF NOT EXISTS Coches;
USE Coches;
CREATE TABLE IF NOT EXISTS Cliente(
    cod_cliente INT auto_increment,
    nif VARCHAR(9) UNIQUE NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(100),
    ciudad VARCHAR(50),
    telefono VARCHAR(9),
    PRIMARY KEY(cod_cliente)
);

CREATE TABLE IF NOT EXISTS Coche(
    matricula VARCHAR(10) UNIQUE NOT NULL,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50),
    color VARCHAR(30),
    precio_venta DECIMAL(10, 2) CHECK (precio_venta > 0),
    cod_cliente INT,
    PRIMARY KEY(matricula),
    FOREIGN KEY(cod_cliente) REFERENCES Cliente(cod_cliente)
);

CREATE TABLE IF NOT EXISTS Venta(
    id_venta INT auto_increment,
    matricula VARCHAR(10),
    cod_cliente INT,
    fecha_venta DATE DEFAULT CURRENT_DATE,
    precio_venta DECIMAL(10, 2) CHECK (precio_venta > 0),
    PRIMARY KEY(id_venta),
    FOREIGN KEY(matricula) REFERENCES Coche(matricula),
    FOREIGN KEY(cod_cliente) REFERENCES Cliente(cod_cliente)
);

CREATE TABLE IF NOT EXISTS Revisiones(
    id_revision INT auto_increment
    matricula VARCHAR(10),
    fecha_revision DATE DEFAULT CURRENT_DATE,
    cambio_aceite BOOLEAN,
    cambio_neumaticos BOOLEAN,
    PRIMARY KEY(id_revision),
    FOREIGN KEY(matricula) REFERENCES Coche(matricula)
    );

INSERT INTO Cliente (cod_cliente, nif, nombre, direccion, ciudad, telefono) VALUES
(1, '12345678A', 'Luis Martínez', 'C/ Gran Vía 12, Madrid', 'Madrid', '612345678'),
(2, '87654321B', 'María González', 'Av. de América 45, Madrid', 'Madrid', '698765432'),
(3, '23456789C', 'Javier Pérez', 'C/ Sant Antoni 9, Barcelona', 'Barcelona', '611223344'),
(4, '34567890D', 'Laura Ruiz', 'Paseo de la Castellana 210, Madrid', 'Madrid', '622334455'),
(5, '98765432E', 'Antonio López', 'C/ Colón 3, Valencia', 'Valencia', '633445566');

INSERT INTO Coche (matricula, marca, modelo, color, precio_venta, cod_cliente) VALUES
('1234ABC', 'Seat', 'Leon', 'Rojo', 19500.00, 1),
('5678DEF', 'Volkswagen', 'Golf', 'Blanco', 21500.50, 2),
('9012GHI', 'Toyota', 'Corolla', 'Gris', 18000.00, NULL),
('3456JKL', 'BMW', 'Serie 1', 'Negro', 29999.99, 3),
('7890MNO', 'Renault', 'Clio', 'Azul', 14999.00, 4);

INSERT INTO Venta (id_venta, matricula, cod_cliente, fecha_venta, precio_venta) VALUES
(1, '1234ABC', 1, '2025-10-05', 19500.00),
(2, '5678DEF', 2, '2025-09-12', 21000.00),
(3, '3456JKL', 3, '2025-07-20', 29500.00),
(4, '7890MNO', 4, '2025-11-01', 14999.00),
(5, '9012GHI', NULL, '2025-08-15', 17500.00);

INSERT INTO Revisiones (id_revision, matricula, fecha_revision, cambio_aceite, cambio_neumaticos) VALUES
(1, '1234ABC', '2025-10-15', TRUE, FALSE),
(2, '5678DEF', '2025-09-20', TRUE, TRUE),
(3, '9012GHI', '2025-08-01', FALSE, TRUE),
(4, '3456JKL', '2025-07-05', TRUE, FALSE),
(5, '7890MNO', '2025-11-10', FALSE, FALSE);