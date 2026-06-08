package actividad11;

import java.util.*;

public class Actividad11 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int base, mitad;
        char caracter;
        // Pedir base impar
        do {
            System.out.print("Introduce la base del rombo (impar): ");
            base = teclado.nextInt();
            if (base % 2 == 0) {
                System.out.println("La base debe ser un número impar. Inténtalo de nuevo.");
            }
        } while (base % 2 == 0);
        // Pedir carácter
        System.out.print("Introduce el carácter para dibujar el rombo: ");
        caracter = teclado.next().charAt(0);
        mitad = base / 2; // la mitad del rombo
        System.out.println("Rombo:");
        // Parte superior del rombo
        for (int i = 0; i <= mitad; i++) {
            // espacios
            for (int j = 0; j < mitad - i; j++) {
                System.out.print(" ");
            }
            // caracteres
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print(caracter);
            }
            System.out.println();
        }
        // Parte inferior del rombo
        for (int i = mitad - 1; i >= 0; i--) {
            // espacios
            for (int j = 0; j < mitad - i; j++) {
                System.out.print(" ");
            }
            // caracteres
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print(caracter);
            }
            System.out.println();
        }
        teclado.close();
    }
}