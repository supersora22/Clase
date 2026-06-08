package actividad_5;

import java.util.*;

public class Actividad5 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String textoOriginal = pedirTexto(teclado, "Introduce un texto: ");
        String textoBuscar = pedirTexto(teclado, "Introduce el texto a buscar: ");
        String textoReemplazo = pedirTexto(teclado, "Introduce el texto a reemplazar: ");
        String textoModificado = reemplazarPalabra(textoOriginal, textoBuscar, textoReemplazo);
        mostrarResultado("El texto modificado es:", textoModificado);
    }
    private static String pedirTexto(Scanner teclado, String mensaje) {
        System.out.print(mensaje);
        return teclado.nextLine();
    }
    // Método para reemplazar
    private static String reemplazarPalabra(String texto, String buscar, String reemplazo) {
        String resultado = "";
        int i = 0;
        int largoBuscar = buscar.length();
        while (i < texto.length()) {
            boolean coincide = true;
            // Comprobamos carácter por carácter si coincide la palabra
            for (int j = 0; j < largoBuscar; j++) {
                if (i + j >= texto.length() || texto.charAt(i + j) != buscar.charAt(j)) {
                    coincide = false;
                    break;
                }
            }
            if (coincide) {
                resultado += reemplazo;
                i += largoBuscar;
            } else {
                resultado += texto.charAt(i);
                i++;
            }
        }
        return resultado;
    }
    // Método para mostrar el resultado
    private static void mostrarResultado(String mensaje, String texto) {
        System.out.println(mensaje);
        System.out.println(texto);
    }
}
