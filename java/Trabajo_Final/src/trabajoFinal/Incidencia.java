package trabajoFinal;

public class Incidencia {
    // Atributos de la incidencia
    private String desc;
    private String tipo;
    private String nombreEquipo;
    private String nicknameJugador;
 
    // Constructor completo con toUpperCase
    public Incidencia(String desc, String tipo, String nombreEquipo, String nicknameJugador) {
        this.desc = desc;
        this.tipo = tipo.toUpperCase();
        this.nombreEquipo = nombreEquipo.toUpperCase();
        this.nicknameJugador = nicknameJugador.toUpperCase();
    }
 
    // Constructor simplificado para errores internos del sistema
    public Incidencia(String desc, String tipo) {
        this(desc, tipo, "SISTEMA", "SISTEMA");
    }
 
    // Metodos Getter
    public String getTipo() { 
    	return tipo; 
    	}
    public String getNombreEquipo() { 
    	return nombreEquipo; 
    	}
    public String getNicknameJugador() { 
    	return nicknameJugador; 
    	}
 
    // Formato de salida para mostrar la incidencia
    @Override
    public String toString() {
        return "INCIDENCIA [" + tipo + "] | EQUIPO: " + nombreEquipo + " | JUGADOR: " + nicknameJugador + " | " + desc;
    }
}