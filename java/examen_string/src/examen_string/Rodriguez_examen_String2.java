package examen_string;

import java.util.*;

public class Rodriguez_examen_String2 {
	private static Scanner teclado = new Scanner(System.in);
	public static void main(String[] args) {
        String frase = pedirFrase(); // llamo al metodo para  que introducir datos por teclado.
        String cani = convertirCani(frase); // llamo al metodo para convertir la frase.
        System.out.println("Resultado: " + cani);
    }
	// metodo para pedir una frase.
	private static String pedirFrase () {
		System.out.println("Introduce un texto");
		String cadena= teclado.nextLine();
		teclado.close();
		return cadena;
	}
    private static String convertirCani(String texto) {
        String resultado = "";
        boolean tocaMayuscula = true; // inicializamos mayúscula para que la primera siempre sea mayuscula.
        
        for (int i = 0; i < texto.length(); i++) { // creamos un bucle que recorra la cadena.
            char c = texto.charAt(i); // guardamos la posicion en la cadena en el momento como un caracter c.
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) { // comprobamos si es una letra y si no, no modifico nada
                if (tocaMayuscula) { // si el boolean esta actualmente en true, llamamos al metodo mayuscula y metemos la letra convertida al string
                    resultado += mayuscula(c);
                    tocaMayuscula = false; // la siguiente toca minúscula porque la siguiente ira directo al else de abajo.
                } else {
                    resultado += minuscula(c);
                    tocaMayuscula = true; // la siguiente toca mayúscula porque la siguiente si entra en el if tocaMayuscula..
                }
            } else {
                // Si no es letra, se deja igual y no modifica nada.
                resultado += c;
            }
        }
        return resultado;
    }
    // metodo para convertir a mayúscula
    private static char mayuscula(char c) {
        if (c >= 'a' && c <= 'z') { // si esta entre a minuscula y z minuscula la convertimos a mayuscula.
            return (char) (c - 32); //la misma letra en mayuscula y minuscula estan separadas en 32 en el codigo ASCII por eso restamos 32.
        } else {
        return c; // Si ya era mayúscula o se devuelve tal cual.
        }
    }
    // metodo para convertir a minúscula lo mismo que el metodo anterior pero sumando.
    private static char minuscula(char c) {
        if (c >= 'A' && c <= 'Z') {
            return (char) (c + 32);
        } else {
        return c;
        }
    }
}
