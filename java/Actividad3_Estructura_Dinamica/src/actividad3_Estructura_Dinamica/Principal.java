package actividad3_Estructura_Dinamica;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Principal {
    private static Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {
        Historial miHistorial = new Historial();
        int opcion = 0;

        do {
            System.out.println("\n--- MENÚ HISTORIAL WEB ---");
            System.out.println("1. Nueva página consultada");
            System.out.println("2. Consultar historial completo");
            System.out.println("3. Consultar historial de un día");
            System.out.println("4. Borrar historial completo");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(teclado.nextLine());
                switch (opcion) {
                    case 1:
                        System.out.print("Introduce la URL de la página: ");
                        String url = teclado.nextLine();
                        LocalDateTime ahora = LocalDateTime.now();
                        if (miHistorial.agregarPagina(url, ahora)) {
                            System.out.println("Página registrada con éxito.");
                        } else {
                            System.out.println("Error: La fecha es anterior al último registro.");
                        }
                        break;
                    case 2:
                        miHistorial.consultarCompleto();
                        break;
                    case 3:
                        System.out.print("Introduce la fecha (AAAA-MM-DD): ");
                        try {
                            LocalDate fechaBusqueda = LocalDate.parse(teclado.nextLine());
                            miHistorial.consultarPorDia(fechaBusqueda);
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de fecha incorrecto.");
                        }
                        break;
                    case 4:
                        miHistorial.borrarTodo();
                        break;
                    case 5:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un número válido.");
            }
        } while (opcion != 5);
    }
}