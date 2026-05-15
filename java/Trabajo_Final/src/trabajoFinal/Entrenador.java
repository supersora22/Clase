package trabajoFinal;

public class Entrenador extends PersonaLiga {
    // Atributos especificos del entrenador
    private int experiencia;
    private String especialidad;
    private int victoriasTotales;
 
    // Constructor para inicializar el entrenador con su experiencia
    public Entrenador(String id, String nombre, String nickname, int edad, double salario, int experiencia, String especialidad) {
        super(id, nombre, nickname, edad, salario);
        this.experiencia = experiencia;
        this.especialidad = especialidad.toUpperCase();
        this.victoriasTotales = 0;
    }
 
    // Metodos Getter y Setter
    public int getExperiencia() { 
    	return experiencia; 
    	}
    public void setExperiencia(int experiencia) { 
    	this.experiencia = experiencia; 
    	}
    public String getEspecialidad() { 
    	return especialidad; 
    	}
    public void setEspecialidad(String especialidad) { 
    	this.especialidad = especialidad.toUpperCase(); 
    	}
    public int getVictorias() { 
    	return victoriasTotales; 
    	}
    
    // Metodo para incrementar el contador de victorias
    public void sumarVictoria() { 
    	this.victoriasTotales++; 
    	}
 
    // Implementacion de calcularCosteMensual con plus por experiencia
    @Override
    public double calcularCosteMensual() {
        return this.salarioBase + (experiencia * 50.0) + (victoriasTotales * 20.0);
    }
 
    // Metodo toString con los datos del entrenador
    @Override
    public String toString() {
        return super.toString() + " | ENTRENADOR - ESPECIALIDAD: " + especialidad + " | EXP: " + experiencia + " | VICTORIAS: " + victoriasTotales;
    }
}