package examenPOO;

import java.util.*;

public class Main {
    private static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
    	Productos[] producto = new Productos[5];
    	
        for (int i = 0; i < producto.length; i++) { // hacemos un bucle que de 5 vueltas para rellenar el array
            System.out.println("Introduzca valores del producto " + (i + 1) + ":"); // usamos i+1 para decirle al usuario el numero de producto
            System.out.print("Nombre: ");
            String nombre = teclado.nextLine(); // guardamos lo que escribe el usuario como el nombre

            boolean valido = false; // creamos un boolean en falso para controlar el bucle de pedir el precio y el Stock
            while (!valido) { // mientras valido no sea true, el bucle seguira pidiendo la nota sin parar
                try {
                    System.out.print("Precio: ");
                    double precio = Double.parseDouble(teclado.nextLine());
                    System.out.print("Stock: ");
                    int stock = Integer.parseInt(teclado.nextLine());// leemos el texto y lo convertimos a double
                    producto[i] = new Productos(nombre, stock, precio); // creamos el objeto productos y lo metemos en su posicion del array
                    valido = true; // si llegamos aqui es que no ha saltado ningun error, cambiamos a true para salir del while      
                } catch (ProductoInvalidoException e) {
                    System.out.println(e.getMessage());
                }
            }
         }
        System.out.println("----------------------------------------");
        System.out.println("Lista Productos");
        System.out.println("----------------------------------------");
        for (Productos a : producto) { // recorremos el array de productos entero (for each)
            System.out.println(a); // mostramos los datos de cada producto
        }
        Arrays.sort(producto); // ordenamos el array de menor a mayor Precio gracias al compareTo que hicimos
        System.out.println("----------------------------------------");
        System.out.println("El producto más caro es: " + producto[producto.length - 1]); // como estan ordenados de menor a mayor, el ultimo es el mas caro.
        System.out.println("----------------------------------------");
    for (Productos a : producto) { // volvemos a recorrer el array para restar 2
        try {
            int nuevoStock = a.getStock() - 2; // pillamos la nota que tiene y le sumamos 1
            if (nuevoStock < 0) nuevoStock = 0; // si al restar se pasa de 0 lo establezco a 0
            a.setStock(nuevoStock); // guardamos el Stock nuevo llamando al metodo
        } catch (ProductoInvalidoException e) {
            System.out.println("Error restando el stock: " + e.getMessage()); // capturamos el error por si acaso el setStock fallase
        	}
    	}
    System.out.println("----------------------------------------");
    System.out.println("Lista Final (-2 del Stock)");
    System.out.println("----------------------------------------");
    for (Productos a : producto) System.out.println(a); // mostramos la lista definitiva ya actualizada
    }
}
