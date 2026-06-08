package actividad2;

import java.util.Scanner;

public class Actividad2{
	private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int num, exp, res;//creo variables para los numeros enteros
        num= 0;
        res = 0;//inicializo la variable resultado en 0
        do {//pide el numero y el exponente
        num = numero(num);
        exp = numero(num);
        
        } while (num<0 || exp<0); //mientras se cumpla que numero y exponente son negativos se repite el bucle
        	res = (int) Math.pow(num,exp);
            System.out.print("El resultado es:" + res);   
        }
    private static int numero (int num) {
    	System.out.print("Introduce un numero: ");
        num = sc.nextInt();
        return num;
    }
    }