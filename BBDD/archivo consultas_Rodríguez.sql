USE ayuntamiento;

# 01. El nombre y el email de todos los usuarios del departamento de "Recursos humanos".
SELECT nombre, email 
FROM usuario 
WHERE departamento = 'Recursos humanos';

# 02. Lista de todos los tickets que no tienen asignado un técnico y su prioridad es "Alta".
SELECT * FROM ticket 
WHERE email_tecnico IS NULL AND prioridad = 'Alta';

# 03. Lista de tickets que incluyen en su descripción la palabra "servi".
SELECT * FROM ticket 
WHERE descripcion LIKE '%servi%';

# 04. Número total de tickets resueltos.
SELECT COUNT(*) AS total_resueltos 
FROM ticket 
WHERE estado = 'Resuelto';

# 05. Numero de usuarios que tiene cada departamento.
SELECT departamento, COUNT(*) AS num_usuarios 
FROM usuario 
GROUP BY departamento;

# 06. El nombre y el email de todos los técnicos y administradores cuyo apellido acabe en "ez", ordenados.
(SELECT nombre, email FROM tecnico WHERE nombre LIKE '%ez')
UNION
(SELECT nombre, email FROM administrador WHERE nombre LIKE '%ez')
ORDER BY nombre ASC;

# 07. Lista de tickets "Pendiente" ordenados por fecha de creación descendente.
SELECT * FROM ticket 
WHERE estado = 'Pendiente' 
ORDER BY fecha_creacion DESC;

# 08. Número de tickets registrados por cada departamento.
SELECT u.departamento, COUNT(t.idTicket) AS total_tickets
FROM ticket t
JOIN usuario u ON t.email_usuario = u.email
GROUP BY u.departamento;

# 09. Lista de tickets resueltos en los últimos 5 días.
SELECT * FROM ticket 
WHERE estado = 'Resuelto' 
AND fecha_creacion >= DATE_SUB(CURDATE(), INTERVAL 5 DAY);

# 10. Mostrar la cantidad de tickets "En progreso", "Resuelto" y "Pendiente".
SELECT estado, COUNT(*) AS cantidad 
FROM ticket 
GROUP BY estado;

# 11. Lista de tickets "Pendiente" con el nombre del usuario que lo ha creado.
SELECT t.*, u.nombre AS nombre_usuario
FROM ticket t
JOIN usuario u ON t.email_usuario = u.email
WHERE t.estado = 'Pendiente';

# 12. Lista de tickets "En progreso" con el nombre del usuario y el técnico asignado.
# (t = ticket, u = usuario, te = tecnico)
SELECT t.*, u.nombre AS nombre_usuario, te.nombre AS nombre_tecnico
FROM ticket t
JOIN usuario u ON t.email_usuario = u.email
JOIN tecnico te ON t.email_tecnico = te.email
WHERE t.estado = 'En progreso';

# 13. Lista de tickets "Resuelto" de las categorías que empiezan por "S" o por "A".
SELECT * FROM ticket 
WHERE estado = 'Resuelto' 
AND (categoria LIKE 'S%' OR categoria LIKE 'A%');

# 14. Nombre de los 3 técnicos que más tickets han resuelto.
SELECT te.nombre, COUNT(t.idTicket) AS tickets_resueltos
FROM ticket t
JOIN tecnico te ON t.email_tecnico = te.email
WHERE t.estado = 'Resuelto'
GROUP BY te.email, te.nombre
ORDER BY tickets_resueltos DESC
LIMIT 3;

# 15. Nombre e email de los técnicos que no han resuelto ningún ticket.
SELECT nombre, email 
FROM tecnico 
WHERE email NOT IN (
    SELECT DISTINCT email_tecnico 
    FROM ticket 
    WHERE estado = 'Resuelto' AND email_tecnico IS NOT NULL
);

# 16. Día del mes de febrero que más tickets se crearon.
SELECT DAY(fecha_creacion) AS dia_febrero, COUNT(*) AS cantidad
FROM ticket
WHERE MONTH(fecha_creacion) = 2
GROUP BY dia_febrero
ORDER BY cantidad DESC
LIMIT 1;

# 17. Top 5 usuarios que más tickets han creado.
SELECT u.nombre, u.email, COUNT(t.idTicket) AS tickets_creados
FROM usuario u
JOIN ticket t ON u.email = t.email_usuario
GROUP BY u.email, u.nombre
ORDER BY tickets_creados DESC
LIMIT 5;

# 18. Descripción, fecha y estado de tickets del técnico que haya resuelto menos tickets.
SELECT descripcion, fecha_creacion, estado
FROM ticket
WHERE email_tecnico = (
    SELECT email_tecnico
    FROM ticket
    WHERE estado = 'Resuelto' AND email_tecnico IS NOT NULL
    GROUP BY email_tecnico
    ORDER BY COUNT(*) ASC
    LIMIT 1
);

# 19. Muestra el último ticket registrado.
SELECT * FROM ticket 
ORDER BY idTicket DESC 
LIMIT 1;

# 20. Historial de estados del ticket con idTicket 5.
# (h = historial, t = ticket, te = tecnico, u = usuario)
SELECT h.estado, t.descripcion, te.nombre AS tecnico_asignado, u.nombre AS creador, h.fecha
FROM historial_ticket h
JOIN ticket t ON h.idTicket = t.idTicket
LEFT JOIN tecnico te ON t.email_tecnico = te.email
JOIN usuario u ON t.email_usuario = u.email
WHERE h.idTicket = 5;