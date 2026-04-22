package array1;

import java.util.*;

public class Array1 {
    Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {
    int tusMuertos [] = {1,2,4,6,8};
    System.out.println("bucle for each");
    for (int todos : tusMuertos) {
    	System.out.println(todos);
    }
    System.out.println("----------");
	System.out.println("bucle for");
    for (int i=0; i<tusMuertos.length; i++) {
    	System.out.println(i);
    }
    }
}
