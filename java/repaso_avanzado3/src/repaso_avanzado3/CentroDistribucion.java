package repaso_avanzado3;

public class CentroDistribucion extends CentroOperativo {

    // Atributo privado y exclusivo de esta clase hija. 
    // Los centros de distribución manejan paquetes urgentes, a diferencia de otros centros.
    private int paquetesUrgentes;

    // Constructor de la clase. Recibe los parámetros necesarios para la clase padre (CentroOperativo)
    // más el parámetro específico de esta clase hija (paquetesUrgentes).
    public CentroDistribucion(String codigo, Zona zona, Responsable responsable, int[][] operaciones,
                              int[][] incidencias, int paquetesUrgentes) {
        // La llamada a "super()" DEBE ser la primera línea del constructor.
        // Sirve para enviar los datos al constructor de la clase padre (CentroOperativo) para que inicialice esos atributos.
        super(codigo, zona, responsable, operaciones, incidencias);
        
        // Inicializa el atributo propio de esta clase con el valor recibido por parámetro.
        this.paquetesUrgentes = paquetesUrgentes;
    }

    // Método Getter para obtener la cantidad de paquetes urgentes desde fuera de la clase.
    public int getPaquetesUrgentes() {
        return paquetesUrgentes;
    }

    // La anotación @Override indica que estamos sobrescribiendo (o implementando) un método de la clase padre.
    // Aquí estamos dando cuerpo al método abstracto "calcularIndiceEficiencia" que CentroOperativo nos obligaba a crear.
    @Override
    public double calcularIndiceEficiencia() {
        // La fórmula específica para un centro de distribución:
        // (Total de operaciones - Total de incidencias) + (Paquetes urgentes multiplicados por un peso de 1.5)
        // Llama a los métodos de la clase padre para obtener los totales.
        return calcularTotalOperaciones() - calcularTotalIncidencias() + paquetesUrgentes * 1.5;
    }

    // Sobrescritura del método necesitaAuditoria(). 
    // Este método probablemente proviene de la interfaz "Supervisable" que implementaba la clase padre.
    @Override
    public boolean necesitaAuditoria() {
        // Condición 1: Si la tasa de incidencias (porcentaje) supera el 20%, necesita auditoría.
        if (calcularTasaIncidencias() > 20) {
            return true;
        }

        // Condición 2: Recorre los 5 días de la semana (de 0 a 4).
        for (int dia = 0; dia < 5; dia++) {
            // Si en ALGÚN día de la semana el centro no llega a 8 operaciones en total (mañana + tarde),
            // se considera un rendimiento bajo y necesita auditoría.
            if (calcularOperacionesDia(dia) < 8) {
                return true;
            }
        }

        // Condición 3: Si la suma absoluta de incidencias en toda la semana supera las 15, necesita auditoría.
        if (calcularTotalIncidencias() > 15) {
            return true;
        }

        // Si sobrevive a todos los "if" anteriores sin retornar "true", significa que el centro está sano.
        return false;
    }

    // Sobrescribe el método toString() para personalizar el texto que se imprime sobre este objeto.
    @Override
    public String toString() {
        // super.toString() llama al texto generado por la clase padre (Código, Zona, Responsable).
        // A ese texto, se le concatena (añade) la información específica de esta clase (Tipo y Paquetes urgentes).
        return super.toString() + ", Tipo: CentroDistribucion, Paquetes urgentes: " + paquetesUrgentes;
    }
}