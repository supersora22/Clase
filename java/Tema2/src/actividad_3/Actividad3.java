package actividad_3;

import java.util.*;

public class Actividad3 {
	private static Scanner teclado = new Scanner(System.in);
	public static void main(String[] args) {
        System.out.print("Introduce una palabra o frase: ");
        String cadena = teclado.nextLine();

        String limpio = limpiarCadena(cadena);

        if (esPalindromo(limpio)) {
            System.out.println("Es un palíndromo.");
        } else {
            System.out.println("No es un palíndromo.");
        }

        teclado.close();
    }
	    private static String limpiarCadena(String texto) {
	        return texto.replaceAll(" ", "").toLowerCase();
	    }

	    private static boolean esPalindromo(String texto) {
	        int inicio = 0;
	        int fin = texto.length() - 1;

	        while (inicio < fin) {
	            if (texto.charAt(inicio) != texto.charAt(fin)) {
	                return false;
	            }
	            inicio++;
	            fin--;
	        }
	        return true;
	    }
}

