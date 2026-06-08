package actividad5;

import java.util.*;

public class actividad5 {
    public static void main(String[] args) {
        try (Scanner teclado = new Scanner(System.in)) {
			int combinacion, intento, maxIntentos, i; // combinación fija de 4 cifras
			maxIntentos=5;
			combinacion=2252;
			System.out.println("Codigo de la caja fuerte");
			System.out.println("Tienes " + maxIntentos + " intentos para acertar la combinación (4 cifras).");
			// Bucle de intentos
			for (i = 1; i <= maxIntentos; i++) {
			    System.out.print("\nIntroduce la combinación (intento " + i + "): ");
			    intento=teclado.nextInt();
			    if (intento == combinacion) {
			        System.out.println("La caja fuerte se ha abierto satisfactoriamente");
			        break; // se sale del bucle si acierta
			    } else {
			        System.out.println("Lo siento, esa no es la combinación");
			        System.out.println("Intentos restantes: " + (maxIntentos - i));
			    }
			}
			// Si no acerta
			if (i>maxIntentos) {
			    System.out.println("Se han agotado los " + maxIntentos + " intentos.");
			}
			teclado.close();
		}
    }
}