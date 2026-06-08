package actividad9;

public class Actividad9 {
	public static void main(String[] args) {
        // Bucle para cada tabla (del 0 al 10)
        for (int i = 0; i <= 10; i++) {
            System.out.println("Tabla del " + i + ":");

            // Bucle para multiplicar por los números del 0 al 10
            for (int j = 0; j <= 10; j++) {
                System.out.println(i + " x " + j + " = " + (i * j));
            }
            System.out.println(); // línea en blanco entre tablas
        }
	}
}
