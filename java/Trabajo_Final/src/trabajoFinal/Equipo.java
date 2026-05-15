package trabajoFinal;

import java.util.ArrayList;

public class Equipo {
    // Atributos de informacion del equipo
    private String nombre;
    private String ciudad;
    private Entrenador entrenador;
    private double presupuesto;
    private int victorias;
    private int derrotas;
    private int puntosFavor;
    private int puntosContra;
 
    // Estructuras para la gestion de la plantilla
    private Jugador[] titulares = new Jugador[5];
    private int contadorTitulares = 0;
    private ArrayList<Jugador> suplentes = new ArrayList<>();
 
    // Constructor para inicializar el equipo
    public Equipo(String nombre, String ciudad, double presupuesto) {
        this.nombre = nombre.toUpperCase();
        this.ciudad = ciudad.toUpperCase();
        this.presupuesto = presupuesto;
        this.victorias = 0;
        this.derrotas = 0;
        this.puntosFavor = 0;
        this.puntosContra = 0;
    }
 
    // --- GETTERS Y SETTERS ---
    public String getNombre() { 
    	return nombre; 
    	}
    public String getCiudad() { 
    	return ciudad; 
    	}
    public Entrenador getEntrenador() { 
    	return entrenador; 
    	}
    public void setEntrenador(Entrenador entrenador) { 
    	this.entrenador = entrenador; 
    	}
    public double getPresupuesto() { 
    	return presupuesto; 
    	}
    public int getVictorias() { 
    	return victorias; 
    	}
    public int getDerrotas() { 
    	return derrotas; 
    	}
    public int getPuntosFavor() { 
    	return puntosFavor; 
    	}
    public int getPuntosContra() { 
    	return puntosContra; 
    	}
    public int getContadorTitulares() { 
    	return contadorTitulares; 
    	}
    public Jugador getTitular(int i) { 
    	return titulares[i]; 
    	}
    public ArrayList<Jugador> getSuplentes() { 
    	return suplentes; 
    	}
 
    // --- LÓGICA DE PLANTILLA ---
 
    // Metodo para añadir jugadores controlando el rol y el limite de titulares
    public void registrarJugadorEnEquipo(Jugador j, boolean esTitular) throws RolNoDisponibleException {
        if (esTitular) {
            for (int i = 0; i < contadorTitulares; i++) {
                if (titulares[i] != null && titulares[i].getRol().equalsIgnoreCase(j.getRol())) {
                    throw new RolNoDisponibleException("ROL " + j.getRol() + " YA OCUPADO EN " + nombre + ".");
                }
            }
            if (contadorTitulares < 5) {
                titulares[contadorTitulares] = j;
                contadorTitulares++;
            } else {
                System.out.println("TITULARES LLENOS. " + j.getNickname() + " AÑADIDO COMO SUPLENTE.");
                suplentes.add(j);
            }
        } else {
            suplentes.add(j);
        }
    }
 
    // Metodo para dar de baja a un jugador de la lista o el array
    public void eliminarJugador(String nickname) {
        // Buscar en titulares
        for (int i = 0; i < contadorTitulares; i++) {
            if (titulares[i] != null && titulares[i].getNickname().equalsIgnoreCase(nickname)) {
                // Desplazar para tapar el hueco
                for (int j = i; j < contadorTitulares - 1; j++) {
                    titulares[j] = titulares[j + 1];
                }
                titulares[contadorTitulares - 1] = null;
                contadorTitulares--;
                System.out.println("TITULAR " + nickname.toUpperCase() + " ELIMINADO.");
                return;
            }
        }
        // Buscar en suplentes
        for (int i = 0; i < suplentes.size(); i++) {
            if (suplentes.get(i).getNickname().equalsIgnoreCase(nickname)) {
                suplentes.remove(i);
                System.out.println("SUPLENTE " + nickname.toUpperCase() + " ELIMINADO.");
                return;
            }
        }
        System.out.println("JUGADOR " + nickname.toUpperCase() + " NO ENCONTRADO EN " + nombre + ".");
    }
 
    // Metodo para pasar un jugador de suplente a titular
    public void promoverSuplente(String nickname) throws RolNoDisponibleException {
        for (int i = 0; i < suplentes.size(); i++) {
            Jugador s = suplentes.get(i);
            if (s.getNickname().equalsIgnoreCase(nickname)) {
                if (contadorTitulares >= 5) {
                    System.out.println("ERROR: YA HAY 5 TITULARES. ELIMINA UNO PRIMERO.");
                    return;
                }
                // Verificar rol
                for (int j = 0; j < contadorTitulares; j++) {
                    if (titulares[j] != null && titulares[j].getRol().equalsIgnoreCase(s.getRol())) {
                        throw new RolNoDisponibleException("ROL " + s.getRol() + " YA OCUPADO. NO SE PUEDE PROMOVER.");
                    }
                }
                suplentes.remove(i);
                titulares[contadorTitulares] = s;
                contadorTitulares++;
                System.out.println(s.getNickname() + " PROMOVIDO A TITULAR.");
                return;
            }
        }
        System.out.println("SUPLENTE " + nickname.toUpperCase() + " NO ENCONTRADO.");
    }
 
    // Metodo para actualizar el marcador y las victorias del equipo y entrenador
    public void registrarResultado(int favor, int contra) {
        this.puntosFavor += favor;
        this.puntosContra += contra;
        if (favor > contra) {
            this.victorias++;
            if (entrenador != null) entrenador.sumarVictoria();
        } else {
            this.derrotas++;
        }
    }
 
    // Comprobacion de seguridad para evitar que jueguen sancionados
    public boolean hayTitularesSancionados() {
        for (int i = 0; i < contadorTitulares; i++) {
            if (titulares[i] != null && titulares[i].isSancionado()) return true;
        }
        return false;
    }
 
    // Incrementa el historial de partidas de los titulares actuales
    public void actualizarPartidasTitulares() {
        for (int i = 0; i < contadorTitulares; i++) {
            if (titulares[i] != null) titulares[i].sumarPartidoJugado();
        }
    }
 
    // Calcula el gasto total sumando todos los integrantes
    public double calcularCosteTotalPlantilla() {
        double total = 0;
        for (int i = 0; i < contadorTitulares; i++) {
            if (titulares[i] != null) total += titulares[i].calcularCosteMensual();
        }
        for (Jugador s : suplentes) {
            total += s.calcularCosteMensual();
        }
        if (entrenador != null) total += entrenador.calcularCosteMensual();
        return total;
    }
 
    // Representacion visual de la plantilla y estadisticas
    @Override
    public String toString() {
        String res = "--- EQUIPO: " + nombre + " (" + ciudad + ")"
                + " | V:" + victorias + " D:" + derrotas
                + " | PF:" + puntosFavor + " PC:" + puntosContra + " ---\n";
        res += "ENTRENADOR: " + (entrenador != null ? entrenador.getNickname() : "SIN ASIGNAR") + "\n";
        res += "TITULARES:\n";
        for (int i = 0; i < contadorTitulares; i++) {
            res += "  [" + (i + 1) + "] " + titulares[i].toString() + "\n";
        }
        res += "SUPLENTES:\n";
        if (suplentes.isEmpty()) {
            res += "  (Vacio)\n";
        } else {
            for (Jugador s : suplentes) res += "  - " + s.toString() + "\n";
        }
        res += "COSTE TOTAL PLANTILLA: " + String.format("%.2f", calcularCosteTotalPlantilla()) + " EUR\n";
        return res;
    }
}