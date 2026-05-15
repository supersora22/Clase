package trabajoFinal;

public class Partido {
    // Atributos del partido
    private String id;
    private int jornada;
    private String local;
    private String visitante;
    private int scoreLocal;
    private int scoreVisitante;
    private String mvp;
    private boolean disputado;
 
    // Constructor para inicializar el partido pendiente
    public Partido(String id, int jornada, String local, String visitante) {
        this.id = id.toUpperCase();
        this.jornada = jornada;
        this.local = local.toUpperCase();
        this.visitante = visitante.toUpperCase();
        this.scoreLocal = 0;
        this.scoreVisitante = 0;
        this.mvp = "SIN MVP";
        this.disputado = false;
    }
 
    // Metodos Getter
    public String getId() { 
    	return id; 
    	}
    public int getJornada() { 
    	return jornada; 
    	}
    public String getLocal() { 
    	return local; 
    	}
    public String getVisitante() { 
    	return visitante; 
    	}
    public int getScoreLocal() { 
    	return scoreLocal; 
    	}
    public int getScoreVisitante() { 
    	return scoreVisitante; 
    	}
    public String getMvp() { 
    	return mvp; 
    	}
    public boolean isDisputado() { 
    	return disputado; 
    	}
 
    // Metodo para registrar el marcador y el mvp
    public void setResultado(int scoreLocal, int scoreVisitante, String mvp) {
        this.scoreLocal = scoreLocal;
        this.scoreVisitante = scoreVisitante;
        this.mvp = mvp.toUpperCase();
        this.disputado = true;
    }
 
    // Metodo para saber quien ha ganado el encuentro
    public String calcularGanador() {
        if (!disputado) 
        	return "PARTIDO NO DISPUTADO";
        if (scoreLocal > scoreVisitante) 
        	return local;
        if (scoreVisitante > scoreLocal) 
        	return visitante;
        else 
        	return "EMPATE";
    }
 
    // Metodo toString con formato de resultado
    @Override
    public String toString() {
        String estado = disputado ? "DISPUTADO" : "PENDIENTE";
        return "[" + id + "] J" + jornada + " | " + local + " " + scoreLocal + " - " + scoreVisitante + " " + visitante + " | MVP: " + mvp + " | " + estado;
    }
}