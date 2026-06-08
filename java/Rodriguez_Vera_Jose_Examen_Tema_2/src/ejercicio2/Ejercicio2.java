package ejercicio2;

import java.util.Scanner;

public class Ejercicio2 {
	  public static Scanner teclado = new Scanner(System.in);
	    public static void main(String[] args) {
	        System.out.print("Dame un número que sea más que el 0: ");
	        int num, b500, b200, b100, b50, b20, b10, b5, m2, m1;
	        num = teclado.nextInt();
	        //Pedimos que el valor sea positivo, sino no se podra hacer.
	        if (num <= 0) {
	            System.out.print("Ese número es menor que 0,introduce un número mayor que 0: ");
	            num = teclado.nextInt();
	        }
	        //dividir el dinero que hemos puesto entre los billetes y voy mostrando la cantidad que necesito
	        b500 = num / 500;
	        num = num % 500; //actualizo el numero a la cantidad restante que es el resto (modulo)
	        System.out.println(b500 + " billete de 500");
	        //Mísmo proceso para todos los billete y monedas.
	        b200 = num / 200;
	        num = num % 200;
	        System.out.println(b200 + " billete de 200");               
	        b100 = num / 100;
	        num = num % 100;
	        System.out.println(b100 + " billete de 100");
	        b50 = num / 50;
	        num = num % 50;
	        System.out.println(b50 + " billete de 50");
	        b20 = num / 20;
	        num = num % 20;
	        System.out.println(b20 + " billete de 20");
	        b10 = num / 10;
	        num = num % 10;
	        System.out.println(b10 + " billete de 10");
	        b5 = num / 5;
	        num = num% 5;
	        System.out.println(b5 + " billete de 5");
	        m2 = num / 2;
	        num = num % 2;
	        System.out.println(m2 + " moneda de 2");
	        m1 = num;
	        num = num % 1;
	        System.out.println(m1 + " moneda de 1");
	    }
	}
