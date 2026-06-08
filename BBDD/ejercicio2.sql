ALTER TABLE empleado 
ADD fecha_alta DATE NOT NULL DEFAULT (CURRENT_DATE);
ALTER TABLE supermercado 
MODIFY domicilio VARCHAR(100);
ALTER TABLE supermercado 
DROP COLUMN superficie;
ALTER TABLE pedido 
ADD CONSTRAINT CHK_TotalPositivo CHECK (total >= 0);
UPDATE empleado 
SET fecha_alta = CURRENT_DATE;
DELETE FROM empleado WHERE nombre = 'Laura Gómez'; --falla porque no puede ser un valor nulo.