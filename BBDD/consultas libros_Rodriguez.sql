-- Eliminar la base de datos si existe
DROP DATABASE IF EXISTS biblioteca_bd;

-- Crear base de datos
CREATE DATABASE biblioteca_bd;
USE biblioteca_bd;

-- CREACIÓN DE TABLAS

-- Tabla Autor
-- Almacena información de los autores
CREATE TABLE Autor (
    id_autor INT AUTO_INCREMENT,
    nombre_autor VARCHAR(100) NOT NULL,
    pais_autor VARCHAR(50),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_autor)
);

-- Tabla Editorial
-- Almacena las editoriales
CREATE TABLE Editorial (
    id_editorial INT AUTO_INCREMENT,
    nombre_editorial VARCHAR(100) NOT NULL UNIQUE,
    PRIMARY KEY (id_editorial)
);

-- Tabla Categoria
-- Categorías temáticas de los libros
CREATE TABLE Categoria (
    id_categoria INT AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    descripcion TEXT,
    PRIMARY KEY (id_categoria)
);

-- Tabla Libros
-- Catálogo de libros
CREATE TABLE Libros (
    isbn VARCHAR(20),
    titulo VARCHAR(200) NOT NULL,
    fecha_publicacion DATE,
    PRIMARY KEY (isbn)
);

-- Tabla Ejemplares
-- Copias físicas de cada libro
CREATE TABLE Ejemplares (
    id_ejemplar INT AUTO_INCREMENT,
    isbn VARCHAR(20) NOT NULL,
    copia_ejemplar VARCHAR(50) NOT NULL,
    estado_ejemplar VARCHAR(20) DEFAULT 'disponible',
    PRIMARY KEY (id_ejemplar)
);

-- Tabla Usuarios
-- Usuarios registrados
CREATE TABLE Usuarios (
    id_usuario INT AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_usuario)
);

-- Tabla Prestamo
-- Registro de préstamos
CREATE TABLE Prestamo (
    id_prestamo INT AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    id_ejemplar INT NOT NULL,
    fecha_prestamo DATE NOT NULL,
    fecha_devolucion DATE,
    Num_Renovaciones INT DEFAULT 0,
    PRIMARY KEY (id_prestamo)
);

-- TABLAS DE RELACIÓN (N:M)

-- Relación: es_escrito (Autor - Libros)
CREATE TABLE es_escrito (
    id_autor INT,
    isbn VARCHAR(20),
    PRIMARY KEY (id_autor, isbn)
);

-- Relación: es_editado_por (Editorial - Libros)
CREATE TABLE es_editado_por (
    id_editorial INT,
    isbn VARCHAR(20),
    PRIMARY KEY (id_editorial, isbn)
);

-- Relación: pertenece (Libros - Categoria)
CREATE TABLE pertenece (
    isbn VARCHAR(20),
    id_categoria INT,
    PRIMARY KEY (isbn, id_categoria)
);

-- Relación: amigos (Usuarios - Usuarios)
CREATE TABLE amigos (
    id_usuario1 INT,
    id_usuario2 INT,
    PRIMARY KEY (id_usuario1, id_usuario2)
);

-- CLAVES FORÁNEAS (FOREIGN KEYS)

-- Claves foráneas de es_escrito
ALTER TABLE es_escrito
    ADD FOREIGN KEY (id_autor) REFERENCES Autor(id_autor) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE es_escrito
    ADD FOREIGN KEY (isbn) REFERENCES Libros(isbn) ON DELETE CASCADE ON UPDATE CASCADE;

-- Claves foráneas de es_editado_por
ALTER TABLE es_editado_por
    ADD FOREIGN KEY (id_editorial) REFERENCES Editorial(id_editorial) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE es_editado_por
    ADD FOREIGN KEY (isbn) REFERENCES Libros(isbn) ON DELETE CASCADE ON UPDATE CASCADE;

-- Claves foráneas de pertenece
ALTER TABLE pertenece
    ADD FOREIGN KEY (isbn) REFERENCES Libros(isbn) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE pertenece
    ADD FOREIGN KEY (id_categoria) REFERENCES Categoria(id_categoria) ON DELETE CASCADE ON UPDATE CASCADE;

-- Claves foráneas de Ejemplares
ALTER TABLE Ejemplares
    ADD FOREIGN KEY (isbn) REFERENCES Libros(isbn) ON DELETE CASCADE ON UPDATE CASCADE;

-- Claves foráneas de Prestamo
ALTER TABLE Prestamo
    ADD FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE Prestamo
    ADD FOREIGN KEY (id_ejemplar) REFERENCES Ejemplares(id_ejemplar) ON DELETE CASCADE ON UPDATE CASCADE;

-- Claves foráneas de amigos
ALTER TABLE amigos
    ADD FOREIGN KEY (id_usuario1) REFERENCES Usuarios(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE amigos
    ADD FOREIGN KEY (id_usuario2) REFERENCES Usuarios(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE;

-- INSERCIÓN DE DATOS

-- Insertar autores
INSERT INTO Autor (nombre_autor, pais_autor) VALUES
('Gabriel García Márquez', 'Colombia'),
('Isabel Allende', 'Chile'),
('Jorge Luis Borges', 'Argentina'),
('Julio Cortázar', 'Argentina'),
('Mario Vargas Llosa', 'Perú');

-- Insertar editoriales
INSERT INTO Editorial (nombre_editorial) VALUES
('Editorial Planeta'),
('Random House'),
('Alfaguara'),
('Anagrama');

-- Insertar categorías
INSERT INTO Categoria (nombre, descripcion) VALUES
('Ficción', 'Obras literarias de ficción'),
('No Ficción', 'Obras basadas en hechos reales'),
('Ciencia Ficción', 'Literatura de ciencia ficción'),
('Poesía', 'Obras poéticas'),
('Ensayo', 'Obras de ensayo');

-- Insertar libros
INSERT INTO Libros (isbn, titulo, fecha_publicacion) VALUES
('978-0307474728', 'Cien años de soledad', '1967-05-30'),
('978-0525433446', 'La casa de los espíritus', '1982-01-01'),
('978-0142437223', 'Ficciones', '1944-01-01'),
('978-0307475473', 'Rayuela', '1963-06-28'),
('978-8420471839', 'La ciudad y los perros', '1963-01-01');

-- Insertar ejemplares
INSERT INTO Ejemplares (isbn, copia_ejemplar, estado_ejemplar) VALUES
('978-0307474728', 'EJ001', 'disponible'),
('978-0307474728', 'EJ002', 'disponible'),
('978-0307474728', 'EJ003', 'prestado'),
('978-0525433446', 'EJ004', 'disponible'),
('978-0142437223', 'EJ005', 'disponible'),
('978-0307475473', 'EJ006', 'disponible'),
('978-8420471839', 'EJ007', 'mantenimiento');

-- Insertar usuarios
INSERT INTO Usuarios (nombre, email) VALUES
('Juan Pérez', 'juan.perez@email.com'),
('María González', 'maria.gonzalez@email.com'),
('Carlos Rodríguez', 'carlos.rodriguez@email.com'),
('Ana Martínez', 'ana.martinez@email.com'),
('Luis Fernández', 'luis.fernandez@email.com');

-- Insertar relaciones autor-libro
INSERT INTO es_escrito (id_autor, isbn) VALUES
(1, '978-0307474728'),
(2, '978-0525433446'),
(3, '978-0142437223'),
(4, '978-0307475473'),
(5, '978-8420471839');

-- Insertar relaciones editorial-libro
INSERT INTO es_editado_por (id_editorial, isbn) VALUES
(1, '978-0307474728'),
(2, '978-0525433446'),
(3, '978-0142437223'),
(1, '978-0307475473'),
(3, '978-8420471839');

-- Insertar relaciones libro-categoría
INSERT INTO pertenece (isbn, id_categoria) VALUES
('978-0307474728', 1),
('978-0525433446', 1),
('978-0142437223', 1),
('978-0307475473', 1),
('978-8420471839', 1);

-- Insertar préstamos
INSERT INTO Prestamo (id_usuario, id_ejemplar, fecha_prestamo, fecha_devolucion, Num_Renovaciones) VALUES
(1, 3, '2024-12-01', NULL, 0),
(2, 4, '2024-11-20', '2024-12-05', 1),
(3, 5, '2024-12-05', NULL, 0);

-- Insertar relaciones de amistad
INSERT INTO amigos (id_usuario1, id_usuario2) VALUES
(1, 2),
(1, 3),
(2, 4);

-- OPERACIONES DE ACTUALIZACIÓN Y BORRADO

-- Actualizar estado de un ejemplar
UPDATE Ejemplares 
SET estado_ejemplar = 'disponible' 
WHERE id_ejemplar = 7;

-- Actualizar un préstamo al devolver un libro
UPDATE Prestamo 
SET fecha_devolucion = '2024-12-10' 
WHERE id_prestamo = 1;

-- Borrar una relación de amistad
DELETE FROM amigos 
WHERE id_usuario1 = 2 AND id_usuario2 = 4;

-- Insertar nueva categoría
INSERT INTO Categoria (nombre, descripcion) VALUES
('Terror', 'Literatura de terror y suspense');

-- CONSULTAS

-- consulta 1 Listado completo de libros con sus autores y editoriales
SELECT L.titulo, A.nombre_autor, E.nombre_editorial
FROM Libros L
INNER JOIN es_escrito EE ON L.isbn = EE.isbn
INNER JOIN Autor A ON EE.id_autor = A.id_autor
INNER JOIN es_editado_por EEP ON L.isbn = EEP.isbn
INNER JOIN Editorial E ON EEP.id_editorial = E.id_editorial;

-- consulta 2 Usuarios y sus préstamos actuales
SELECT U.nombre, P.id_ejemplar, P.fecha_prestamo
FROM Usuarios U
LEFT JOIN Prestamo P ON U.id_usuario = P.id_usuario
WHERE P.fecha_devolucion IS NULL OR P.id_prestamo IS NULL;

-- consulta 3 Libros que nunca han sido prestados
SELECT titulo 
FROM Libros 
WHERE isbn NOT IN (
    SELECT E.isbn 
    FROM Ejemplares E
    INNER JOIN Prestamo P ON E.id_ejemplar = P.id_ejemplar
);

-- consulta 4 Nombres de Autores y Usuarios en una sola lista asce
SELECT nombre_autor AS nombre, 'Autor' AS rol
FROM Autor
UNION
SELECT nombre, 'Usuario' AS rol
FROM Usuarios
ORDER BY nombre ASC;

-- consulta 5 Categorías que tienen más de un libro registrado
SELECT C.nombre, COUNT(P.isbn) AS total_libros
FROM Categoria C
INNER JOIN pertenece P ON C.id_categoria = P.id_categoria
GROUP BY C.nombre
HAVING COUNT(P.isbn) > 1;

-- consulta 6 Títulos de libros y sus autores
SELECT L.titulo, 
       (SELECT A.nombre_autor 
        FROM Autor A 
        INNER JOIN es_escrito EE ON A.id_autor = EE.id_autor 
        WHERE EE.isbn = L.isbn LIMIT 1) AS Autor
FROM Libros L;

-- consulta 7 Insertar un nuevo libro.
INSERT INTO Libros (isbn, titulo, fecha_publicacion) 
VALUES ('978-8437604947', 'Bodas de sangre', '1933-03-08');

INSERT INTO Ejemplares (isbn, copia_ejemplar, estado_ejemplar) 
VALUES ('978-8437604947', 'EJ008', 'disponible');


-- consulta 8 Eliminar un ejemplar específico que ya no sirve
DELETE FROM Ejemplares 
WHERE copia_ejemplar = 'EJ007';

-- consulta 9 Crear vista ejemplares.
CREATE VIEW vista_ejemplares_disponibles AS
SELECT isbn, copia_ejemplar 
FROM Ejemplares
WHERE estado_ejemplar = 'disponible';

-- consulta 10 Usar una vista con sus libros.
SELECT 
L.titulo AS Titulo_Libro, 
V.copia_ejemplar AS Codigo_Copia
FROM vista_ejemplares_disponibles V
INNER JOIN Libros L ON V.isbn = L.isbn;

-- consulta 11 Lista de todos los contactos
SELECT email AS contacto, 'Usuario' AS tipo
FROM Usuarios
UNION
SELECT nombre_editorial AS contacto, 'Editorial' AS tipo
FROM Editorial
ORDER BY tipo DESC;


