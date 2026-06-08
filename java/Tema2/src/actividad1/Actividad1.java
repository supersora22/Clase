package actividad1;

import java.util.Scanner;

public class Actividad1{
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        double num1, num2, num3, num;
         num=0;
         num1= pedirNumero(num);
         num2= pedirNumero(num);
         num3= pedirNumero(num);
         System.out.print("Los numeros ordenados de menor a mayor son: ");

         if (num1 <= num2 && num1 <= num3) { // num1 es el menor
             System.out.print(num1 + ", ");
             if (num2 <= num3) {
                 System.out.println(num2 + ", " + num3);
             } else {
                 System.out.println(num3 + ", " + num2);
             }
         } else if (num2 <= num1 && num2 <= num3) { // num2 es el menor
             System.out.print(num2 + ", ");
             if (num1 <= num3) {
                 System.out.println(num1 + ", " + num3);
             } else {
                 System.out.println(num3 + ", " + num1);
             }
         } else { // num3 es el menor
             System.out.print(num3 + ", ");
             if (num1 <= num2) {
                 System.out.println(num1 + ", " + num2);
             } else {
                 System.out.println(num2 + ", " + num1);
             }
         }
    }
    private static double pedirNumero(double num) {
    	System.out.print("introduce numero ");
        num = sc.nextDouble();
        return num;
    }
    }