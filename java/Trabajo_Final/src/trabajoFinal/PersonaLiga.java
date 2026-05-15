package trabajoFinal;

public abstract class PersonaLiga {
    // Atributos protegidos de la clase
    protected String id;
    protected String nombre;
    protected String nickname;
    protected int edad;
    protected double salarioBase;
 
    // Constructor con toUpperCase
    public PersonaLiga(String id, String nombre, String nickname, int edad, double salarioBase) {
        this.id = id.toUpperCase();
        this.nombre = nombre.toUpperCase();
        this.nickname = nickname.toUpperCase();
        this.edad = edad;
        this.salarioBase = salarioBase;
    }
 
    // Metodos Getter y Setter
    public String getId() { 
    	return id; 
    	}
    public void setId(String id) { this.id = id.toUpperCase(); }

    public String getNombre() { 
    	return nombre; 
    	}
    public void setNombre(String nombre) { 
    	this.nombre = nombre.toUpperCase(); 
    	}

    public String getNickname() { 
    	return nickname; 
    	}
    public void setNickname(String nickname) { 
    	this.nickname = nickname.toUpperCase(); 
    	}

    public int getEdad() { 
    	return edad; 
    	}
    public void setEdad(int edad) { 
    	this.edad = edad; 
    	}

    public double getSalarioBase() { 
    	return salarioBase; 
    	}
    public void setSalarioBase(double salarioBase) { 
    	this.salarioBase = salarioBase; 
    	}
 
    // Metodo abstracto para el coste
    public abstract double calcularCosteMensual();
 
    // Metodo para mostrar el resumen
    public String mostrarResumen() {
        return "ID: " + id + " | NICK: " + nickname + " | COSTE MENSUAL: " + calcularCosteMensual() + " EUR";
    }
 
    // Metodo toString
    @Override
    public String toString() {
        return "PERSONA: " + nombre + " [" + nickname + "] - ID: " + id + " - EDAD: " + edad;
    }
}