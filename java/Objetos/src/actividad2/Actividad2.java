package actividad2;

import java.util.*;

public class Actividad2 {
	private static Scanner teclado = new Scanner(System.in);
	public static void main(String[] args) {
		int opcion;
		double saldoInicial;
		System.out.println("Bienvenido al banco Bankez.");
		System.out.println("¿Cuantos Zenis dices tener?.");
		saldoInicial = teclado.nextDouble();
		Cuenta cuenta1 = new Cuenta(saldoInicial);
		System.out.println("Indica la operación que desea realizar (indica el número):");
		System.out.println("1. Retirar zeni.");
		System.out.println("2. Ingresar zeni.");
		System.out.println("3. consultar zenis y numero de operaciones.");
		System.out.println("4. Finalizar.");
		opcion = teclado.nextInt();
		switch (opcion) {
		case 1:
			System.out.println("¿Cuánto Zenis deseas retirar?");
		    double cantidadRetirar = teclado.nextDouble();
		    if (cuenta1.retirar(cantidadRetirar)) {
		        System.out.println("Recoja sus Zeni");
		    } else {
		        System.out.println("No tienes suficientes Zenis o la cantidad es inválida.");
		    }
		    break;
		case 2:
			System.out.println("¿Cuántos Zenis deseas ingresar?");
		    double cantidadIngresar = teclado.nextDouble();
		    cuenta1.ingresar(cantidadIngresar);
		    System.out.println("Ingreso realizado correctamente.");
		    break;
		case 3:
			System.out.println("Tienes " + cuenta1.getSaldo() + " Zenis. Has hecho " + cuenta1.getContadorIngresos() + " Ingresos, " + cuenta1.getContadorRetiradas() + " Retiradas y " + cuenta1.getContador() + " Operaciones");
			break;
		case 4:
			
	}
	}
}
