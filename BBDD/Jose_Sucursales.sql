CREATE DATABASE IF NOT EXISTS Sucursales;
USE Sucursales;

CREATE TABLE IF NOT EXISTS Sucursal(
    id_sucursal INT auto_increment,
    ciudad VARCHAR(50) NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    PRIMARY KEY(id_sucursal)
);

CREATE TABLE IF NOT EXISTS Cliente(
    codigo INT auto_increment,
    nombre VARCHAR(100) NOT NULL,
    dni INT UNIQUE NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    direccion VARCHAR(150),
    PRIMARY KEY(codigo)
);

CREATE TABLE IF NOT EXISTS Cuenta(
    num_cuenta INT NOT NULL,
    saldo DECIMAL(10,2) CHECK (saldo >= 0),
    num_sucursal INT,
    PRIMARY KEY(num_cuenta),
    FOREIGN KEY(num_sucursal) REFERENCES Sucursal(id_sucursal)
);

CREATE TABLE IF NOT EXISTS Transaccion(
    num_transaccion INT auto_increment,
    tipo VARCHAR(20) NOT NULL,
    cantidad DECIMAL(10,2) CHECK (cantidad > 0),
    fecha DATE DEFAULT CURRENT_DATE,
    num_cuenta INT,
    PRIMARY KEY(num_transaccion),
    FOREIGN KEY(num_cuenta) REFERENCES Cuenta(num_cuenta)
);

CREATE TABLE IF NOT EXISTS Cuenta_cliente(
    num_cliente INT,
    num_cuenta INT,
    codigo INT,
    PRIMARY KEY(num_cliente),
    FOREIGN KEY(codigo) REFERENCES Cliente(codigo),
    FOREIGN KEY(num_cuenta) REFERENCES Cuenta(num_cuenta)
);

INSERT INTO Sucursal (id_sucursal, ciudad, activo) VALUES
(1, 'Madrid', TRUE),
(2, 'Barcelona', TRUE),
(3, 'Valencia', TRUE),
(4, 'Sevilla', FALSE),
(5, 'Bilbao', TRUE);

INSERT INTO Cliente (codigo, nombre, dni, apellidos, direccion) VALUES
(1, 'Luis', 12345678, 'Martínez Pérez', 'C/ Gran Vía 12, Madrid'),
(2, 'María', 87654321, 'García López', 'Av. Diagonal 45, Barcelona'),
(3, 'Javier', 23456789, 'Ruiz Sánchez', 'C/ Colón 3, Valencia'),
(4, 'Laura', 34567890, 'Gómez Fernández', 'Paseo de la Castellana 210, Madrid'),
(5, 'Sandra', 98765432, 'Ortiz Morales', 'C/ Larga 7, Bilbao');

INSERT INTO Cuenta (num_cuenta, saldo, num_sucursal) VALUES
(1001001, 2500.75, 1),
(1001002, 500.00, 2),
(1001003, 12000.00, 3),
(1001004, 0.00, 4),
(1001005, 340.40, 5);

INSERT INTO Transaccion (num_transaccion, tipo, cantidad, fecha, num_cuenta) VALUES
(1, 'Depósito', 1500.00, '2025-10-01', 1001001),
(2, 'Retiro', 200.00, '2025-10-15', 1001002),
(3, 'Transferencia', 3000.00, '2025-09-20', 1001003),
(4, 'Pago', 50.25, '2025-11-05', 1001005),
(5, 'Depósito', 500.00, '2025-08-30', 1001004);

INSERT INTO Cuenta_cliente (num_cliente, num_cuenta, codigo) VALUES
(1, 1001001, 1),
(2, 1001002, 2),
(3, 1001003, 3),
(4, 1001004, 4),
(5, 1001005, 5);