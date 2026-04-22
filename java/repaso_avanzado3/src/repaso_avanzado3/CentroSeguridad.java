package repaso_avanzado3;

public class CentroSeguridad extends CentroOperativo {

    // Atributo privado exclusivo de los centros de seguridad.
    // Representa el número de inspecciones especiales realizadas por este centro.
    private int inspeccionesEspeciales;

    // Constructor de la clase. Recibe todos los parámetros necesarios para inicializar
    // tanto la parte genérica del centro operativo como la parte específica de seguridad.
    public CentroSeguridad(String codigo, Zona zona, Responsable responsable, int[][] operaciones,
                           int[][] incidencias, int inspeccionesEspeciales) {
        
        // Llamada al constructor de la clase padre (CentroOperativo) usando "super()".
        // Le pasamos los 5 primeros parámetros para que inicialice esos atributos heredados.
        // Debe ser obligatoriamente la primera instrucción del constructor.
        super(codigo, zona, responsable, operaciones, incidencias);
        
        // Inicializa el atributo propio de esta clase.
        this.inspeccionesEspeciales = inspeccionesEspeciales;
    }

    // Método Getter para consultar el número de inspecciones especiales desde otras clases.
    public int getInspeccionesEspeciales() {
        return inspeccionesEspeciales;
    }

    // Sobrescritura (@Override) del método abstracto definido en la clase padre.
    // Aquí implementamos la fórmula matemática específica para calcular la eficiencia de un centro de seguridad.
    @Override
    public double calcularIndiceEficiencia() {
        // La fórmula es: (Total de operaciones) - (Total incidencias penalizadas a la mitad, * 0.5) + (Inspecciones especiales con peso doble, * 2).
        return calcularTotalOperaciones() - calcularTotalIncidencias() * 0.5 + inspeccionesEspeciales * 2;
    }

    // Sobrescritura del método de la interfaz Supervisable (a través de la clase padre)
    // para determinar si este centro específico necesita una auditoría.
    @Override
    public boolean necesitaAuditoria() {
        
        // Bucle externo para recorrer las filas (los días de la semana)
        // Nota: Como la matriz "operaciones" es privada en la clase padre, 
        // usamos el método getOperaciones() para poder acceder a ella.
        for (int i = 0; i < getOperaciones().length; i++) {
            
            // Bucle interno para recorrer las columnas (turnos de mañana y tarde)
            for (int j = 0; j < getOperaciones()[i].length; j++) {
                
                // Condición 1: Si en algún turno (mañana o tarde) de cualquier día hubo 0 operaciones,
                // salta la alarma y devuelve true (necesita auditoría).
                if (getOperaciones()[i][j] == 0) {
                    return true;
                }

                // Condición 2: Si en un mismo turno hubo más incidencias que operaciones,
                // significa que la situación fue crítica en ese momento y devuelve true.
                if (getIncidencias()[i][j] > getOperaciones()[i][j]) {
                    return true;
                }
            }
        }

        // Condición 3: Si tras sumar todas las operaciones de la semana, el total no llega a 35,
        // el rendimiento global se considera demasiado bajo y devuelve true.
        if (calcularTotalOperaciones() < 35) {
            return true;
        }

        // Si se han revisado todos los turnos y totales y no se ha cumplido ninguna 
        // de las condiciones negativas anteriores, el centro está bien y devuelve false.
        return false;
    }

    // Sobrescribe el método toString() para personalizar la impresión del objeto.
    @Override
    public String toString() {
        // Aprovecha el texto base generado por la clase padre (super.toString()) 
        // y le concatena la información particular de la clase CentroSeguridad.
        return super.toString() + ", Tipo: CentroSeguridad, Inspecciones especiales: " + inspeccionesEspeciales;
    }
}