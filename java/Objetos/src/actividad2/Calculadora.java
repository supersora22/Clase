package actividad2;

import java.util.*;

public class Calculadora {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        
        int a, b;
        char op;

        a = leerEntero(teclado, "IntroduzKa el primer número entero:");

        op = leerOperacion(teclado);

        do {
            b = leerEntero(teclado, "IntroduzKa el segundo número entero:");
            
            if (op == '/' && b == 0) {
                System.out.println("Error: No se puede dividir por cero. Introduce otro número.");
            }
        } while (op == '/' && b == 0);

        double resultado = calcular(a, b, op);
        System.out.println("--------------------------------");
        System.out.println("El resultado es: " + resultado);
        
        teclado.close();
    }

    static int leerEntero(Scanner teclado, String mensaje) {
        int numero = 0;
        boolean valido = false;

        while (!valido) {
            System.out.println(mensaje);
            try {
                numero = teclado.nextInt();
                valido = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Eso no es un número entero válido.");
                teclado.nextLine();
            }
        }
        return numero;
    }

    static char leerOperacion(Scanner teclado) {
        char op = ' ';
        boolean valida = false;

        while (!valida) {
            System.out.println("IntroduzKa la operación (+, -, *, /):");
            String entrada = teclado.next();
            op = entrada.charAt(0);

            if (op == '+' || op == '-' || op == '*' || op == '/') {
                valida = true;
            } else {
                System.out.println("Operación desconocida. Intentalo de nuevo.");
            }
        }
        return op;
    }

    static double calcular(int a, int b, char op) {
        double res = 0;
        switch (op) {
            case '+': res = a + b; break;
            case '-': res = a - b; break;
            case '*': res = a * b; break;
            case '/': res = (double) a / b; break;
        }
        return res;
    }
}
