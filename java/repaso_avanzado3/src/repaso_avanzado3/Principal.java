package repaso_avanzado3;

public class Principal {

    public static void main(String[] args) {

        // Creación de 6 objetos de tipo Responsable, pasando por parámetro DNI, Nombre y Años de antigüedad
        Responsable r1 = new Responsable("11111111A", "Ana Torres", 12);
        Responsable r2 = new Responsable("22222222B", "Luis Romero", 8);
        Responsable r3 = new Responsable("33333333C", "Marta Gil", 15);
        Responsable r4 = new Responsable("44444444D", "Pablo Ruiz", 6);
        Responsable r5 = new Responsable("55555555E", "Clara Soto", 11);
        Responsable r6 = new Responsable("66666666F", "Javier León", 9);

        // Se declaran e inicializan las matrices bidimensionales de operaciones e incidencias.
        // Tienen un tamaño de 5x2 (5 días de la semana, 2 turnos: mañana y tarde).
        
        // Operaciones del centro 1
        int[][] op1 = {
            {5, 4}, // Lunes: 5 mañana, 4 tarde
            {6, 3}, // Martes: 6 mañana, 3 tarde
            {4, 5}, // Miércoles...
            {7, 2}, // Jueves...
            {5, 4}  // Viernes...
        };

        // Incidencias del centro 1
        int[][] in1 = {
            {1, 0},
            {0, 1},
            {1, 1},
            {0, 1},
            {1, 0}
        };

        // Operaciones del centro 2
        int[][] op2 = { {3, 4}, {4, 3}, {5, 2}, {3, 3}, {4, 2} };

        // Incidencias del centro 2
        int[][] in2 = { {1, 1}, {2, 0}, {1, 1}, {1, 0}, {2, 1} };

        // Operaciones del centro 3
        int[][] op3 = { {6, 5}, {5, 4}, {6, 4}, {5, 5}, {4, 4} };

        // Incidencias del centro 3
        int[][] in3 = { {0, 1}, {1, 0}, {1, 1}, {0, 1}, {1, 0} };

        // Operaciones del centro 4
        int[][] op4 = { {4, 4}, {5, 3}, {0, 4}, {6, 3}, {5, 2} };

        // Incidencias del centro 4
        int[][] in4 = { {1, 0}, {1, 1}, {0, 1}, {1, 0}, {1, 1} };

        // Operaciones del centro 5
        int[][] op5 = { {2, 2}, {3, 3}, {2, 1}, {4, 2}, {3, 2} };

        // Incidencias del centro 5
        int[][] in5 = { {1, 0}, {0, 1}, {1, 0}, {0, 1}, {1, 1} };

        // Operaciones del centro 6
        int[][] op6 = { {5, 5}, {6, 4}, {5, 5}, {4, 4}, {6, 3} };

        // Incidencias del centro 6
        int[][] in6 = { {0, 1}, {1, 0}, {1, 1}, {0, 0}, {1, 0} };

        // Declaración de un array polimórfico de tipo CentroOperativo con capacidad para 6 elementos
        CentroOperativo[] centros = new CentroOperativo[6];

        // Se instancian 3 objetos de la clase hija CentroDistribucion y se guardan en el array.
        // Se pasa: código, zona (Enum), responsable, matriz de operaciones, matriz de incidencias, y un valor específico de distribución (ej: número de vehículos).
        centros[0] = new CentroDistribucion("D-01", Zona.NORTE, r1, op1, in1, 10);
        centros[1] = new CentroDistribucion("D-02", Zona.SUR, r2, op2, in2, 7);
        centros[2] = new CentroDistribucion("D-03", Zona.CENTRAL, r3, op3, in3, 12);

        // Se instancian 3 objetos de la clase hija CentroSeguridad y se guardan en el array.
        // Se pasa: código, zona, responsable, operaciones, incidencias, y un valor específico de seguridad (ej: número de cámaras).
        centros[3] = new CentroSeguridad("S-01", Zona.ESTE, r4, op4, in4, 8);
        centros[4] = new CentroSeguridad("S-02", Zona.OESTE, r5, op5, in5, 6);
        centros[5] = new CentroSeguridad("S-03", Zona.NORTE, r6, op6, in6, 9);

        // Llamadas secuenciales a diversos métodos estáticos para procesar y mostrar los datos del array
        mostrarTodaLaInformacion(centros);
        mostrarCentroMayorEficiencia(centros);
        mostrarCentroPeorTasa(centros);
        contarTipos(centros);
        mostrarMediasGlobalesPorTurno(centros);
        mostrarDiaMasProblematico(centros);
        mostrarCentroMasEquilibrado(centros);
        mostrarCentrosConResponsableVeterano(centros);
        mostrarCentrosDestacados(centros);
    }

    // Método que muestra todos los datos y cálculos de cada centro en el array
    public static void mostrarTodaLaInformacion(CentroOperativo[] centros) {
        // Imprime una cabecera para identificar la sección
        System.out.println("========== INFORMACIÓN COMPLETA ==========");

        // Bucle for que recorre cada centro del array
        for (int i = 0; i < centros.length; i++) {
            System.out.println("------------------------------------------"); // Separador visual
            System.out.println(centros[i]); // Llama implícitamente al método toString() del objeto para mostrar sus datos básicos
            centros[i].mostrarResumenSemanal(); // Llama a un método del objeto para imprimir el resumen
            // Llama a los métodos de cálculo del objeto e imprime los resultados
            System.out.println("Total operaciones: " + centros[i].calcularTotalOperaciones());
            System.out.println("Total incidencias: " + centros[i].calcularTotalIncidencias());
            System.out.println("Tasa incidencias: " + centros[i].calcularTasaIncidencias());
            System.out.println("Índice de eficiencia: " + centros[i].calcularIndiceEficiencia());
            System.out.println("¿Necesita auditoría?: " + centros[i].necesitaAuditoria());
        }
    }

    // Método para encontrar e imprimir el centro con el mayor índice de eficiencia
    public static void mostrarCentroMayorEficiencia(CentroOperativo[] centros) {
        // Asume inicialmente que el primer centro (índice 0) es el más eficiente
        CentroOperativo mejor = centros[0];

        // Recorre el array desde el segundo elemento (índice 1) en adelante
        for (int i = 1; i < centros.length; i++) {
            // Si el centro actual tiene mayor eficiencia que el que guardamos como 'mejor'...
            if (centros[i].calcularIndiceEficiencia() > mejor.calcularIndiceEficiencia()) {
                mejor = centros[i]; // ...actualizamos la variable 'mejor' con este nuevo centro
            }
        }

        // Imprime los datos del centro más eficiente encontrado
        System.out.println("========== CENTRO CON MAYOR EFICIENCIA ==========");
        System.out.println("Código: " + mejor.getCodigo());
        System.out.println("Responsable: " + mejor.getResponsable().getNombre());
        System.out.println("Índice: " + mejor.calcularIndiceEficiencia());

        // Comprueba con 'instanceof' de qué clase hija es exactamente el objeto e imprime su tipo
        if (mejor instanceof CentroDistribucion) {
            System.out.println("Tipo: CentroDistribucion");
        } else if (mejor instanceof CentroSeguridad) {
            System.out.println("Tipo: CentroSeguridad");
        }
    }

    // Método para encontrar e imprimir el centro con la peor (más alta) tasa de incidencias
    public static void mostrarCentroPeorTasa(CentroOperativo[] centros) {
        // Asume inicialmente que el primer centro tiene la peor tasa
        CentroOperativo peor = centros[0];

        // Recorre el resto del array
        for (int i = 1; i < centros.length; i++) {
            // Si la tasa del centro actual es mayor que la guardada...
            if (centros[i].calcularTasaIncidencias() > peor.calcularTasaIncidencias()) {
                peor = centros[i]; // ...se guarda como el nuevo peor
            }
        }

        // Imprime los datos del centro con peor tasa
        System.out.println("========== PEOR TASA DE INCIDENCIAS ==========");
        System.out.println("Código: " + peor.getCodigo());
        System.out.println("Tasa: " + peor.calcularTasaIncidencias());

        // Identifica el tipo de centro mediante 'instanceof'
        if (peor instanceof CentroDistribucion) {
            System.out.println("Tipo: CentroDistribucion");
        } else if (peor instanceof CentroSeguridad) {
            System.out.println("Tipo: CentroSeguridad");
        }
    }

    // Método que cuenta cuántos centros hay de cada tipo
    public static void contarTipos(CentroOperativo[] centros) {
        int distribucion = 0; // Contador para centros de distribución
        int seguridad = 0;    // Contador para centros de seguridad

        // Recorre todos los centros
        for (int i = 0; i < centros.length; i++) {
            // Si el objeto actual es de la clase CentroDistribucion, incrementa el contador
            if (centros[i] instanceof CentroDistribucion) {
                distribucion++;
            } 
            // Si es de la clase CentroSeguridad, incrementa el otro contador
            else if (centros[i] instanceof CentroSeguridad) {
                seguridad++;
            }
        }

        // Muestra el resultado de los contadores
        System.out.println("========== CONTEO DE TIPOS ==========");
        System.out.println("Centros de distribución: " + distribucion);
        System.out.println("Centros de seguridad: " + seguridad);
    }

    // Método que calcula las medias de operaciones e incidencias según sea turno de mañana o tarde
    public static void mostrarMediasGlobalesPorTurno(CentroOperativo[] centros) {
        // Variables acumuladoras (se usan double para permitir decimales en la división posterior)
        double opManana = 0;
        double opTarde = 0;
        double inManana = 0;
        double inTarde = 0;

        // Calcula el total de días evaluados (nº de centros multiplicados por 5 días de la semana)
        int totalDias = centros.length * 5;

        // Bucle externo que recorre los centros
        for (int i = 0; i < centros.length; i++) {
            // Bucle interno que recorre los 5 días de la semana (filas de las matrices)
            for (int dia = 0; dia < 5; dia++) {
                // Suma los valores de la columna 0 (mañana) y columna 1 (tarde) de ambas matrices
                opManana += centros[i].getOperaciones()[dia][0];
                opTarde += centros[i].getOperaciones()[dia][1];
                inManana += centros[i].getIncidencias()[dia][0];
                inTarde += centros[i].getIncidencias()[dia][1];
            }
        }

        // Calcula e imprime las medias dividiendo el acumulado total entre los días totales
        System.out.println("========== MEDIAS GLOBALES POR TURNO ==========");
        System.out.println("Media operaciones mañana: " + (opManana / totalDias));
        System.out.println("Media operaciones tarde: " + (opTarde / totalDias));
        System.out.println("Media incidencias mañana: " + (inManana / totalDias));
        System.out.println("Media incidencias tarde: " + (inTarde / totalDias));
    }

    // Método que determina qué día de la semana suma más incidencias entre todos los centros
    public static void mostrarDiaMasProblematico(CentroOperativo[] centros) {
        int diaMax = 0;          // Guardará el índice del día más problemático (0=Lunes, 4=Viernes)
        int maxIncidencias = -1; // Guardará el récord de incidencias encontradas

        // Bucle externo que itera sobre los 5 días de la semana (0 a 4)
        for (int dia = 0; dia < 5; dia++) {
            int totalDia = 0; // Acumulador de incidencias para el día actual

            // Bucle interno que recorre todos los centros
            for (int i = 0; i < centros.length; i++) {
                // Suma las incidencias del centro actual en el día específico
                totalDia += centros[i].calcularIncidenciasDia(dia);
            }

            // Si el total de incidencias de este día supera al máximo registrado...
            if (totalDia > maxIncidencias) {
                maxIncidencias = totalDia; // ...actualizamos el máximo
                diaMax = dia;              // ...y guardamos el índice del día
            }
        }

        // Array auxiliar para traducir el número del día (0-4) a su nombre en texto
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};

        // Imprime el resultado final utilizando el array para obtener el nombre del día
        System.out.println("========== DÍA MÁS PROBLEMÁTICO ==========");
        System.out.println("Día: " + dias[diaMax]);
        System.out.println("Total incidencias: " + maxIncidencias);
    }

    // Método que busca el centro donde la diferencia entre operaciones totales e incidencias es más pequeña (Math.abs)
    public static void mostrarCentroMasEquilibrado(CentroOperativo[] centros) {
        // Asume que el primer centro es el más equilibrado
        CentroOperativo equilibrado = centros[0];
        // Calcula la diferencia absoluta (sin signos negativos) inicial
        int diferenciaMin = Math.abs(centros[0].calcularTotalOperaciones() - centros[0].calcularTotalIncidencias());

        // Recorre el resto de centros
        for (int i = 1; i < centros.length; i++) {
            // Calcula la diferencia absoluta del centro actual
            int diferenciaActual = Math.abs(centros[i].calcularTotalOperaciones() - centros[i].calcularTotalIncidencias());

            // Si la diferencia actual es menor que la mínima registrada (es más equilibrado)...
            if (diferenciaActual < diferenciaMin) {
                diferenciaMin = diferenciaActual; // ...actualiza la diferencia mínima
                equilibrado = centros[i];         // ...y guarda el centro como el más equilibrado
            }
        }

        // Muestra los datos del centro ganador
        System.out.println("========== CENTRO MÁS EQUILIBRADO ==========");
        System.out.println("Código: " + equilibrado.getCodigo());
        System.out.println("Diferencia: " + diferenciaMin);

        // Muestra su tipo verificando de qué clase es instancia
        if (equilibrado instanceof CentroDistribucion) {
            System.out.println("Tipo: CentroDistribucion");
        } else if (equilibrado instanceof CentroSeguridad) {
            System.out.println("Tipo: CentroSeguridad");
        }
    }

    // Método para listar qué centros están a cargo de responsables con más de 10 años de antigüedad
    public static void mostrarCentrosConResponsableVeterano(CentroOperativo[] centros) {
        System.out.println("========== RESPONSABLES CON MÁS DE 10 AÑOS ==========");

        // Recorre todos los centros
        for (int i = 0; i < centros.length; i++) {
            // Accede al objeto Responsable del centro y comprueba si su antigüedad supera los 10 años
            if (centros[i].getResponsable().getAntiguedad() > 10) {
                // Imprime los datos (usando print en vez de println para que quede en la misma línea)
                System.out.print("Código: " + centros[i].getCodigo());
                System.out.print(", Responsable: " + centros[i].getResponsable().getNombre());
                System.out.print(", Antigüedad: " + centros[i].getResponsable().getAntiguedad());

                // Imprime el tipo de centro según su clase y hace el salto de línea al final (println)
                if (centros[i] instanceof CentroDistribucion) {
                    System.out.println(", Tipo: CentroDistribucion");
                } else if (centros[i] instanceof CentroSeguridad) {
                    System.out.println(", Tipo: CentroSeguridad");
                }
            }
        }
    }

    // Método que imprime los centros que cumplen simultáneamente tres criterios concretos
    public static void mostrarCentrosDestacados(CentroOperativo[] centros) {
        System.out.println("========== CENTROS DESTACADOS ==========");

        // Recorre los centros
        for (int i = 0; i < centros.length; i++) {
            // Evalúa tres condiciones usando el operador AND lógico (&&):
            // 1. Tener más de 40 operaciones totales
            // 2. Tener menos de 10 incidencias totales
            // 3. NO necesitar auditoría (el operador ! niega el resultado booleano)
            if (centros[i].calcularTotalOperaciones() > 40 &&
                centros[i].calcularTotalIncidencias() < 10 &&
                !centros[i].necesitaAuditoria()) {

                // Si se cumplen TODAS las condiciones, se imprime el centro
                System.out.println("Código: " + centros[i].getCodigo() +
                                   ", Operaciones: " + centros[i].calcularTotalOperaciones() +
                                   ", Incidencias: " + centros[i].calcularTotalIncidencias());
            }
        }
    }
}
