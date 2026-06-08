package simulacro;

class Alumno implements Comparable<Alumno> {
    private String nombre;
    private double nota;

    public Alumno(String nombre, double nota)  {
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("La nota debe estar entre 0 y 10 Javi.");
        }
        this.nombre = nombre;
        this.nota = nota;
    }

    public double getNota() { 
    	return nota; 
    	}
    public String getNombre() {
    	return nombre; 
    	}
    public void setNota(double nota) {
    	this.nota = nota; 
    	}

    @Override
    public int compareTo(Alumno a) {
        if (this.nota < a.nota) {
            return -1;
        } else if (this.nota > a.nota) {
            return 1;
        } else {
            return 0;
        }
    }
    
    @Override
    public String toString() {
        return nombre + " Nota: " + nota;
    }
}
