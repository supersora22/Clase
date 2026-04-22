package actividad2;

public class Cuenta {
	private double saldo;
    private int contadorIngresos;
    private int contadorRetiradas;
    private int contador;

    // Constructor: Inicializa la cuenta con un saldo inicial
    public Cuenta(double saldoInicial) {
        this.saldo = saldoInicial;
        this.contadorIngresos = 0;
        this.contadorRetiradas = 0;
    }

    // Método para ingresar dinero
    public void ingresar(double cantidad) {
        if (cantidad > 0) {
            this.saldo += cantidad;
            this.contadorIngresos++;
            this.contador++;
        }
    }

    // Método para retirar dinero (reintegro)
    public boolean retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= this.saldo) {
            this.saldo -= cantidad;
            this.contadorRetiradas++;
            this.contador++;
            return true; // Operación exitosa
        }
        return false; // Saldo insuficiente o cantidad inválida
    }

    // Getters para consultar el estado de la cuenta
    public double getSaldo() {
        return saldo;
    }

    public int getContadorIngresos() {
        return contadorIngresos;
    }

    public int getContadorRetiradas() {
        return contadorRetiradas;
    }
    public int getContador() {
        return contador;
    }
}
