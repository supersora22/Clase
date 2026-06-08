package actividad4;

import java.util.*;

public class Actividad4 {
	private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
    	Random random = new Random();
        int num, fallos, secret;//creo variables para los numeros enteros
        fallos = 0;//Inicio el contador en 0
        secret = random.nextInt(100)+1;
        while (fallos<5) {
            System.out.println("Introduce un número: ");
            num = sc.nextInt();
            if (num<1 || num>100) {
                System.out.println("El número debe estar entre 1 y 100.");
            } else if (num==secret) {
                System.out.println("Correcto. El número secreto era " + secret + " tienes " +fallos + " fallos");
              break;
            } else if (num<secret) {
                System.out.println("El número secreto es MAYOR que " + num);
            } else {
                System.out.println("El número secreto es MENOR que " + num);
            }
            fallos++;
            System.out.println("Intentos restantes: " + (5 - fallos));
        }
        System.out.println("El número secreto era " + secret + ".");
    }
}