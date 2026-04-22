package simulacroTema5;

class Administrativo extends Empleado {
    private int documentosTramitados;

    public Administrativo(String nombre, String id, int documentosTramitados) {
        super(nombre, id, Departamento.ADMINISTRACION);
        this.documentosTramitados = documentosTramitados;
    }

    @Override
    public double calcularProductividad() {
        return calcularHorasTotalesSemana() + (documentosTramitados * 0.5); // sumamos las horas y los documentos a la mitad
    }

    @Override
    public boolean mereceReconocimiento() {
        return calcularProductividad() >= 30; // si es mayor o igual a 30 ya devuelve true directamente
    }

    @Override
    public String toString() {
        return super.toString() + " | Documentos: " + documentosTramitados + 
               " | Productividad: " + calcularProductividad();
    }
}