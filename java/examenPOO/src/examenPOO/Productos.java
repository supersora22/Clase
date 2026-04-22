package examenPOO;

public class Productos implements Comparable<Productos> {
	 private String nombre;
	 private int stock;
	 private double precio;

	 public Productos() {
	     this.nombre = "Desconocido"; // usamos this para referirnos al atributo de este objeto
	     this.stock = 0;
	     this.precio = 0.0;
	 }
	 public Productos(String nombre, int stock, double precio) throws ProductoInvalidoException { //preparamos la excepcion
	     this.nombre = nombre;
	     setStock(stock); // llamamos al setter para que valide el stock antes de guardarlo
	     setPrecio(precio);
	 	}
	 public String getNombre() {
	     return this.nombre; // returneamos el nombre
	 }

	 public void setNombre(String nombre) {
	     this.nombre = nombre; // guardamos el nombre nuevo
	 }

	 public int getStock() {
	     return this.stock; // returneamos el stock
	 }

	 // hacemos un setter que compruebe que la el stock es mayor a 0
	 public void setStock(int stock) throws ProductoInvalidoException {
	     if (stock < 0) { // comprobamos si el stock es imposible
	         throw new ProductoInvalidoException("Tanto el Stock como el precio deben ser mayores a 0, Javi."); // lanzamos el error
	     }
	     this.stock = stock; // si pasa el if, guardamos la edad
	 }

	 public double getPrecio() {
	     return this.precio; // returneamos el precio
	 }
	 public void setPrecio(double precio) throws ProductoInvalidoException {
	     if (precio <= 0) { // comprobamos si el precio es menor que 0
	         throw new ProductoInvalidoException("No regalamos nada, fush Javi."); // lanzamos la excepcion
	     }
	     this.precio = precio; // si es correcto, lo guardamos
	 }
	 @Override
	 public int compareTo(Productos otro) {
	     if (this.precio < otro.precio) { // comprobamos si nuestro precio es menor que el del otro objeto
	         return -1; // returneamos -1 para decir que vamos ANTES
	     } else if (this.precio > otro.precio) { // comprobamos si nuestro precio es mayor
	         return 1; // returneamos 1 para decir que vamos DESPUES
	     } else {
	         return 0; // si no entra en los if, son iguales y returneamos 0
	     }
	 }

	 @Override
	 public String toString() {
	     return nombre + " | Stock: " + stock + " | Precio: " + precio; // returneamos la frase montada con los datos
	 }
}