package examen_tema5;

public class PokemonOfensivo extends Pokemon {
	private int ataquesFuertes;
	
	public class PokemonOfensivo (int numeroPokedex, String nombre, Tipo tipo, int nivelActual, int ataquesFuertes) {
		this.ataquesFuertes = contarMovimientosDisponibles()>70;
		}

	@Override
	public boolean necesitaMejorar() {
		if (contarMovimientosDisponibles() < 2 || calcularPotenciaMediaDisponible() < 50)
		return true;
	}
	@Override
	public double calcularIndiceCombate(double indice) {
		indice = calcularPotenciaMediaDisponible() + ataquesFuertes * 2;
		return indice;
	}
}