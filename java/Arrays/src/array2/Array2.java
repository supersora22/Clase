package array2;

import java.util.*;

public class Array2 {
	    Scanner teclado = new Scanner(System.in);
	    public static void main(String[] args) {
	        double[] numeros = {10.5, 5.2, 8.0, 3.75, 12.1, 6.4};

	        double sumaTotal = 0;

	        for (double num : numeros) {
	            sumaTotal += num;
	        }

	        double media = sumaTotal / numeros.length;

	        System.out.println("Array de números procesado.");
	        System.out.println("Suma total: " + sumaTotal);
	        System.out.printf("Media: %.2f%n", media);
	    }
}
