package actividad10a;

import java.util.Scanner;

public class Actividad10a {
    public static void main(String[] args) {
            Scanner teclado = new Scanner(System.in);
            int base;
            char caracter;
            // Pedir base impar
            do {
                System.out.print("Introduce la base del triángulo (impar): ");
                base = teclado.nextInt();
                if (base % 2 == 0) {
                    System.out.println("La base debe ser un número impar. Inténtalo de nuevo.");
                }
            } while (base % 2 == 0);
            // Pedir carácter
            System.out.print("Introduce el carácter para dibujar el triángulo: ");
            caracter = teclado.next().charAt(0);
            System.out.println("Triángulo centrado:");
            // Dibujar triángulo centrado
            for (int i = 1; i <= base; i += 2) {
                int espacios = (base - i) / 2;
                // imprimir espacios
                for (int j = 0; j < espacios; j++) {
                    System.out.print(" ");
                }
                // imprimir caracteres
                for (int j = 0; j < i; j++) {
                    System.out.print(caracter);
                }
                System.out.println();
            }
            teclado.close();
        }
    }
