CREATE DATABASE IF NOT EXISTS Bicicletas;
CREATE TABLE IF NOT EXISTS Inventario (
    id_bici INT PRIMARY KEY auto_increment,
    marca VARCHAR(50),
    modelo VARCHAR(50) NOT NULL,
    color VARCHAR(30) NOT NULL,
    precio DECIMAL(10, 2) CHECK (precio > 0),
    stock INT DEFAULT 5,
    fecha_entrada DATE DEFAULT CURRENT_DATE
 )

ALTER TABLE Inventario
ADD COLUMN descripcion VARCHAR(255);

ALTER TABLE Inventario
MODIFY COLUMN precio DECIMAL(6,2);

ALTER TABLE Inventario
MODIFY COLUMN color VARCHAR(30);

ALTER TABLE Inventario
ADD COLUMN promocionada BOOLEAN DEFAULT FALSE;

INSERT INTO Inventario (marca, modelo, color, precio, stock, fecha_entrada, descripcion, promocionada)
VALUES ('Trek', 'Marlin 7', 'Negro', 899.99, 8, '2025-11-01', 'Todo-terreno, cuadro de aluminio, suspension delantera', FALSE);

INSERT INTO Inventario (marca, modelo, color, precio, stock, fecha_entrada, descripcion, promocionada)
VALUES ('Giant', 'Escape 3', 'Blanco', 499.50, 15, '2025-10-15', 'Urbana/hibrida, ligera, ideal para desplazamientos diarios', TRUE);

INSERT INTO Inventario (marca, modelo, color, precio, stock, fecha_entrada, descripcion, promocionada)
VALUES ('Specialized', 'Sirrus X 4.0', 'Rojo', 1099.00, 4, '2025-09-20', 'Gravel/fitness, transmision 2x y frenos de disco', FALSE);

INSERT INTO Inventario (marca, modelo, color, precio, stock, fecha_entrada, descripcion, promocionada)
VALUES ('Cannondale', 'Quick 4', 'Azul', 799.00, 6, '2025-11-10', 'Commuter rapido y comodo, compatible con guardabarros', TRUE);

INSERT INTO Inventario (marca, modelo, color, precio, stock, fecha_entrada, descripcion, promocionada)
VALUES ('Scott', 'Sub Cross 40', 'Verde', 649.75, 10, '2025-08-05', 'Mixta, suspension delantera ligera, apta para caminos', FALSE);