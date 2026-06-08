CREATE TABLE IF NOT EXISTS informe_salarial(
    CodDep CHAR(5) PRIMARY KEY,
    NomDep VARCHAR(40),
    NumEmpleados INT,
    SalarioMedio DECIMAL(10,2),
    Categoria VARCHAR(10)
);
Insert INTO informe_salarial
