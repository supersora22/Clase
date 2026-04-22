package simulacro;

import java.util.*;

public class Main {
    private static Scanner teclado = new Scanner(System.in);
    
    public static void main(String[] args) {

        Alumno[] alumnos = new Alumno[5]; // creamos un array vacio preparado para guardar 5 alumnos
        
        for (int i = 0; i < alumnos.length; i++) { // hacemos un bucle que de 5 vueltas para rellenar el array
            System.out.println("Introduzca valores del alumno " + (i + 1) + ":"); // usamos i+1 para decirle al usuario el numero del alumno
            System.out.print("Nombre: ");
            String nombre = teclado.nextLine(); // guardamos lo que escribe el usuario como el nombre

            boolean valido = false; // creamos un boolean en falso para controlar el bucle de pedir la nota
            while (!valido) { // mientras valido no sea true, el bucle seguira pidiendo la nota sin parar
                try {
                    System.out.print("Nota: ");
                    double nota = Double.parseDouble(teclado.nextLine()); // leemos el texto y lo convertimos a double del tiron
                    alumnos[i] = new Alumno(nombre, nota); // creamos el objeto alumno y lo metemos en su posicion del array
                    valido = true; // si llegamos aqui es que no ha saltado ningun error, cambiamos a true para salir del while
                    
                } catch (NotaInvalidaException e) {
                    System.out.println(e.getMessage()); // mostramos el error si mete un 12 o un -5
                } catch (NumberFormatException e) {
                    System.out.println("Error: Has introducido letras. Por favor, escribe un número."); // mostramos el error si mete texto en vez de numeros
                }
            }
        }
        
        System.out.println("----------------------------------------");
        System.out.println("Lista Original");
        System.out.println("----------------------------------------");
        for (Alumno a : alumnos) { // recorremos el array de alumnos entero (for each)
            System.out.println(a); // mostramos los datos de cada alumno
        }

        Arrays.sort(alumnos); // ordenamos el array de menor a mayor nota gracias al compareTo que hicimos
        System.out.println("----------------------------------------");
        System.out.println("El mejor alumno es: " + alumnos[alumnos.length - 1]); // como estan ordenados de menor a mayor, el ultimo es el mejor
        System.out.println("----------------------------------------");

        for (Alumno a : alumnos) { // volvemos a recorrer el array para subirles la nota
            try {
                double nuevaNota = a.getNota() + 1; // pillamos la nota que tiene y le sumamos 1
                if (nuevaNota > 10) nuevaNota = 10; // si al sumarle 1 se pasa de 10, la dejamos en 10 como tope
                a.setNota(nuevaNota); // guardamos la nueva nota llamando al metodo
            } catch (NotaInvalidaException e) {
                System.out.println("Error subiendo la nota: " + e.getMessage()); // capturamos el error por si acaso el setNota fallase
            }
        }
        
        System.out.println("----------------------------------------");
        System.out.println("Lista Final (+1 punto)");
        System.out.println("----------------------------------------");
        for (Alumno a : alumnos) System.out.println(a); // mostramos la lista definitiva ya actualizada
    }
}