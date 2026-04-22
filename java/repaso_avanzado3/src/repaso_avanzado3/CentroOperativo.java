package repaso_avanzado3;

public abstract class CentroOperativo implements Supervisable {

    // Atributos privados (encapsulados) que definirán el estado de cada centro operativo.
    private String codigo;        // Identificador único del centro (ej: "D-01")
    private Zona zona;            // Tipo enumerado (enum) que indica la zona geográfica (NORTE, SUR, etc.)
    private Responsable responsable; // Objeto de la clase Responsable asignado al centro
    private int[][] operaciones;  // Matriz 2D para guardar las operaciones (filas = días, columnas = turnos)
    private int[][] incidencias;  // Matriz 2D para guardar las incidencias (filas = días, columnas = turnos)

    // Constructor de la clase. Se llama cuando las clases hijas (CentroDistribucion, etc.) se instancian.
    // Recibe todos los datos necesarios para inicializar el estado del objeto.
    public CentroOperativo(String codigo, Zona zona, Responsable responsable, int[][] operaciones, int[][] incidencias) {
        this.codigo = codigo;           // "this" diferencia el atributo de la clase del parámetro recibido
        this.zona = zona;
        this.responsable = responsable;
        this.operaciones = operaciones;
        this.incidencias = incidencias;
    }

    // -------------------------------------------------------------------------
    // Bloque de métodos "Getters": Permiten leer (obtener) el valor de los 
    // atributos privados desde fuera de la clase.
    // -------------------------------------------------------------------------
    public String getCodigo() {
        return codigo;
    }

    public Zona getZona() {
        return zona;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public int[][] getOperaciones() {
        return operaciones;
    }

    public int[][] getIncidencias() {
        return incidencias;
    }

    // -------------------------------------------------------------------------
    // Bloque de métodos de cálculo de la clase
    // -------------------------------------------------------------------------

    // Método que suma todos los valores dentro de la matriz bidimensional de operaciones
    public int calcularTotalOperaciones() {
        int total = 0; // Acumulador inicializado a 0

        // Bucle externo que recorre las filas (los días de la semana)
        for (int i = 0; i < operaciones.length; i++) {
            // Bucle interno que recorre las columnas (los turnos dentro de un día)
            for (int j = 0; j < operaciones[i].length; j++) {
                total += operaciones[i][j]; // Suma el valor de la celda actual al total
            }
        }

        return total; // Devuelve la suma completa
    }

    // Método que suma todos los valores dentro de la matriz bidimensional de incidencias
    public int calcularTotalIncidencias() {
        int total = 0;

        // Misma lógica que el método anterior, pero recorriendo la matriz 'incidencias'
        for (int i = 0; i < incidencias.length; i++) {
            for (int j = 0; j < incidencias[i].length; j++) {
                total += incidencias[i][j];
            }
        }

        return total;
    }

    // Método que suma las operaciones de un día en concreto pasado por parámetro (ej: 0 para el Lunes)
    public int calcularOperacionesDia(int dia) {
        int total = 0;

        // Recorre únicamente las columnas (turnos) de la fila específica ('dia')
        for (int j = 0; j < operaciones[dia].length; j++) {
            total += operaciones[dia][j]; // Suma el turno de mañana (0) y tarde (1)
        }

        return total;
    }

    // Método que suma las incidencias de un día en concreto
    public int calcularIncidenciasDia(int dia) {
        int total = 0;

        // Recorre únicamente las columnas (turnos) de la fila específica ('dia')
        for (int j = 0; j < incidencias[dia].length; j++) {
            total += incidencias[dia][j];
        }

        return total;
    }

    // Método que calcula el porcentaje que representan las incidencias respecto a las operaciones totales
    public double calcularTasaIncidencias() {
        // Reutiliza los métodos creados anteriormente para obtener los totales
        int totalOperaciones = calcularTotalOperaciones();
        int totalIncidencias = calcularTotalIncidencias();

        // Control de seguridad para evitar un error matemático (división por cero)
        if (totalOperaciones == 0) {
            return 0; 
        }

        // Se multiplica por 100.0 (con decimal) para forzar que el resultado sea 'double' (decimal)
        // en lugar de una división entera perdiendo precisión, calculando así el porcentaje.
        return (totalIncidencias * 100.0) / totalOperaciones;
    }

    // Método que imprime por pantalla un desglose día a día de todo lo ocurrido en el centro
    public void mostrarResumenSemanal() {
        // Array auxiliar con los nombres de los días correspondientes a cada índice (0 a 4)
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};

        System.out.println("Resumen semanal del centro " + codigo + ":");
        
        // Recorre las 5 filas (los 5 días)
        for (int i = 0; i < operaciones.length; i++) {
            // Imprime concatenando el nombre del día y accediendo directamente a [i][0] (mañana) y [i][1] (tarde)
            System.out.println(
                dias[i] +
                " -> Operaciones mañana: " + operaciones[i][0] +
                ", Operaciones tarde: " + operaciones[i][1] +
                ", Incidencias mañana: " + incidencias[i][0] +
                ", Incidencias tarde: " + incidencias[i][1]
            );
        }
    }

    // Declaración de un método abstracto. 
    // Obliga a que cualquier clase hija (CentroDistribucion, CentroSeguridad) programe (implemente)
    // su propia versión de cómo se calcula este índice, ya que cada tipo de centro tendrá su propia fórmula matemática.
    public abstract double calcularIndiceEficiencia();

    // Sobrescritura (Override) del método toString() heredado de la clase base Object.
    // Sirve para que, cuando intentemos imprimir un objeto de esta clase (ej: System.out.println(centro)), 
    // nos devuelva este texto formateado en lugar de la dirección de memoria genérica de Java.
    @Override
    public String toString() {
        return "Código: " + codigo + ", Zona: " + zona + ", Responsable: [" + responsable + "]";
    }
}