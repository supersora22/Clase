package simulacro;

//creamos una clase publica para nuestra excepcion y le ponemos "extends Exception" para que herede de las excepciones oficiales de Java
public class NotaInvalidaException extends Exception {
 public NotaInvalidaException(String mensaje) { // creamos el constructor que recibe un String llamado mensaje, que sera el texto del error que queremos mostrar.
     super(mensaje); // usamos super para mandarle nuestro mensaje a la clase padre (Exception) y que ella se encargue de procesarlo y guardarlo
 }
}