package actividad_7;

import java.util.*;

public class Actividad7 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String frase;
        String resultado;

        System.out.print("Introduce una frase: ");
        frase = teclado.nextLine();

        resultado = ordenar(frase);
        System.out.println("Resultado: " + resultado);
        teclado.close();
    }
    private static String ordenar(String texto) {
        texto = texto.toLowerCase();  // pasamos a minusculas
        String consonantes = "";
        String vocales = "";
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);

            if (c != ' ') { // solo seguimos si NO es un espacio
                if (esVocal(c)) {
                    vocales += c;
                } else {
                    consonantes += c;
                }
            }
        }
        return consonantes + vocales;
    }
    private static boolean esVocal(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}