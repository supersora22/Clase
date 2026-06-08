package simulacro;

import java.util.*;

public class Main {
	private static Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {

        Alumno[] alumnos = new Alumno[5];
        for (int i = 0; i < alumnos.length; i++) {
            System.out.println("IntroduzKa valores del alumno " + (i + 1) + ":");
            System.out.print("Nombre: ");
            String nombre = teclado.nextLine();

            boolean valido = false;
            while (!valido) {
                try {
                    System.out.print("Nota: ");
                    double nota = Double.parseDouble(teclado.nextLine());
                    alumnos[i] = new Alumno(nombre, nota);
                    valido = true; 
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println("----------------------------------------");
        System.out.println("Lista Original");
        System.out.println("----------------------------------------");
        for (Alumno a : alumnos) {
        	System.out.println(a);
        }

        Arrays.sort(alumnos);
        System.out.println("----------------------------------------");
        System.out.println("El mejor alumno es: " + alumnos[alumnos.length - 1]);
        System.out.println("----------------------------------------");

        for (Alumno a : alumnos) {
            double nuevaNota = a.getNota() + 1;
            if (nuevaNota > 10) nuevaNota = 10;
            a.setNota(nuevaNota);
        }
        System.out.println("----------------------------------------");
        System.out.println("Lista Final (+1 punto)");
        System.out.println("----------------------------------------");
        for (Alumno a : alumnos) System.out.println(a);
    }
}