package examen_tema5;

public class Principal {
	public static void main(String[] args) {
        Pokemon[] Equipo = new Pokemon[4]; // hacemos un array de 4 huecos para meter a los empleados

        Equipo[0] = new PokemonOfensivo("20", "cindaquill","Fuego","10", "Llamarada");
        Equipo[1] = new PokemonOfensivo("20", "smoliv","Fuego","10", "Llamarada");
        Equipo[2] = new PokemonDefensivo("20", "treeko","Fuego","10", "40");
        Equipo[3] = new PokemonDefensivo("20", "stunfish","Fuego","10", "40");
	}
}
