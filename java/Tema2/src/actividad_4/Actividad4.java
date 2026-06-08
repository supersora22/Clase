package actividad_4;

import java.util.*;

public class Actividad4 {
	private static Scanner teclado = new Scanner(System.in);
	public static void main(String[] args) {
        System.out.print("Introduce frase: ");
        String frase = teclado.nextLine();

        System.out.print("Palabra escondida: ");
        String palabra = teclado.nextLine();

        if (estaEscondida(frase, palabra)) {
            System.out.println("Encontrada");
        } else {
            System.out.println("No se encuentra");
        }
        teclado.close();
    }
    // Comprueba si la palabra aparece en orden dentro del texto
    private static boolean estaEscondida(String texto, String palabra) {

        int j = 0; // índice para la palabra

        for (int i = 0; i < texto.length() && j < palabra.length(); i++) {
            if (texto.charAt(i) == palabra.charAt(j)) {
                j++; // avanza si coincide la letra
            }
        }
        // Si j llegó al final, encontró todas las letras en orden
        return j == palabra.length();
    }
	}
