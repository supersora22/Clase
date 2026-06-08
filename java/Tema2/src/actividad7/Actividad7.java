package actividad7;

import java.util.Scanner;

public class Actividad7 {
	public static void main(String[] args) {
	Scanner teclado = new Scanner(System.in);
    int num1, num2, a, b, resto;
    // Leer dos números mayores que 0
    do {
        System.out.print("Introduce el primer número (mayor que 0): ");
        num1 = teclado.nextInt();
        System.out.print("Introduce el segundo número (mayor que 0): ");
        num2 = teclado.nextInt();
        if (num1 <= 0 || num2 <= 0) {
            System.out.println("Ambos números deben ser mayores que 0. Inténtalo de nuevo.");
        }
    } while (num1 <= 0 || num2 <= 0);
    // Inicializamos a y b
    a = num1;
    b = num2;
    // Aplicamos el algoritmo
    while (b != 0) {
        resto = a % b;  // calcular el resto
        a = b;          // el divisor pasa a ser el nuevo dividendo
        b = resto;      // el resto pasa a ser el nuevo divisor
    }
    // Mostrar resultado
    System.out.println("El máximo común divisor de " + num1 + " y " + num2 + " es: " + a);

    teclado.close();
	}
}

