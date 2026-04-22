package examen_tema5;

public abstract class Pokemon implements Entrenable {
	private int numeroPokedex;
	private String nombre;
	private Tipo tipo;
	private int nivelActual;
	private Movimiento[] movimientos;
	private int[][] aprendizaje;
	
	public Pokemon (int numeroPokedex, String nombre, Tipo tipo, int nivelActual) {
		this.numeroPokedex = numeroPokedex;
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivelActual = nivelActual;
        this.aprendizaje = aprendizaje;
        this.movimientos = movimientos;
	}
    public int getNumeroPokedex() {
        return numeroPokedex;
    }
    public String getNombre() {
        return nombre;
    }
    public Tipo getTipo() {
        return tipo;
    }
    public int getNivelActual() {
        return nivelActual;
    }
    public Movimiento[] getMovimientos() {
        return movimientos;
    }
    public int[][] getAprendizaje() {
        return aprendizaje;
    }
    public int contarMovimientosDisponibles() {
   	 int total = 0; // creamos una variable a 0 para ir acumulando los movimientos
        for (int i = 0; i < movimientos.length; i++) {
        	for (int j = 0; nivelActual < aprendizaje[i].length; j++ ) {
        	total += j;
        	}
   }
        return total;
   }
    public double calcularPotenciaMediaDisponible() {
    		int movDisponible = contarMovimientosDisponibles();
    }
    public abstract double calcularIndiceCombate();
    
    @Override
    public String toString() {
        return "Pokedex: " + numeroPokedex + ", Nombre: " + nombre + ", Tipo: " + tipo + ", Nivel: " + nivelActual;
    }
}


