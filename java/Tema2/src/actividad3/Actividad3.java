package actividad3;

import java.util.Scanner;

public class Actividad3 {
	private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int num, num1, cifras;//creo variables para los numeros enteros
        cifras = 0;//Inicio el contador en 0
        System.out.print("Introduce un numero: ");//pido un numero
        num1 = sc.nextInt();
        num=num1;//guardo el valor de num para mostrarlo al final del ejercicio.
        if (num == 0) {
        	cifras = 1;
        } while(num!=0) {
        		num= num/10;
        		cifras++;
        	}
        	System.out.println("el numero " + num1 + " tiene " + cifras + " cifras");
        }
    
    }