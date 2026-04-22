package array3;

import java.util.*;

public class Array3 {
    Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {
    	int[] numeros = {4, -3, 7, 0, -2, 8};
        int contadorPositivos = 0;

        for (int numero : numeros) {
            // Condición: mayor que 0
            if (numero > 0) {
                contadorPositivos++;
            }
        }
        System.out.println("Cantidad de números positivos: " + contadorPositivos);
    }
}
