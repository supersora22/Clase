package simulacroTema5;

public class Main {
	public static void main(String[] args) {
        Empleado[] lista = new Empleado[4]; // hacemos un array de 4 huecos para meter a los empleados

        lista[0] = new Programador("Dante", "P01", 12);
        lista[1] = new Programador("Beatriz", "P02", 5);
        lista[2] = new Administrativo("Carlos", "A01", 40);
        lista[3] = new Administrativo("Elena", "A02", 25);
        
        
        double[][] horasBase = { // creamos una matriz con horas ya puestas para no tener que escribirlas a mano luego
            {4, 3},
            {4, 4},
            {5, 3},
            {4, 4},
            {3, 2}
        };

        for (int i = 0; i < lista.length; i++) { // recorremos el array de empleados
            for (int fila = 0; fila < 5; fila++) { // recorremos los 5 dias
                for (int col = 0; col < 2; col++) { // recorremos los 2 turnos
                    lista[i].getHorasTrabajadas()[fila][col] = horasBase[fila][col]; // le metemos al empleado las horasBase en su propia matriz
                }
            }
        }

        for (Empleado e : lista) { // recorremos el array de empleados entero (for each)
            System.out.println("----------------------------------");
            System.out.println("DATOS: " + e.toString()); // mostramos los datos con el toString
            e.mostrarHorario();
            System.out.println("Horas Semanales: " + e.calcularHorasTotalesSemana());
            System.out.println("Productividad: " + e.calcularProductividad());
            System.out.println("¿Merece Reconocimiento?: " + e.mereceReconocimiento());
        }

        Empleado masProductivo = lista[0]; // pillamos al primer empleado como el mas productivo por defecto
        int numProgramadores = 0; // iniciamos los contadores a cero
        int numAdministrativos = 0;

        for (Empleado e : lista) { // volvemos a recorrer el array entero
            if (e.calcularProductividad() > masProductivo.calcularProductividad()) {
                masProductivo = e; // si el que estamos mirando tiene mas productividad que el guardado, lo cambiamos
            }

            if (e instanceof Programador) { // usamos instanceof para comprobar si es un Programador
                numProgramadores++; // si lo es, le sumamos 1 al contador
            } else if (e instanceof Administrativo) { // comprobamos si es Administrativo
                numAdministrativos++;
            }
        }

        System.out.println("\n==================================");
        System.out.println("Empleado con mayor productividad: " + masProductivo.getNombre());
        System.out.println("Cantidad de Programadores: " + numProgramadores);
        System.out.println("Cantidad de Administrativos: " + numAdministrativos);

        double sumaMañana = 0;
        double sumaTarde = 0;
        int divisor = lista.length * 5; // calculamos el divisor multiplicando los empleados por los 5 dias 

        for (Empleado e : lista) { // otro for each para calcular las medias
            double[][] h = e.getHorasTrabajadas(); // guardamos la matriz del empleado en una variable h para escribir menos
            for (int i = 0; i < 5; i++) {
                sumaMañana += h[i][0]; // acumulamos las horas de la mañana (columna 0)
                sumaTarde += h[i][1]; // acumulamos las horas de la tarde (columna 1)
            }
        }

        System.out.println("Media Mañana: " + (sumaMañana / divisor)); // sacamos la media dividiendo el total por el divisor
        System.out.println("Media Tarde: " + (sumaTarde / divisor));
        System.out.println("==================================");
    }
}