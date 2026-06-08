package actividad8;

import java.util.Scanner;

public class Actividad8 {
	public static void main(String[] args) {
	Scanner teclado = new Scanner(System.in);
    int ancho, alto;
    char caracter;
    // Pedir ancho y alto
    System.out.print("Introduce el ancho del rectángulo: ");
    ancho = teclado.nextInt();
    System.out.print("Introduce el alto del rectángulo: ");
    alto = teclado.nextInt();
    // Limpiar el buffer y pedir carácter
    System.out.print("Introduce el carácter con el que se dibujará: ");
    caracter = teclado.next().charAt(0); // tomar solo el primer carácter
    System.out.println("\nRectángulo:\n");
    // Bucle para pintar el rectángulo
    for (int i = 1; i <= alto; i++) { // filas
        for (int j = 1; j <= ancho; j++) { // columnas
            System.out.print(caracter);
        }
        System.out.println(); // salto de línea al final de cada fila
    }
    teclado.close();
	}
}
