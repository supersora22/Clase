package actividad_2;

import java.util.*;

public class Actividad_2 {
	private static Scanner teclado = new Scanner(System.in);
	public static void main(String[] args) {
	int mayus = 0;
	int minus = 0;
	int numeros = 0;
	String cadena;
	
    System.out.print("Introduce una cadena de caracteres: ");
    cadena = teclado.nextLine();
    cantidad (cadena, mayus, minus, numeros);

    teclado.close();
	}
	private static void cantidad (String cadena, int mayus, int minus, int numeros) {
	    for (int i = 0; i < cadena.length(); i++) {
	        char c = cadena.charAt(i);
	        if (Character.isUpperCase(c)) {
	            mayus++;
	        } else if (Character.isLowerCase(c)) {
	            minus++;
	        } else if (Character.isDigit(c)) {
	            numeros++;
	        }
	    }
        System.out.println("Mayúsculas: " + mayus);
        System.out.println("Minúsculas: " + minus);
        System.out.println("Numéricos: " + numeros);
	}
}