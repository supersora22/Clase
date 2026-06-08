SQLINSERT INTO alumnado (id, nombre, apellidos, curso) VALUES
(1, 'Juan', 'Pérez', 'Programación'),
(2, 'María', 'Gómez', 'IPE'),
(3, 'Carlos', 'López', 'BaseDeDatos'),
(4, 'Ana', 'Martínez', 'Sistemas Informaticos'),
(5, 'Luis', 'Sánchez', 'Lenguajes DeMarcas');
2. Definición de 2 Disparadores (Triggers)Disparadores automáticos basados en las estructuras de tus archivos:CrearEmailPreInsert: Actúa antes de una inserción en alumnado para generar el correo de forma automatizada si este es nulo.  triggerHolaHijo: Actúa después de una inserción en la tabla hijo para actualizar el contador de hijos del empleado asociado.  SQL-- Trigger 1: Automatización de emails en alumnado
DELIMITER $$
CREATE OR REPLACE TRIGGER CrearEmailPreInsert
BEFORE INSERT ON alumnado
FOR EACH ROW
BEGIN
    IF NEW.email IS NULL THEN
        SET NEW.email = crearEmail(NEW.nombre, NEW.apellidos, NEW.curso);
    END IF;
END $$
DELIMITER ;

-- Trigger 2: Actualización de cargas familiares en empleados
DELIMITER $$
CREATE OR REPLACE TRIGGER triggerHolaHijo
AFTER INSERT ON hijo
FOR EACH ROW
BEGIN
    UPDATE empleado
    SET NumHi = NumHi + 1
    WHERE CodEmp = NEW.CodEmp;
END $$
DELIMITER ;
3. Definición de 2 Procedimientos con Transacciones y Gestión de Errores (Sin SIGNAL)Estos procedimientos ejecutan múltiples operaciones bajo un bloque transaccional seguro. Si ocurre una excepción, un manejador (EXIT HANDLER FOR SQLEXCEPTION) revierte los cambios (ROLLBACK) y notifica el problema mediante un mensaje en un SELECT.ac12ModificarSalarioEmpleado: Modifica el salario base de un empleado en la tabla empleado e inserta de forma simultánea el registro histórico en la tabla salarios.  ac12TransferirPresupuesto: Realiza una transferencia de presupuesto entre dos registros de la tabla departamentoFamiliar, reduciendo el saldo de uno e incrementando el de otro.  SQL-- Procedimiento 1: Modificación salarial e inserción histórica estructurada
DELIMITER $$
CREATE OR REPLACE PROCEDURE ac12ModificarSalarioEmpleado(IN p_CodEmp INT, IN p_NuevoSalario DECIMAL(12,2))
BEGIN
    -- Gestión de errores mediante ROLLBACK y mensaje informativo directo
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SELECT 'Error: No se pudo completar la actualización o el registro del salario.' AS MensajeError;
    END;

    START TRANSACTION;
    
    -- Operación 1: Actualizar la entidad principal (empleado)
    UPDATE empleado 
    SET SalEmp = p_NuevoSalario
    WHERE CodEmp = p_CodEmp;
    
    -- Operación 2: Insertar en la tabla de auditoría (salarios)
    INSERT INTO salarios (salario, CodEmp)
    VALUES (p_NuevoSalario, p_CodEmp);
    
    COMMIT;
END $$
DELIMITER ;

-- Procedimiento 2: Transferencia presupuestaria entre departamentos internos
DELIMITER $$
CREATE OR REPLACE PROCEDURE ac12TransferirPresupuesto(IN p_CodDepOrigen CHAR(5), IN p_CodDepDestino CHAR(5), IN p_Monto DECIMAL(12,2))
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SELECT 'Error: Transacción fallida. Verifique los códigos de departamento.' AS MensajeError;
    END;

    START TRANSACTION;
    
    -- Operación 1: Decrementar presupuesto en origen
    UPDATE departamentoFamiliar 
    SET PreAnu = PreAnu - p_Monto
    WHERE CodDep = p_CodDepOrigen;
    
    -- Operación 2: Incrementar presupuesto en destino
    UPDATE departamentoFamiliar 
    SET PreAnu = PreAnu + p_Monto
    WHERE CodDep = p_CodDepDestino;
    
    COMMIT;
END $$
DELIMITER ;
4. Definición de 2 Procedimientos con Uso de CursoresSe implementa la gestión del fin de los datos empleando un CONTINUE HANDLER FOR NOT FOUND para controlar de manera limpia el flujo del bucle iterativo.  ac1111empleadosSinHijos: Filtra y traspasa a los empleados sin hijos a la tabla espejo empleado_copia.  ac1111empleadosNumHijos: Realiza la misma operación de filtrado parametrizando la consulta según un volumen dinámico de hijos.  SQL-- Procedimiento 1: Cursor para empleados sin cargas familiares
DELIMITER $$
CREATE OR REPLACE PROCEDURE ac1111empleadosSinHijos()
BEGIN
    DECLARE empl ROW TYPE OF empleado;
    DECLARE fin INT DEFAULT 0;
    DECLARE cur_sin_hijos CURSOR FOR SELECT codEmp, codDep, exTelEmp, FecInEmp, fecNaEmp, NifEmp, NomEmp, Numhi, salEmp FROM empleado WHERE numHi = 0;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;
    
    OPEN cur_sin_hijos;
    bucle: LOOP
        FETCH cur_sin_hijos INTO empl;
        IF fin = 1 THEN
            LEAVE bucle;
        END IF;
        INSERT INTO empleado_copia(codEmp, codDep, exTelEmp, FecInEmp, fecNaEmp, NifEmp, NomEmp, NumHi, salEmp)
        VALUES (empl.codEmp, empl.codDep, empl.exTelEmp, empl.FecInEmp, empl.fecNaEmp, empl.NifEmp, empl.NomEmp, empl.NumHi, empl.salEmp);
    END LOOP;
    CLOSE cur_sin_hijos;
END $$
DELIMITER ;

-- Procedimiento 2: Cursor parametrizado por volumen de hijos específico
DELIMITER $$
CREATE OR REPLACE PROCEDURE ac1111empleadosNumHijos(IN numHijos INT)
BEGIN
    DECLARE empl ROW TYPE OF empleado;
    DECLARE fin INT DEFAULT 0;
    DECLARE cur_sin_hijos CURSOR FOR SELECT codEmp, codDep, exTelEmp, FecInEmp, fecNaEmp, NifEmp, NomEmp, Numhi, salEmp FROM empleado WHERE numHi = numHijos; [cite: 328]
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;
    
    OPEN cur_sin_hijos;
    bucle: LOOP
        FETCH cur_sin_hijos INTO empl;
        IF fin = 1 THEN
            LEAVE bucle;
        END IF;
        INSERT INTO empleado_copia(codEmp, codDep, exTelEmp, FecInEmp, fecNaEmp, NifEmp, NomEmp, NumHi, salEmp) [cite: 345]
        VALUES (empl.codEmp, empl.codDep, empl.exTelEmp, empl.FecInEmp, empl.fecNaEmp, empl.NifEmp, empl.NomEmp, empl.NumHi, empl.salEmp); [cite: 346]
    END LOOP;
    CLOSE cur_sin_hijos;
END $$
DELIMITER ;
5. Definición de 2 Funciones de ConsultaFunciones deterministas encargadas del procesamiento de cadenas de texto y evaluación analítica de métricas financieras:crearEmail: Toma los datos de un alumno y genera una dirección bajo reglas precisas de longitud y minúsculas.  ac12categoriaDepartamento: Examina el gasto medio de personal e identifica el rango del departamento invocando subtareas asociadas.  SQL-- Función 1: Generador estructurado de correos institucionales
DELIMITER $$
CREATE FUNCTION crearEmail(nombre VARCHAR(50), apellidos VARCHAR(50), curso VARCHAR(50))
RETURNS VARCHAR(100)
BEGIN
    DECLARE email VARCHAR(100);
    SET email = CONCAT(LOWER(LEFT(nombre, 1)), LOWER(LEFT(apellidos, 5)), LENGTH(apellidos), '@', LOWER(curso), '.kursal.es');
    RETURN email;
END $$
DELIMITER ;

-- Función 2: Clasificador analítico de rangos de costes salariales
DELIMITER $$
CREATE OR REPLACE FUNCTION ac12categoriaDepartamento(p_codDep CHAR(5))
RETURNS VARCHAR(10)
BEGIN
    DECLARE media DECIMAL(12,2);
    DECLARE categoria VARCHAR(10);
    CALL ac12mediaSalarial(p_codDep, media);
    
    IF media IS NULL THEN SET categoria = 'bajo';
    ELSEIF media < 2000000 THEN SET categoria = 'bajo';
    ELSEIF media <= 5000000 THEN  SET categoria = 'medio';
    ELSE
        SET categoria = 'alto';
    END IF;
    RETURN categoria;
END $$
DELIMITER ;
6. Tabla Dashboard
CREATE TABLE IF NOT EXISTS informe_salarial(
    CodDep CHAR(5) PRIMARY KEY,
    NomDep VARCHAR(40),
    NumEmpleados INT,
    SalarioMedio DECIMAL(10,2),
    Categoria VARCHAR(10)
);

DELIMITER $$
CREATE OR REPLACE PROCEDURE ac12actualizaInforme(IN p_CodDep CHAR(5))
BEGIN
    DECLARE numEmpleados INT;
    DECLARE salarioMedio DECIMAL(10,2);
    DECLARE categoria VARCHAR(10);
    
    -- Operación estadística directa
    SELECT COUNT(*) INTO numEmpleados
    FROM empleado WHERE CodDep = p_CodDep;
    
    -- Invocación de rutinas complementarias de cálculo
    CALL ac12mediaSalarial(p_CodDep, salarioMedio);
    SET categoria = ac12categoriaDepartamento(p_CodDep);
    
    -- Volcado de resultados procesados en la tabla Dashboard
    UPDATE informe_salarial 
    SET NumEmpleados = numEmpleados,
        SalarioMedio = salarioMedio,
        Categoria = categoria 
    WHERE CodDep = p_CodDep;
END $$
DELIMITER ;

-- 3. Carga inicial del área del informe y ejecución analítica
INSERT INTO informe_salarial (CodDep, NomDep)
VALUES ('PROZS', (SELECT NomDep FROM departamento WHERE CodDep = 'PROZS'));

-- Ejecución del cálculo global estructurado
CALL ac12actualizaInforme('PROZS');

-- Visualización de resultados finales consolidados
SELECT * FROM informe_salarial WHERE CodDep = 'PROZS';