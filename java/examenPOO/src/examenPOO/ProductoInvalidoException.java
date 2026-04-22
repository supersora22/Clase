package examenPOO;

public class ProductoInvalidoException extends Exception {
 
		 // creamos el constructor que recibe el texto del error
		 public ProductoInvalidoException(String mensaje) {
		     super(mensaje); // le mandamos el mensaje a la clase padre para que lo gestione
		 }
		}