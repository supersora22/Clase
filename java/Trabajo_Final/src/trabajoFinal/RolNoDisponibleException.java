package trabajoFinal;

public class RolNoDisponibleException extends Exception {
    public RolNoDisponibleException(String msj) {
        super(msj); // Le paso el mensaje de error al padre
    }
}