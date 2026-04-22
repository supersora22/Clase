package simulacro;

class Alumno implements Comparable<Alumno> {
    private String nombre; // creamos el atributo para guardar el nombre
    private double nota; // creamos el atributo para guardar la nota

    public Alumno(String nombre, double nota) throws NotaInvalidaException { // ponemos throws para avisar de que puede lanzar nuestra excepcion
        this.nombre = nombre; // guardamos el nombre que nos pasan
        setNota(nota); // llamamos al metodo setNota para validar si la nota esta bien antes de guardarla
    }

    public double getNota() { 
        return nota; // returneamos la nota del alumno
    }
    
    public String getNombre() { 
        return nombre; // returneamos el nombre del alumno
    }
    
    public void setNota(double nota) throws NotaInvalidaException {
        if (nota < 0 || nota > 10) { // comprobamos si la nota se sale del rango valido
            throw new NotaInvalidaException("La nota debe estar entre 0 y 10 Javi."); // lanzamos el error personalizado si no es valida
        }
        this.nota = nota; // si pasa el if y es valida, la guardamos
    }

    @Override
    public int compareTo(Alumno a) {
        if (this.nota < a.nota) { // comprobamos si la nota de nuestro alumno es menor que la del otro alumno (a)
            return -1; // returneamos -1 para que Java sepa que este tiene que ir antes
        } else if (this.nota > a.nota) { // si no es menor, comprobamos si nuestra nota es mayor
            return 1; // returneamos 1 para que Java sepa que este tiene que ir despues
        } else {
            return 0; // si no entra en los if anteriores, es que tienen la misma nota y returneamos 0
        }
    }
    
    @Override
    public String toString() {
        return nombre + " Nota: " + nota; // returneamos los datos del alumno en formato texto para cuando hagamos el print
    }
}