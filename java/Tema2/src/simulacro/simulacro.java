package simulacro;

import java.util.Scanner;

public class simulacro {
    private static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        int fila, contador, contadorln, contadoresp, multiplo;
        contador = 1;
        multiplo = 2;
        //Pedir cantidad de filas
        do {
            System.out.print("Cantidad de filas a representar: ");
            fila = teclado.nextInt();
        } while (fila<=0);

        //controla las filas
        do {
            contadorln = 1;
            contadoresp = fila-contador; //espacio
            //espacios
            do {
                System.out.print(" "); //espacios
                contadoresp--;
            } while (contadoresp>0);
            //múltiplos de 2
            do {
                System.out.print(multiplo + "  ");
                multiplo += 2;
                contadorln++;
            } while (contadorln<=contador);
            System.out.println(); // salto de línea al terminar cada fila
            contador++;
        } while (contador<=fila);
    }
}