package actividad_6;

import java.util.*;

public class Actividad6 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String frase;
        int cantidadVocales;

        System.out.print("Introduce una palabra o frase: ");
        frase = teclado.nextLine();

        cantidadVocales = contarVocales(frase);
        System.out.println("Vocales diferentes: " + cantidadVocales);
        teclado.close();
    }

    private static int contarVocales(String texto) {
        texto = texto.toLowerCase(); // Todo a minusculas para facilitarlo.

        boolean tieneA = contieneVocal(texto, 'a');
        boolean tieneE = contieneVocal(texto, 'e');
        boolean tieneI = contieneVocal(texto, 'i');
        boolean tieneO = contieneVocal(texto, 'o');
        boolean tieneU = contieneVocal(texto, 'u');

        int contador = 0;
        if (tieneA) {
        	contador++;
        }
        if (tieneE)  {
        	contador++;
        }
        if (tieneI) {
        	contador++;
        }
        if (tieneO) {
        	contador++;
        }
        if (tieneU) {
        	contador++;
        }
        return contador;
    }
    private static boolean contieneVocal(String texto, char vocal) {
        // Recorre el texto buscando la vocal
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == vocal) {
                return true;
            }
        }
        return false;
    }
}
