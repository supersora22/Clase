package simulacroTema5;

public abstract class Empleado implements Reconocible {
    protected String nombre;
    protected Departamento departamento;
    protected String id;
    protected double[][] horasTrabajadas;

    public Empleado(String nombre, String id, Departamento departamento) {
        this.nombre = nombre;
        this.id = id;
        this.departamento = departamento;
        this.horasTrabajadas = new double[5][2]; // creamos la matriz para los 5 dias de la semana y los 2 turnos (mañana y tarde)
    }

    public String getNombre() {
    	// hacemos un metodo para devolver el nombre del empleado cuando nos haga falta
    	return nombre; 
    }
    
    public String getId() { 
    	// este nos devuelve el id que le hayamos puesto al empleado
    	return id; 
    }
    
    public Departamento getDepartamento() {
    	// con este pillamos el departamento al que pertenece
    	return departamento; 
    }
    
    public double[][] getHorasTrabajadas() {
    	// devolvemos la matriz entera con todas las horas que ha echado en la semana
    	return horasTrabajadas; 
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Dept: " + departamento;
    }

    public double calcularHorasTotalesSemana() {
        double total = 0; // creamos una variable a 0 para ir acumulando las horas
        for (int i = 0; i < 5; i++) { // hacemos un bucle que de 5 vueltas para los dias
            for (int j = 0; j < 2; j++) { // otro bucle por dentro para los dos turnos
                total += horasTrabajadas[i][j]; // pillamos las horas de esa casilla y se las sumamos al total
            }
        }
        return total;
    }

    public double calcularHorasDia(int dia) {
        if (dia < 0 || dia >= 5) 
        	return 0; // si nos meten un dia raro que no es de lunes a viernes, devolvemos 0
        else {
        return horasTrabajadas[dia][0] + horasTrabajadas[dia][1]; // sumamos la columna 0 (mañana) y la 1 (tarde) de ese dia
        }
     }

    public abstract double calcularProductividad(); // lo dejamos abstracto para que cada hijo lo calcule a su manera

    public void mostrarHorario() {
        String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
        System.out.println("Horario de " + nombre + ":");
        System.out.println("        M  |  T");
        
        for (int i = 0; i < 5; i++) { // recorremos los 5 dias para ir imprimiendo el array con su dia al lado
            System.out.println(dias[i] + ": " + horasTrabajadas[i][0] + " | " + horasTrabajadas[i][1]);
        }
    }
}