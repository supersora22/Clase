package actividad3_Estructura_Dinamica;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PaginaWeb {
    private String url;
    private LocalDateTime fechaHora;

    public PaginaWeb(String url, LocalDateTime fechaHora) {
        this.url = url;
        this.fechaHora = fechaHora;
    }

    public String getUrl() {
        return url;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "[" + fechaHora.format(formatter) + "] URL: " + url;
    }
}