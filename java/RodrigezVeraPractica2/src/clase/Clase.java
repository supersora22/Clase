package clase;

import java.util.*;

public class Clase {
    public static void main(String[] args) {
    	char fj1, fj2, fj3, fj4, letra1, letra2, letra3, letra4;
    	long inicio, fin, duracion;
    	String clave;
    	letra1=generarLetra();
    	letra2=generarLetra();
    	letra3=generarLetra();
    	letra4=generarLetra();
    	clave=""+letra1+letra2+letra3+letra4;
    	inicio = System.nanoTime();
    	do {
    	fj1=generarLetra();
    	} while (fj1!= letra1);
    	do {
    	fj2=generarLetra();
    	} while (fj2!= letra2);
    	do {
    	fj3=generarLetra();
    	} while (fj3!= letra3);
    	do {
    	fj4=generarLetra();
    	} while (fj4!= letra4);
    	fin = System.nanoTime();
    	duracion = fin - inicio;
    	System.out.println("La combinación es:" + clave);
    	System.out.println("Encontrado letra a letra:" + fj1 + fj2 + fj3+ fj4);
    	System.out.println("Por fuerza Justa tardó:" + duracion + "ms");
    }
   
    private static char generarLetra() {
        Random random = new Random();
        int secret;
        do {
            secret = random.nextInt(90) + 1;
        } while (secret < 65);
        char letra = (char) secret;
        return letra;
    }
}
