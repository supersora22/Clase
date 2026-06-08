package ejercicio1;

import java.util.Scanner;

public class Ejercicio1 { 
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int primo, n, elevado, contador;
    do {
    System.out.println("Introduce el número (mayor que 0): ");
    n = teclado.nextInt();
    elevado=n*n*n*n;
        if (n<0) {
            System.out.println("El número de alumnos debe ser mayor que 0. Inténtalo de nuevo.");
        } if (n==0) {
        	System.out.print("Venga máquina, hasta luego");
        	} else if (n>0) {
        		break;
        }
    } while (n>=0); {
} for (int i=n;  i!=elevado; i++) {
	for (int j=1; j<=elevado; j++) {
		contador=0;
		while (contador<=2); {
(i%j==0);{
			}
		}
}
}
	teclado.close();
}
}