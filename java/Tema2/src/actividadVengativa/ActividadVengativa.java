package actividadVengativa;

import java.util.*;

public class ActividadVengativa {
	private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int numini, numfin;
        
        System.out.println("Introduce el  inicio: "); //pido numero inicial
        numini = sc.nextInt();
        System.out.println("introduce numero fin");
        numfin = sc.nextInt();
        turno(numini, numfin);
        for (int i = numini; i <= numfin; i = turno(i, numfin)) {
            if (esPrimo(i)) {
                System.out.println(i + " es primo");
            }
        }
        
        sc.close();
    }
    
    // Contamos numero desde inicio / fin para pasarselo a la funcion esPrimo
    public static int turno(int numini,int numfin) {
    	int numero;
    	if (numini <= numfin) {
    		numero = numini + 1;
    		return numero;
    	} 
    	return numfin;
    
    }
    
    // Funcion de comprobacion si es primo
    private static boolean esPrimo(int numero) {
        if (numero < 2) {
            return false;
        }

        for (int i = 2; i < numero; i++) {
            if (numero % i == 0) {
                return false; 
            }
        }
        
        return true; // si no entró en el if, es primo
    }
}