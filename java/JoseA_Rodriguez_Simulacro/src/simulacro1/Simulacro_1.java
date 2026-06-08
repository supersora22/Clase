package simulacro1;

import java.util.*;

public class Simulacro_1 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String frase = leerFrase(teclado);
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = leerOpcion(teclado);
            switch (opcion) {
                case 1:
                    System.out.println("Número de espacios: " + contarEspacios(frase));
                    break;
                case 2:
                    System.out.println("Número de palabras: " + contarPalabras(frase));
                    break;
                case 3:
                    System.out.println("Mayúsculas en última letra: " + mayusculas(frase));
                    break;
                case 4:
                    System.out.println("Frase invertida: " + invertirPalabras(frase));
                    break;

                case 5:
                    System.out.println("Nos vemos.");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
            if (continuar) {
                System.out.print("¿Desea hacer algo más? (Si/No): ");
                String respuesta = teclado.nextLine();
                if (!respuesta.equalsIgnoreCase("Si")) {
                    System.out.println("Nos vemos.");
                    continuar = false;
                }
            }
        }
    }
    private static String leerFrase(Scanner teclado) {
        System.out.print("Introduzca una frase: ");
        return teclado.nextLine();
    }
    private static void mostrarMenu() {
        System.out.println("Menú:");
        System.out.println("1. Contar espacios");
        System.out.println("2. Contar palabras");
        System.out.println("3. Mayúsculas en última letra");
        System.out.println("4. Invertir");
        System.out.println("5. Salir");
    }
    private static int leerOpcion(Scanner teclado) {
        boolean correcto=false;
        int opcion = 0;
        while (!correcto) {
            System.out.print("Seleccione una opción: ");
            String entrada = teclado.nextLine();
            if (entrada.equals("")) {
                System.out.println("Debes escribir un número.");
            } else {
                // comprobar que es válido
                boolean numValido = true;
                for (int i = 0; i < entrada.length(); i++) {
                    char c = entrada.charAt(i);
                    if (c < '0' || c > '5') {
                        numValido = false;
                        break;
                    }
                }
                if (!numValido) {
                    System.out.println("Eso no es un número válido.");
                } else {
                    opcion = Integer.parseInt(entrada);
                    correcto = true;   //salimos del while
                }
            }
        }
        return opcion;
    }
    private static int contarEspacios(String frase) {
        int cont = 0;
        for (int i = 0; i < frase.length(); i++) {
            if (frase.charAt(i) == ' ') cont++;
        }
        return cont;
    }
    private static int contarPalabras(String frase) {
        int contadorp = 0;
        boolean dentro = false;
        for (int i = 0; i < frase.length(); i++) {
            char c = frase.charAt(i);
            if (c != ' ' && !dentro) {//si no es un espacio y dentro era false entonces suma el contador y estamos en la primera letra.
                contadorp++;//sumamos 1 palabra
                dentro = true; //actualizamos a estar dentro de la palabra
            } else if (c == ' ') { //si el caracter es un espacio
                dentro = false;//actuelizamos a que no estamos en una palabra
            }
        }
        return contadorp;
    }
    private static String mayusculas(String frase) {
        String resultado = "";
        boolean nuevaPalabra = true;
        for (int i = 0; i < frase.length(); i++) {
            char c = frase.charAt(i);
            if (c == ' ') {// si el caracter es un espacio dejamos el espacio tal cual y actualizamos a true por si la siguiente es una letra.
                resultado += c;       // añadimos el espacio tal cual
                nuevaPalabra = true;  // la próxima letra será inicio de palabra o otro espacio
            } else {
                if (nuevaPalabra) { //si el booleano es true haremos que la siguiente letra se pase a mayusculas.
                    resultado += Character.toUpperCase(c);
                    nuevaPalabra = false; //despues de pasar la primera letra a mayusculas volvemos a iniciar el bucle con la nueva palabra en falso
                } else {
                    resultado += Character.toLowerCase(c);//si no es un espacio y no es la primera, entonces es una minuscula.
                }
            }
        }
	    return resultado;
    }
    private static String invertirPalabras(String frase) {
        String resultado = "";
        int fin = frase.length() - 1;
        while (fin >= 0) {
            while (fin >= 0 && frase.charAt(fin) == ' ') {
                fin--;
            }
            if (fin < 0) break;
            int inicio = fin;
            while (inicio >= 0 && frase.charAt(inicio) != ' ') {
                inicio--;
            }
            String palabra = "";
            for (int i = inicio + 1; i <= fin; i++) {
                palabra = palabra + frase.charAt(i);
            }
            if (resultado.equals("")) {
                resultado = palabra;
            } else {
                resultado = resultado + " " + palabra;
            }
            fin = inicio - 1;
        }
        return resultado;
    }
}