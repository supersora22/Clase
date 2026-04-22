package examen_string;

import java.util.*;

public class Rodriguez_examen_String {
	private static Scanner teclado = new Scanner(System.in);
	public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) { // hacemos un bucle que pida 1 frase 5 veces y usamos i para decirle al usuario el numero de la frase que esta introduciendo.
            System.out.print("Introduce la frase " + i + ": ");
            String frase = teclado.nextLine();
            
            String espiteado = convertir(frase); //llamamos al metodo convertir para usar lo que returnea como texto resultado.
            System.out.println("Resultado: " + espiteado); // mostramos el resultado.
            System.out.println(" "); // dejamos un espacio vacio debajo del resultado para que no este todo pegado.
        }
        teclado.close();
    }
    private static String convertir(String texto) {
        String resultado = ""; // creamos una string vacia pra ir almacenando las letras.
        
        for (int i = 0; i < texto.length(); i++) { // creamos un bucle que recorra la cadena.
            char c = texto.charAt(i); // guardamos la posicion en la cadena en el momento como un caracter c.
            resultado += c; // Añadimos la letra sin añadir Pi
            // Si es una vocal 'e' mayuscula o minuscula, añadimos "Pi"
            if (esVocal(c)) {
                resultado += "Pi";
            }
        }
        return resultado;
    }
    private static boolean esVocal(char c) {
        // Comprobamos mayúsculas y minúsculas en las vocales sin la e.
        if (c == 'a' || c == 'A' || c == 'i' || c == 'I' || c == 'o' || c == 'O' ||  c == 'u' || c == 'U') {
            return true; // returneamos true para que si el valor del espacio en la cadena no es una vocal e, sustituirla
        } else {
        return false; // en caso de que sea una e escribimos letra normal (no entra en el if esVocal)
        }
    }
}