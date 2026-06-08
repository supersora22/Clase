-- 01. El nombre y el genero de todos los videojuegos "Multiplataforma"
SELECT nombre, genero 
FROM Videojuegos 
WHERE plataforma = 'Multiplataforma';

-- 02. Cantidad de videojuegos de "Acción"
SELECT COUNT(*) 
FROM Videojuegos 
WHERE genero = 'Acción';

-- 03. Nombre de las distintas plataformas que existen para los videojuegos(sin repetir).
SELECT DISTINCT plataforma 
FROM Videojuegos;

-- 04. Nombre y fecha_lanzamiento de los videojuegos que salieron a la venta entre el 5 de mayo de 2017 y el 5 de mayo de 2019
SELECT nombre, fecha_lanzamiento 
FROM Videojuegos 
WHERE fecha_lanzamiento BETWEEN '2017-05-05' AND '2019-05-05';

-- 05. Nombre y email de los jugadores cuyo apellido acabe con la letra "a"
SELECT nombre, email 
FROM Jugadores 
WHERE nombre LIKE '%a';

-- 06. Duración (en minutos) de la partida más larga y nombre del videojuego en el que se jugó la partida
SELECT Partidas.duracion_minutos, Videojuegos.nombre 
FROM Partidas 
JOIN Videojuegos ON Partidas.videojuego_id = Videojuegos.id 
ORDER BY Partidas.duracion_minutos DESC 
LIMIT 1;

-- 07. Duración promedio de las partidas jugadas
SELECT (SUM(duracion_minutos) / COUNT(duracion_minutos)) AS duracion_promedio 
FROM Partidas;

-- 08. Nombre del jugador, videojuego jugado y puntuación del jugador con menor puntuación registrado
SELECT Jugadores.nombre AS jugador, Videojuegos.nombre AS videojuego, Jugadores_Partidas.puntuacion 
FROM Jugadores_Partidas 
JOIN Jugadores ON Jugadores_Partidas.jugador_id = Jugadores.id 
JOIN Partidas ON Jugadores_Partidas.partida_id = Partidas.id 
JOIN Videojuegos ON Partidas.videojuego_id = Videojuegos.id 
ORDER BY Jugadores_Partidas.puntuacion ASC 
LIMIT 1;

-- 09. Puntuación total obtenida en todos los videojuegos de "Playstation 4"
SELECT SUM(Jugadores_Partidas.puntuacion) AS puntuacion_total 
FROM Jugadores_Partidas 
JOIN Partidas ON Jugadores_Partidas.partida_id = Partidas.id 
JOIN Videojuegos ON Partidas.videojuego_id = Videojuegos.id 
WHERE Videojuegos.plataforma = 'PlayStation 4';

-- 10. Nombre de plataforma y número de partidas totales jugadas en cada una de ellas 
SELECT Videojuegos.plataforma, COUNT(Partidas.id) AS total_partidas 
FROM Videojuegos 
JOIN Partidas ON Videojuegos.id = Partidas.videojuego_id 
GROUP BY Videojuegos.plataforma;