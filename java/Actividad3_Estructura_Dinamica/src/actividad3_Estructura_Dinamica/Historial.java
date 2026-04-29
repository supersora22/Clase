package actividad3_Estructura_Dinamica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Historial {
    private List<PaginaWeb> registros;

    public Historial() {
        this.registros = new ArrayList<>();
    }

    public boolean agregarPagina(String url, LocalDateTime fechaHoraActual) {
        if (!registros.isEmpty()) {
            LocalDateTime ultimaFecha = registros.get(registros.size() - 1).getFechaHora();
            if (fechaHoraActual.isBefore(ultimaFecha)) {
                return false;
            }
        }
        registros.add(new PaginaWeb(url, fechaHoraActual));
        return true;
    }

    public void consultarCompleto() {
        if (registros.isEmpty()) {
            System.out.println("El historial está vacío.");
        } else {
            registros.forEach(System.out::println);
        }
    }

    public void consultarPorDia(LocalDate dia) {
        boolean encontrado = false;
        for (PaginaWeb p : registros) {
            if (p.getFechaHora().toLocalDate().equals(dia)) {
                System.out.println(p);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No hay registros para el día: " + dia);
        }
    }

    public void borrarTodo() {
        registros.clear();
        System.out.println("Historial eliminado correctamente.");
    }
}