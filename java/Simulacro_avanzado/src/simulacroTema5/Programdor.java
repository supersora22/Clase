package simulacroTema5;

class Programador extends Empleado {
    private int incidenciasResueltas;

    public Programador(String nombre, String id, int incidenciasResueltas) {
        super(nombre, id, Departamento.INFORMATICA); // llamamos al constructor del padre y le pasamos el departamento a piñón
        this.incidenciasResueltas = incidenciasResueltas;
    }

    public int getIncidenciasResueltas() {
		// pillamos el numero de incidencias que ha resuelto el programador por si lo necesitamos en el main
		return incidenciasResueltas;
	}
    
    @Override
    public double calcularProductividad() {
        return calcularHorasTotalesSemana() + (incidenciasResueltas * 2); // sumamos las horas y multiplicamos las incidencias por 2 como dice el enunciado
    }

    @Override
    public boolean mereceReconocimiento() {
    	if (calcularProductividad() >=45) // miramos si la productividad llega a 45
       return true; // si llega, devolvemos true
    	else { 
    		return false; // si no llega, pues falso
    	}
    }

    @Override
    public String toString() {
        return super.toString() + " | Incidencias: " + incidenciasResueltas + 
               " | Productividad: " + calcularProductividad(); // aprovechamos el toString del padre con super y le añadimos lo nuestro
    }
}