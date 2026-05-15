package trabajoFinal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Scanner;
 
public class GestionLigaUnite {
 
    // Lista general de personas registradas
    private static ArrayList<Jugador> listaJugadores = new ArrayList<>();
    private static ArrayList<Entrenador> listaEntrenadores = new ArrayList<>();
 
    // HashSet para controlar IDs únicas (estructura dinámica antiduplicados - punto 5.7)
    private static HashSet<String> idsRegistrados = new HashSet<>();
 
    // Pila LIFO con ArrayList (push = add al final, pop = remove del final)
    private static ArrayList<String> historial = new ArrayList<>();
 
    // Cola FIFO con LinkedList (offer al final, remove del principio - punto 5.9)
    private static LinkedList<String> colaPartidos = new LinkedList<>();
 
    private static ArrayList<Partido> partidosJugados = new ArrayList<>();
    private static ArrayList<Incidencia> registroIncidencias = new ArrayList<>();
    private static ArrayList<Equipo> listaEquipos = new ArrayList<>();
 
    // HashSet para controlar IDs de partidos ya jugados (evita duplicados - punto 5.11.6)
    private static HashSet<String> idsPartidos = new HashSet<>();
 
    // Matriz obligatoria: puntos por equipo y jornada (punto 5.8)
    private static int[][] matrizPuntos = new int[10][10];
    private static int jornadaActiva = 0;
 
    private static int contadorPartidos = 1;
 
    private static Scanner teclado = new Scanner(System.in);
 
    // =========================================================
    //  MENÚ PRINCIPAL
    // =========================================================
    public static void main(String[] args) {
        int opcion = 0;
        do {
            System.out.println("\n========================================");
            System.out.println("  POKEMON UNITE LEAGUE - JORNADA " + (jornadaActiva + 1));
            System.out.println("========================================");
            System.out.println(" 1.  Registrar Jugador");
            System.out.println(" 2.  Registrar Entrenador");
            System.out.println(" 3.  Buscar Persona por ID");
            System.out.println(" 4.  Modificar Jugador");
            System.out.println(" 5.  Eliminar Jugador de Equipo");
            System.out.println(" 6.  Crear Equipo");
            System.out.println(" 7.  Asignar Entrenador a Equipo");
            System.out.println(" 8.  Promover Suplente a Titular");
            System.out.println(" 9.  Ver Plantilla Completa");
            System.out.println("10.  Programar Partido (Encolar)");
            System.out.println("11.  Ver Siguiente Partido Pendiente");
            System.out.println("12.  Jugar Siguiente Partido (FIFO)");
            System.out.println("13.  Ver Todos los Partidos Pendientes");
            System.out.println("14.  Finalizar Jornada");
            System.out.println("15.  Ver Clasificacion");
            System.out.println("16.  Ver Estadisticas de Jugadores");
            System.out.println("17.  Registrar Incidencia / Sancionar Jugador");
            System.out.println("18.  Buscar Incidencias");
            System.out.println("19.  Ver Historial de Acciones (LIFO)");
            System.out.println("20.  Deshacer Ultima Accion");
            System.out.println("21.  Ver Partidos de una Jornada");
            System.out.println("22.  Ver Coste Total de un Equipo");
            System.out.println("23.  Salir");
            System.out.print("Seleccione una opcion: ");
 
            try {
                opcion = teclado.nextInt();
                teclado.nextLine();
                switch (opcion) {
                    case 1:  registrarJugador(); break;
                    case 2:  registrarEntrenador(); break;
                    case 3:  buscarPersonaPorId(); break;
                    case 4:  modificarJugador(); break;
                    case 5:  eliminarJugador(); break;
                    case 6:  crearEquipo(); break;
                    case 7:  asignarEntrenador(); break;
                    case 8:  promoverSuplente(); break;
                    case 9:  verPlantilla(); break;
                    case 10: anotarPartido(); break;
                    case 11: verSiguientePartido(); break;
                    case 12: jugar(); break;
                    case 13: verTodosLosPendientes(); break;
                    case 14: avanzarJornada(); break;
                    case 15: verClasificacion(); break;
                    case 16: verEstadisticasJugadores(); break;
                    case 17: registrarIncidencia(); break;
                    case 18: buscarIncidencias(); break;
                    case 19: verHistorial(); break;
                    case 20: deshacerAccion(); break;
                    case 21: verPartidosDeJornada(); break;
                    case 22: verCosteTotalEquipo(); break;
                    case 23: System.out.println("Saliendo del sistema..."); break;
                    default: System.out.println("Opcion no valida.");
                }
            } catch (Exception e) {
                System.out.println("ERROR: INTRODUCE UN NUMERO VALIDO.");
                registroIncidencias.add(new Incidencia("error de entrada en menu", "InputMismatch"));
                teclado.nextLine();
            }
        } while (opcion != 23);
    }
 
    // =========================================================
    //  1. REGISTRAR JUGADOR
    // =========================================================
    public static void registrarJugador() {
        if (listaEquipos.isEmpty()) {
            System.out.println("ERROR: DEBES CREAR UN EQUIPO PRIMERO.");
            return;
        }
        try {
            System.out.print("ID: "); String id = teclado.nextLine().toUpperCase();
 
            if (idsRegistrados.contains(id)) {
                System.out.println("ERROR: ESE ID YA ESTA REGISTRADO.");
                registroIncidencias.add(new Incidencia("intento de ID duplicado: " + id, "IDDuplicado"));
                return;
            }
 
            System.out.print("Nombre Real: "); String nombre = teclado.nextLine();
            if (estaVacio(nombre)) { System.out.println("ERROR: EL NOMBRE NO PUEDE ESTAR VACIO."); return; }
 
            System.out.print("Nickname: "); String nick = teclado.nextLine();
            if (estaVacio(nick)) { System.out.println("ERROR: EL NICKNAME NO PUEDE ESTAR VACIO."); return; }
 
            System.out.print("Edad: "); int edad = teclado.nextInt();
            if (edad <= 0) { System.out.println("ERROR: LA EDAD DEBE SER MAYOR QUE 0."); teclado.nextLine(); return; }
 
            System.out.print("Salario Base: "); double sueldo = teclado.nextDouble();
            if (sueldo < 0) { System.out.println("ERROR: EL SALARIO NO PUEDE SER NEGATIVO."); teclado.nextLine(); return; }
            teclado.nextLine();
 
            System.out.print("Rol (TOP/MID/JUNGLE/ADC/SUPPORT): "); String rol = teclado.nextLine().toUpperCase();
            if (!rolValido(rol)) { System.out.println("ERROR: ROL '" + rol + "' NO VALIDO. Usa TOP, MID, JUNGLE, ADC o SUPPORT."); return; }
            System.out.print("Pokemon (PIKACHU/CHARIZARD/LUCARIO/SNORLAX/ELDEGOSS/ZERAORA): ");
            String pkmStr = teclado.nextLine().toUpperCase();
 
            PokemonMain pkm = null;
            switch (pkmStr) {
                case "PIKACHU":   pkm = PokemonMain.PIKACHU;   break;
                case "CHARIZARD": pkm = PokemonMain.CHARIZARD; break;
                case "LUCARIO":   pkm = PokemonMain.LUCARIO;   break;
                case "SNORLAX":   pkm = PokemonMain.SNORLAX;   break;
                case "ELDEGOSS":  pkm = PokemonMain.ELDEGOSS;  break;
                case "ZERAORA":   pkm = PokemonMain.ZERAORA;   break;
                default: throw new PokemonNoReconocidoException("El Pokemon '" + pkmStr + "' no esta en la lista.");
            }
 
            mostrarEquipos();
            System.out.print("Numero de Equipo: "); int posE = teclado.nextInt() - 1; teclado.nextLine();
            if (posE < 0 || posE >= listaEquipos.size()) throw new IndexOutOfBoundsException();
 
            System.out.print("¿Es titular? (si/no): ");
            boolean esTitular = teclado.nextLine().trim().toLowerCase().startsWith("s");
 
            Jugador nuevo = new Jugador(id, nombre, nick, edad, sueldo, rol, pkm);
            listaEquipos.get(posE).registrarJugadorEnEquipo(nuevo, esTitular);
            listaJugadores.add(nuevo);
            idsRegistrados.add(id);
 
            historial.add("FICHAJE: " + nick.toUpperCase() + " -> " + listaEquipos.get(posE).getNombre());
            System.out.println("JUGADOR REGISTRADO CON EXITO.");
 
        } catch (RolNoDisponibleException e) {
            System.out.println("ERROR: " + e.getMessage());
            registroIncidencias.add(new Incidencia(e.getMessage(), "RolDuplicado"));
        } catch (PokemonNoReconocidoException e) {
            System.out.println("ERROR: " + e.getMessage());
            registroIncidencias.add(new Incidencia(e.getMessage(), "PokemonInvalido"));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR: NUMERO DE EQUIPO NO VALIDO.");
        } catch (Exception e) {
            System.out.println("ERROR EN LOS DATOS INTRODUCIDOS.");
            teclado.nextLine();
        }
    }
 
    // =========================================================
    //  2. REGISTRAR ENTRENADOR
    // =========================================================
    public static void registrarEntrenador() {
        try {
            System.out.print("ID: "); String id = teclado.nextLine().toUpperCase();
            if (idsRegistrados.contains(id)) {
                System.out.println("ERROR: ESE ID YA ESTA REGISTRADO.");
                return;
            }
            System.out.print("Nombre Real: "); String nombre = teclado.nextLine();
            if (estaVacio(nombre)) { System.out.println("ERROR: EL NOMBRE NO PUEDE ESTAR VACIO."); return; }
 
            System.out.print("Nickname: "); String nick = teclado.nextLine();
            if (estaVacio(nick)) { System.out.println("ERROR: EL NICKNAME NO PUEDE ESTAR VACIO."); return; }
 
            System.out.print("Edad: "); int edad = teclado.nextInt();
            if (edad <= 0) { System.out.println("ERROR: LA EDAD DEBE SER MAYOR QUE 0."); teclado.nextLine(); return; }
 
            System.out.print("Salario Base: "); double sueldo = teclado.nextDouble();
            if (sueldo < 0) { System.out.println("ERROR: EL SALARIO NO PUEDE SER NEGATIVO."); teclado.nextLine(); return; }
 
            System.out.print("Experiencia (años): "); int exp = teclado.nextInt();
            if (exp < 0) { System.out.println("ERROR: LA EXPERIENCIA NO PUEDE SER NEGATIVA."); teclado.nextLine(); return; }
            teclado.nextLine();
 
            System.out.print("Especialidad: "); String espec = teclado.nextLine();
            if (estaVacio(espec)) { System.out.println("ERROR: LA ESPECIALIDAD NO PUEDE ESTAR VACIA."); return; }
 
            Entrenador nuevo = new Entrenador(id, nombre, nick, edad, sueldo, exp, espec);
            listaEntrenadores.add(nuevo);
            idsRegistrados.add(id);
            historial.add("REGISTRO ENTRENADOR: " + nick.toUpperCase());
            System.out.println("ENTRENADOR REGISTRADO CON EXITO.");
        } catch (Exception e) {
            System.out.println("ERROR EN LOS DATOS INTRODUCIDOS.");
            teclado.nextLine();
        }
    }
 
    // =========================================================
    //  3. BUSCAR PERSONA POR ID
    // =========================================================
    public static void buscarPersonaPorId() {
        System.out.print("Introduce el ID a buscar: "); String id = teclado.nextLine().toUpperCase();
 
        for (Jugador j : listaJugadores) {
            if (j.getId().equals(id)) {
                System.out.println("JUGADOR ENCONTRADO:\n" + j.toString());
                System.out.println(j.mostrarResumen());
                return;
            }
        }
        for (Entrenador e : listaEntrenadores) {
            if (e.getId().equals(id)) {
                System.out.println("ENTRENADOR ENCONTRADO:\n" + e.toString());
                System.out.println(e.mostrarResumen());
                return;
            }
        }
        System.out.println("PERSONA CON ID '" + id + "' NO ENCONTRADA.");
    }
 
    // =========================================================
    //  4. MODIFICAR JUGADOR
    // =========================================================
    public static void modificarJugador() {
        System.out.print("ID del jugador a modificar: "); String id = teclado.nextLine().toUpperCase();
        Jugador objetivo = null;
        for (Jugador j : listaJugadores) {
            if (j.getId().equals(id)) { objetivo = j; break; }
        }
        if (objetivo == null) { System.out.println("JUGADOR NO ENCONTRADO."); return; }
 
        System.out.println("Jugador encontrado: " + objetivo);
        System.out.println("¿Que deseas modificar?");
        System.out.println("1. Nickname  2. Edad  3. Salario  4. Rol");
        System.out.print("Opcion: ");
        try {
            int op = teclado.nextInt(); teclado.nextLine();
            switch (op) {
                case 1:
                    System.out.print("Nuevo nickname: "); objetivo.setNickname(teclado.nextLine()); break;
                case 2:
                    System.out.print("Nueva edad: "); objetivo.setEdad(teclado.nextInt()); teclado.nextLine(); break;
                case 3:
                    System.out.print("Nuevo salario: "); objetivo.setSalarioBase(teclado.nextDouble()); teclado.nextLine(); break;
                case 4:
                    System.out.print("Nuevo rol (TOP/MID/JUNGLE/ADC/SUPPORT): ");
                    String nuevoRol = teclado.nextLine().toUpperCase();
                    if (!rolValido(nuevoRol)) { System.out.println("ERROR: ROL '" + nuevoRol + "' NO VALIDO."); return; }
                    objetivo.setRol(nuevoRol); break;
                default: System.out.println("Opcion no valida."); return;
            }
            historial.add("MODIFICACION JUGADOR: " + id);
            System.out.println("JUGADOR MODIFICADO CON EXITO.");
        } catch (Exception e) {
            System.out.println("ERROR AL MODIFICAR."); teclado.nextLine();
        }
    }
 
    // =========================================================
    //  5. ELIMINAR JUGADOR DE EQUIPO
    // =========================================================
    public static void eliminarJugador() {
        if (listaEquipos.isEmpty()) { System.out.println("NO HAY EQUIPOS."); return; }
        mostrarEquipos();
        System.out.print("Numero de Equipo: ");
        try {
            int posE = teclado.nextInt() - 1; teclado.nextLine();
            if (posE < 0 || posE >= listaEquipos.size()) { System.out.println("EQUIPO NO VALIDO."); return; }
            System.out.print("Nickname del jugador a eliminar: "); String nick = teclado.nextLine();
            listaEquipos.get(posE).eliminarJugador(nick);
            historial.add("BAJA JUGADOR: " + nick.toUpperCase());
        } catch (Exception e) {
            System.out.println("ERROR."); teclado.nextLine();
        }
    }
 
    // =========================================================
    //  6. CREAR EQUIPO
    // =========================================================
    public static void crearEquipo() {
        try {
            System.out.print("Nombre del equipo: "); String nom = teclado.nextLine();
            if (estaVacio(nom)) { System.out.println("ERROR: EL NOMBRE NO PUEDE ESTAR VACIO."); return; }
            for (Equipo e : listaEquipos) {
                if (e.getNombre().equalsIgnoreCase(nom)) {
                    System.out.println("ERROR: EL EQUIPO '" + nom.toUpperCase() + "' YA EXISTE.");
                    registroIncidencias.add(new Incidencia("intento de equipo duplicado: " + nom, "EquipoDuplicado"));
                    return;
                }
            }
            System.out.print("Ciudad: "); String ciudad = teclado.nextLine();
            if (estaVacio(ciudad)) { System.out.println("ERROR: LA CIUDAD NO PUEDE ESTAR VACIA."); return; }
 
            System.out.print("Presupuesto: "); double presupuesto = teclado.nextDouble(); teclado.nextLine();
            if (presupuesto < 0) { System.out.println("ERROR: EL PRESUPUESTO NO PUEDE SER NEGATIVO."); return; }
 
            listaEquipos.add(new Equipo(nom, ciudad, presupuesto));
            historial.add("CREACION EQUIPO: " + nom.toUpperCase());
            System.out.println("EQUIPO '" + nom.toUpperCase() + "' CREADO CORRECTAMENTE.");
        } catch (Exception e) {
            System.out.println("ERROR AL CREAR EQUIPO."); teclado.nextLine();
        }
    }
 
    // =========================================================
    //  7. ASIGNAR ENTRENADOR A EQUIPO
    // =========================================================
    public static void asignarEntrenador() {
        if (listaEntrenadores.isEmpty()) { System.out.println("NO HAY ENTRENADORES REGISTRADOS."); return; }
        if (listaEquipos.isEmpty()) { System.out.println("NO HAY EQUIPOS CREADOS."); return; }
 
        System.out.println("\n--- ENTRENADORES DISPONIBLES ---");
        for (int i = 0; i < listaEntrenadores.size(); i++) {
            System.out.println((i + 1) + ". " + listaEntrenadores.get(i).getNickname()
                    + " | ID: " + listaEntrenadores.get(i).getId());
        }
        mostrarEquipos();
        try {
            System.out.print("Numero de Entrenador: "); int posT = teclado.nextInt() - 1;
            System.out.print("Numero de Equipo: "); int posE = teclado.nextInt() - 1;
            teclado.nextLine();
            if (posT < 0 || posT >= listaEntrenadores.size() || posE < 0 || posE >= listaEquipos.size()) {
                System.out.println("SELECCION NO VALIDA."); return;
            }
            Entrenador ent = listaEntrenadores.get(posT);
            listaEquipos.get(posE).setEntrenador(ent);
            historial.add("ENTRENADOR " + ent.getNickname() + " -> " + listaEquipos.get(posE).getNombre());
            System.out.println("ENTRENADOR ASIGNADO CON EXITO.");
        } catch (Exception e) {
            System.out.println("ERROR."); teclado.nextLine();
        }
    }
 
    // =========================================================
    //  8. PROMOVER SUPLENTE A TITULAR
    // =========================================================
    public static void promoverSuplente() {
        if (listaEquipos.isEmpty()) { System.out.println("NO HAY EQUIPOS."); return; }
        mostrarEquipos();
        try {
            System.out.print("Numero de Equipo: "); int posE = teclado.nextInt() - 1; teclado.nextLine();
            if (posE < 0 || posE >= listaEquipos.size()) { System.out.println("EQUIPO NO VALIDO."); return; }
            System.out.print("Nickname del suplente a promover: "); String nick = teclado.nextLine();
            listaEquipos.get(posE).promoverSuplente(nick);
            historial.add("PROMOCION SUPLENTE: " + nick.toUpperCase());
        } catch (RolNoDisponibleException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR."); teclado.nextLine();
        }
    }
 
    // =========================================================
    //  9. VER PLANTILLA
    // =========================================================
    public static void verPlantilla() {
        if (listaEquipos.isEmpty()) { System.out.println("NO HAY EQUIPOS CREADOS."); return; }
        mostrarEquipos();
        System.out.print("Numero o Nombre del equipo: "); String entrada = teclado.nextLine().trim();
        try {
            int idx = buscarIndiceEquipo(entrada);
            if (idx < 0) throw new EquipoNoEncontradoException("El equipo '" + entrada + "' no existe.");
            System.out.println(listaEquipos.get(idx).toString());
        } catch (EquipoNoEncontradoException e) {
            System.out.println("ERROR: " + e.getMessage());
            registroIncidencias.add(new Incidencia(e.getMessage(), "EquipoNoEncontrado"));
        }
    }
 
    // =========================================================
    //  10. PROGRAMAR PARTIDO (ENCOLAR - FIFO)
    // =========================================================
    public static void anotarPartido() {
        if (listaEquipos.size() < 2) { System.out.println("SE NECESITAN AL MENOS 2 EQUIPOS."); return; }
        System.out.print("Nombre Equipo Local: "); String loc = teclado.nextLine();
        System.out.print("Nombre Equipo Visitante: "); String vis = teclado.nextLine();
 
        if (loc.equalsIgnoreCase(vis)) {
            System.out.println("ERROR: UN EQUIPO NO PUEDE JUGAR CONTRA SI MISMO.");
            return;
        }
 
        boolean existeL = false, existeV = false;
        for (Equipo e : listaEquipos) {
            if (e.getNombre().equalsIgnoreCase(loc)) existeL = true;
            if (e.getNombre().equalsIgnoreCase(vis)) existeV = true;
        }
 
        if (existeL && existeV) {
            // offer() añade al final de la LinkedList — comportamiento FIFO
            colaPartidos.offer(loc.toUpperCase() + " VS " + vis.toUpperCase());
            historial.add("PARTIDO ENCOLADO: " + loc.toUpperCase() + " VS " + vis.toUpperCase());
            System.out.println("PARTIDO PROGRAMADO EN COLA (FIFO). Pendientes: " + colaPartidos.size());
        } else {
            System.out.println("ERROR: UNO O AMBOS EQUIPOS NO EXISTEN.");
        }
    }
 
    // =========================================================
    //  11. VER SIGUIENTE PARTIDO PENDIENTE
    // =========================================================
    public static void verSiguientePartido() {
        if (colaPartidos.isEmpty()) {
            System.out.println("NO HAY PARTIDOS PENDIENTES.");
        } else {
            // element() devuelve la cabeza sin eliminarla
            System.out.println("SIGUIENTE PARTIDO: " + colaPartidos.element());
        }
    }
 
    // =========================================================
    //  12. JUGAR SIGUIENTE PARTIDO (FIFO)
    // =========================================================
    public static void jugar() {
        if (colaPartidos.isEmpty()) { System.out.println("NO HAY PARTIDOS PENDIENTES."); return; }
 
        // element() para ver sin eliminar; remove() para eliminar solo si todo va bien
        String enfrentamiento = colaPartidos.element();
        String[] nombres = enfrentamiento.split(" VS ");
 
        int idxL = -1, idxV = -1;
        for (int i = 0; i < listaEquipos.size(); i++) {
            if (listaEquipos.get(i).getNombre().equalsIgnoreCase(nombres[0])) idxL = i;
            if (listaEquipos.get(i).getNombre().equalsIgnoreCase(nombres[1])) idxV = i;
        }
 
        if (idxL == -1 || idxV == -1) {
            System.out.println("ERROR: NO SE ENCONTRARON LOS EQUIPOS. PARTIDO DESCARTADO.");
            colaPartidos.remove();
            return;
        }
 
        Equipo local = listaEquipos.get(idxL);
        Equipo visit = listaEquipos.get(idxV);
 
        // Validación de convocatoria: 5 titulares
        if (local.getContadorTitulares() < 5 || visit.getContadorTitulares() < 5) {
            System.out.println("ERROR: AMBOS EQUIPOS DEBEN TENER 5 TITULARES.");
            return;
        }
 
        // Validación de sancionados
        try {
            if (local.hayTitularesSancionados()) throw new JugadorSancionadoException("EQUIPO " + local.getNombre() + " TIENE UN TITULAR SANCIONADO.");
            if (visit.hayTitularesSancionados()) throw new JugadorSancionadoException("EQUIPO " + visit.getNombre() + " TIENE UN TITULAR SANCIONADO.");
        } catch (JugadorSancionadoException e) {
            System.out.println("ERROR CONVOCATORIA: " + e.getMessage());
            registroIncidencias.add(new Incidencia(e.getMessage(), "Sancion", "", ""));
            return;
        }
 
        // Todo correcto — ahora sí eliminamos de la cola
        colaPartidos.remove();
 
        try {
            System.out.println("DISPUTANDO: " + enfrentamiento);
            System.out.print("Puntos para " + nombres[0] + ": "); int ptsL = teclado.nextInt();
            System.out.print("Puntos para " + nombres[1] + ": "); int ptsV = teclado.nextInt();
            teclado.nextLine();
            System.out.print("Nickname del MVP: "); String mvp = teclado.nextLine();
 
            // Generar ID único para el partido
            String idPartido = "P" + contadorPartidos++;
            while (idsPartidos.contains(idPartido)) idPartido = "P" + contadorPartidos++;
 
            Partido p = new Partido(idPartido, jornadaActiva + 1, nombres[0], nombres[1]);
            p.setResultado(ptsL, ptsV, mvp);
            partidosJugados.add(p);
            idsPartidos.add(idPartido);
 
            // Actualizar estadísticas de equipos
            local.registrarResultado(ptsL, ptsV);
            visit.registrarResultado(ptsV, ptsL);
 
            // Actualizar partidas de titulares
            local.actualizarPartidasTitulares();
            visit.actualizarPartidasTitulares();
 
            // Sumar MVP al jugador si existe
            for (Jugador j : listaJugadores) {
                if (j.getNickname().equalsIgnoreCase(mvp)) { j.sumarMVP(); break; }
            }
 
            // Actualizar matriz de puntos
            if (jornadaActiva < 10 && idxL < 10 && idxV < 10) {
                matrizPuntos[idxL][jornadaActiva] += ptsL;
                matrizPuntos[idxV][jornadaActiva] += ptsV;
            }
 
            System.out.println("RESULTADO: " + p.toString());
            System.out.println("GANADOR: " + p.calcularGanador());
            historial.add("PARTIDO JUGADO: " + enfrentamiento + " (" + ptsL + "-" + ptsV + ")");
 
        } catch (Exception e) {
            System.out.println("ERROR AL PROCESAR EL PARTIDO.");
            teclado.nextLine();
        }
    }
 
    // =========================================================
    //  13. VER TODOS LOS PARTIDOS PENDIENTES
    // =========================================================
    public static void verTodosLosPendientes() {
        if (colaPartidos.isEmpty()) { System.out.println("NO HAY PARTIDOS PENDIENTES."); return; }
        System.out.println("\n--- COLA DE PARTIDOS PENDIENTES ---");
        int pos = 1;
        for (String partido : colaPartidos) {
            System.out.println(pos++ + ". " + partido);
        }
    }
 
    // =========================================================
    //  14. AVANZAR JORNADA
    // =========================================================
    public static void avanzarJornada() {
        if (partidosJugados.isEmpty()) { System.out.println("ERROR: NO HAY PARTIDOS JUGADOS EN ESTA JORNADA."); return; }
        if (jornadaActiva >= 9) { System.out.println("LIGA FINALIZADA. NO SE PUEDE AVANZAR MAS."); return; }
        jornadaActiva++;
        partidosJugados.clear();
        historial.add("JORNADA CERRADA -> JORNADA " + (jornadaActiva + 1));
        System.out.println("--- JORNADA CERRADA. PASAMOS A LA JORNADA " + (jornadaActiva + 1) + " ---");
    }
 
    // =========================================================
    //  15. VER CLASIFICACION (con sort por victorias y diferencia)
    // =========================================================
    public static void verClasificacion() {
        if (listaEquipos.isEmpty()) { System.out.println("NO HAY EQUIPOS REGISTRADOS."); return; }
 
        // Ordenar con lambda: primero por victorias, luego por diferencia de puntos
        listaEquipos.sort((a, b) -> {
            if (b.getVictorias() != a.getVictorias())
                return b.getVictorias() - a.getVictorias();
            int difB = b.getPuntosFavor() - b.getPuntosContra();
            int difA = a.getPuntosFavor() - a.getPuntosContra();
            return difB - difA;
        });
 
        System.out.println("\n--- CLASIFICACION ---");
        System.out.printf("%-3s %-15s | %3s | %3s | %4s | %4s | %5s%n",
                "POS", "EQUIPO", " V ", " D ", " PF ", " PC ", " DIF ");
        System.out.println("------------------------------------------------------");
        for (int i = 0; i < listaEquipos.size(); i++) {
            Equipo e = listaEquipos.get(i);
            int dif = e.getPuntosFavor() - e.getPuntosContra();
            System.out.printf("%-3d %-15s | %3d | %3d | %4d | %4d | %5d%n",
                    (i + 1), e.getNombre(), e.getVictorias(), e.getDerrotas(),
                    e.getPuntosFavor(), e.getPuntosContra(), dif);
        }
 
        // Mostrar también la matriz de puntos por jornada
        System.out.println("\n--- PUNTOS POR JORNADA (MATRIZ) ---");
        System.out.printf("%-15s", "EQUIPO");
        for (int j = 0; j <= jornadaActiva; j++) System.out.printf("| J%-3d", (j + 1));
        System.out.println();
        for (int i = 0; i < listaEquipos.size(); i++) {
            System.out.printf("%-15s", listaEquipos.get(i).getNombre());
            for (int j = 0; j <= jornadaActiva; j++) System.out.printf("| %4d", matrizPuntos[i][j]);
            System.out.println();
        }
    }
 
    // =========================================================
    //  16. VER ESTADÍSTICAS DE JUGADORES
    // =========================================================
    public static void verEstadisticasJugadores() {
        if (listaJugadores.isEmpty()) { System.out.println("NO HAY JUGADORES REGISTRADOS."); return; }
        System.out.println("\n--- ESTADISTICAS DE JUGADORES ---");
        System.out.printf("%-15s | %-7s | %3s | %3s | %8s | %10s | %s%n",
                "NICKNAME", "ROL", "PJ", "MVP", "REND.", "COSTE/MES", "ESTADO");
        System.out.println("-------------------------------------------------------------------------");
        for (Jugador j : listaJugadores) {
            System.out.printf("%-15s | %-7s | %3d | %3d | %8.1f | %10.2f | %s%n",
                    j.getNickname(), j.getRol(), j.getPartidasJugadas(), j.getMvpTotales(),
                    j.calcularRendimiento(), j.calcularCosteMensual(),
                    j.isSancionado() ? "SANCIONADO" : "ACTIVO");
        }
    }
 
    // =========================================================
    //  17. REGISTRAR INCIDENCIA / SANCIONAR JUGADOR
    // =========================================================
    public static void registrarIncidencia() {
        System.out.println("Tipo de registro:");
        System.out.println("1. Incidencia general  2. Sancionar jugador  3. Levantar sancion");
        System.out.print("Opcion: ");
        try {
            int op = teclado.nextInt(); teclado.nextLine();
            if (op == 1) {
                System.out.print("Descripcion: "); String desc = teclado.nextLine();
                System.out.print("Tipo (SANCION/EXPULSION/ERROR_TECNICO/APLAZADO/OTRO): "); String tipo = teclado.nextLine();
                System.out.print("Nombre del equipo afectado (o NINGUNO): "); String equipo = teclado.nextLine();
                System.out.print("Nickname del jugador afectado (o NINGUNO): "); String jugador = teclado.nextLine();
                registroIncidencias.add(new Incidencia(desc, tipo, equipo, jugador));
                historial.add("INCIDENCIA REGISTRADA: " + tipo.toUpperCase());
                System.out.println("INCIDENCIA REGISTRADA.");
 
            } else if (op == 2) {
                System.out.print("Nickname del jugador a sancionar: "); String nick = teclado.nextLine();
                Jugador objetivo = buscarJugadorPorNick(nick);
                if (objetivo == null) { System.out.println("JUGADOR NO ENCONTRADO."); return; }
                if (objetivo.isSancionado()) { System.out.println("EL JUGADOR YA ESTA SANCIONADO."); return; }
                objetivo.setSancionado(true);
                registroIncidencias.add(new Incidencia("Sancion aplicada a " + nick.toUpperCase(), "SANCION", "", nick));
                historial.add("SANCION: " + nick.toUpperCase());
                System.out.println("JUGADOR " + nick.toUpperCase() + " SANCIONADO.");
 
            } else if (op == 3) {
                System.out.print("Nickname del jugador al que levantar sancion: "); String nick = teclado.nextLine();
                Jugador objetivo = buscarJugadorPorNick(nick);
                if (objetivo == null) { System.out.println("JUGADOR NO ENCONTRADO."); return; }
                objetivo.setSancionado(false);
                registroIncidencias.add(new Incidencia("Sancion levantada a " + nick.toUpperCase(), "LEVANTAMIENTO_SANCION", "", nick));
                historial.add("SANCION LEVANTADA: " + nick.toUpperCase());
                System.out.println("SANCION LEVANTADA A " + nick.toUpperCase() + ".");
            }
        } catch (Exception e) {
            System.out.println("ERROR."); teclado.nextLine();
        }
    }
 
    // =========================================================
    //  18. BUSCAR INCIDENCIAS
    // =========================================================
    public static void buscarIncidencias() {
        if (registroIncidencias.isEmpty()) { System.out.println("NO HAY INCIDENCIAS REGISTRADAS."); return; }
        System.out.println("Buscar por:  1. Equipo   2. Jugador   3. Ver todas");
        System.out.print("Opcion: ");
        try {
            int op = teclado.nextInt(); teclado.nextLine();
            String filtro = "";
            if (op == 1) { System.out.print("Nombre del equipo: "); filtro = teclado.nextLine().toUpperCase(); }
            else if (op == 2) { System.out.print("Nickname del jugador: "); filtro = teclado.nextLine().toUpperCase(); }
 
            System.out.println("\n--- INCIDENCIAS ---");
            boolean hayResultados = false;
            for (Incidencia inc : registroIncidencias) {
                boolean mostrar = (op == 3)
                        || (op == 1 && inc.getNombreEquipo().contains(filtro))
                        || (op == 2 && inc.getNicknameJugador().contains(filtro));
                if (mostrar) { System.out.println(inc.toString()); hayResultados = true; }
            }
            if (!hayResultados) System.out.println("NO SE ENCONTRARON INCIDENCIAS.");
        } catch (Exception e) {
            System.out.println("ERROR."); teclado.nextLine();
        }
    }
 
    // =========================================================
    //  19. VER HISTORIAL (LIFO — muestra del último al primero)
    // =========================================================
    public static void verHistorial() {
        if (historial.isEmpty()) { System.out.println("HISTORIAL VACIO."); return; }
        System.out.println("\n--- HISTORIAL DE ACCIONES (LIFO) ---");
        // Recorremos de fin a inicio para mostrar comportamiento LIFO
        for (int i = historial.size() - 1; i >= 0; i--) {
            System.out.println((historial.size() - i) + ". " + historial.get(i));
        }
    }
 
    // =========================================================
    //  20. DESHACER ÚLTIMA ACCIÓN (pop LIFO)
    // =========================================================
    public static void deshacerAccion() {
        if (historial.isEmpty()) { System.out.println("NADA QUE DESHACER."); return; }
        // remove del final = comportamiento pop (LIFO)
        String accion = historial.remove(historial.size() - 1);
        System.out.println("ACCION DESHECHA: " + accion);
    }
 
    // =========================================================
    //  21. VER PARTIDOS DE UNA JORNADA
    // =========================================================
    public static void verPartidosDeJornada() {
        if (partidosJugados.isEmpty() && jornadaActiva == 0) {
            System.out.println("NO HAY PARTIDOS REGISTRADOS AUN.");
            return;
        }
        try {
            System.out.print("Numero de jornada a consultar (1-" + (jornadaActiva + 1) + "): ");
            int jornada = teclado.nextInt(); teclado.nextLine();
            if (jornada < 1 || jornada > jornadaActiva + 1) {
                System.out.println("JORNADA NO VALIDA.");
                return;
            }
 
            System.out.println("\n--- PARTIDOS DE LA JORNADA " + jornada + " ---");
            boolean hayPartidos = false;
 
            // Buscar en el historial global de partidos jugados
            for (Partido p : partidosJugados) {
                if (p.getJornada() == jornada) {
                    System.out.println(p.toString());
                    hayPartidos = true;
                }
            }
 
            // Mostrar también los puntos de esa jornada desde la matriz
            System.out.println("\n--- PUNTOS EN JORNADA " + jornada + " (MATRIZ) ---");
            boolean hayPuntos = false;
            for (int i = 0; i < listaEquipos.size(); i++) {
                int pts = matrizPuntos[i][jornada - 1];
                if (pts > 0) {
                    System.out.println(listaEquipos.get(i).getNombre() + ": " + pts + " pts");
                    hayPuntos = true;
                }
            }
 
            if (!hayPartidos && !hayPuntos) {
                System.out.println("NO HAY DATOS PARA ESA JORNADA.");
            }
        } catch (Exception e) {
            System.out.println("ERROR AL CONSULTAR JORNADA."); teclado.nextLine();
        }
    }
 
    // =========================================================
    //  22. VER COSTE TOTAL DE UN EQUIPO
    // =========================================================
    public static void verCosteTotalEquipo() {
        if (listaEquipos.isEmpty()) { System.out.println("NO HAY EQUIPOS CREADOS."); return; }
        mostrarEquipos();
        System.out.print("Numero o Nombre del equipo: "); String entrada = teclado.nextLine().trim();
        int idx = buscarIndiceEquipo(entrada);
        try {
            if (idx < 0) throw new EquipoNoEncontradoException("El equipo '" + entrada + "' no existe.");
        } catch (EquipoNoEncontradoException e) {
            System.out.println("ERROR: " + e.getMessage());
            registroIncidencias.add(new Incidencia(e.getMessage(), "EquipoNoEncontrado"));
            return;
        }
 
        Equipo e = listaEquipos.get(idx);
        System.out.println("\n--- COSTE TOTAL: " + e.getNombre() + " ---");
 
        // Desglose por titulares
        System.out.println("TITULARES:");
        for (int i = 0; i < e.getContadorTitulares(); i++) {
            Jugador j = e.getTitular(i);
            System.out.printf("  %-15s | %s | %.2f EUR/mes%n",
                    j.getNickname(), j.getRol(), j.calcularCosteMensual());
        }
 
        // Desglose por suplentes
        System.out.println("SUPLENTES:");
        if (e.getSuplentes().isEmpty()) {
            System.out.println("  (Sin suplentes)");
        } else {
            for (Jugador s : e.getSuplentes()) {
                System.out.printf("  %-15s | %s | %.2f EUR/mes%n",
                        s.getNickname(), s.getRol(), s.calcularCosteMensual());
            }
        }
 
        // Entrenador
        if (e.getEntrenador() != null) {
            System.out.printf("ENTRENADOR: %-15s | %.2f EUR/mes%n",
                    e.getEntrenador().getNickname(), e.getEntrenador().calcularCosteMensual());
        }
 
        System.out.printf("%nCOSTE TOTAL PLANTILLA: %.2f EUR/mes%n", e.calcularCosteTotalPlantilla());
    }
 
    // =========================================================
    //  MÉTODOS AUXILIARES
    // =========================================================
    private static void mostrarEquipos() {
        System.out.println("\n--- EQUIPOS ---");
        for (int i = 0; i < listaEquipos.size(); i++) {
            System.out.println((i + 1) + ". " + listaEquipos.get(i).getNombre()
                    + " (" + listaEquipos.get(i).getCiudad() + ")");
        }
    }
 
    private static int buscarIndiceEquipo(String entrada) {
        try {
            int num = Integer.parseInt(entrada) - 1;
            if (num >= 0 && num < listaEquipos.size()) return num;
        } catch (NumberFormatException e) {
            for (int i = 0; i < listaEquipos.size(); i++) {
                if (listaEquipos.get(i).getNombre().equalsIgnoreCase(entrada)) return i;
            }
        }
        return -1;
    }
 
    private static Jugador buscarJugadorPorNick(String nick) {
        for (Jugador j : listaJugadores) {
            if (j.getNickname().equalsIgnoreCase(nick)) return j;
        }
        return null;
    }
 
    // Devuelve true si el texto es nulo o vacío
    private static boolean estaVacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }
 
    // Devuelve true si el rol es uno de los cinco permitidos
    private static boolean rolValido(String rol) {
        return rol.equals("TOP") || rol.equals("MID") || rol.equals("JUNGLE")
                || rol.equals("ADC") || rol.equals("SUPPORT");
    }
}
 