package examen_tema5;

public class PokemonDefensivo extends Pokemon {
	private int resistencia;
	
	public class PokemonDefensivo (int numeroPokedex, String nombre, Tipo tipo, int nivelActual, int resistencia) {
		this.ataquesFuertes = resistencia;
		}

	@Override
	public boolean necesitaMejorar() {
		if (contarMovimientosDisponibles() < 0 || nivelActual() < 20)
		return true;
	}
	@Override
	public double calcularIndiceCombate(double indice) {
		indice = calcularPotenciaMediaDisponible() + resistencia * 1.5;
		return indice;
	}
}