package trabajoFinal;

public class Jugador extends PersonaLiga implements Entrenable {
    // Atributos especificos de jugador
    private String rol;
    private PokemonMain pokemon;
    private int nivelMecanico;
    private int nivelEstrategico;
    private int partidasJugadas;
    private int mvpTotales;
    private boolean sancionado;
 
    // Constructor con valores por defecto
    public Jugador(String id, String nombre, String nickname, int edad, double salario, String rol, PokemonMain pokemon) {
        super(id, nombre, nickname, edad, salario);
        this.rol = rol.toUpperCase();
        this.pokemon = pokemon;
        this.nivelMecanico = 50;
        this.nivelEstrategico = 50;
        this.partidasJugadas = 0;
        this.mvpTotales = 0;
        this.sancionado = false;
    }
 
    // Getters y Setters
    public String getRol() { 
    	return rol; 
    	}
    public void setRol(String rol) { 
    	this.rol = rol.toUpperCase(); 
    	}
    public PokemonMain getPokemon() { 
    	return pokemon; 
    	}
    public int getNivelMecanico() { 
    	return nivelMecanico; 
    	}
    public int getNivelEstrategico() { 
    	return nivelEstrategico; 
    	}
    public int getPartidasJugadas() { 
    	return partidasJugadas; 
    	}
    public int getMvpTotales() { 
    	return mvpTotales; 
    	}
    public boolean isSancionado() { 
    	return sancionado; 
    	}
    public void setSancionado(boolean sancionado) { 
    	this.sancionado = sancionado; 
    	}
 
    // Metodos para sumar estadisticas
    public void sumarMVP() { 
    	this.mvpTotales++; 
    	}
    public void sumarPartidoJugado() { 
    	this.partidasJugadas++; 
    	}
 
    // Implementacion de calcularCosteMensual
    @Override
    public double calcularCosteMensual() {
        return this.salarioBase + (this.mvpTotales * 10);
    }
 
    // Metodo para subir niveles
    @Override
    public void entrenar() {
        this.nivelMecanico += 5;
        this.nivelEstrategico += 2;
        System.out.println(this.nickname + " HA ENTRENADO. MECANICO: " + nivelMecanico + " | ESTRATEGICO: " + nivelEstrategico);
    }
 
    // Metodo para calcular rendimiento
    @Override
    public double calcularRendimiento() {
        return (this.nivelMecanico + this.nivelEstrategico + this.mvpTotales) / 3.0;
    }
 
    // toString con formato de estado y rendimiento
    @Override
    public String toString() {
        String estado = sancionado ? " [SANCIONADO]" : " [ACTIVO]";
        return super.toString() + estado
                + " | ROL: " + rol
                + " | POKEMON: " + pokemon
                + " | PJ: " + partidasJugadas
                + " | MVP: " + mvpTotales
                + " | RENDIMIENTO: " + String.format("%.1f", calcularRendimiento());
    }
}
 