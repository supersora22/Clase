package actividad6;
import java.util.Scanner;

public class actividad6 {
	   public static void main(String[] args) {
	        Scanner teclado = new Scanner(System.in);
	        int aprobados, suspensos, numAlumnos;
	        float nota, porcentajeAprobados, porcentajeSuspensos;
	        aprobados=0;
	        suspensos=0;
	        // Leer número de alumnos con validación usando do...while
	        do {
	            System.out.print("Introduce el número de alumnos (mayor que 0): ");
	            numAlumnos = teclado.nextInt();
	            if (numAlumnos <= 0) {
	                System.out.println("El número de alumnos debe ser mayor que 0. Inténtalo de nuevo.");
	            }
	        } while (numAlumnos <= 0);
	        // Leer las notas
	        for (int i = 1; i <= numAlumnos; i++) {
	        	
	            System.out.print("Introduce la nota del alumno " + i + ": ");
	            nota = teclado.nextFloat();

	            if (nota >= 5) {
	                aprobados++;
	            } else {
	                suspensos++;
	            }
	        }
	        // Calcular porcentajes
	        porcentajeAprobados= (float) aprobados / numAlumnos * 100;
	        porcentajeSuspensos= (float) suspensos / numAlumnos * 100;
	        // Mostrar resultados
	        System.out.println("RESULTADOS:");
	        System.out.println("Aprobados: " + aprobados);
	        System.out.println("Suspensos: " + suspensos);
	        System.out.println("Porcentaje de aprobados: " + porcentajeAprobados + "%");
	        System.out.println("Porcentaje de suspensos: " + porcentajeSuspensos + "%");
	        teclado.close();
	    }
	}
