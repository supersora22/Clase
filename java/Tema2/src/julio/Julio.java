package julio;

import java.util.*;

public class Julio {
	private static Scanner teclado = new Scanner(System.in);
	public static void main(String[] args) {
		int mes=0,ano=0,dias=0;
		String meses = "Enero,Febrero,Marzo,Abril,Mayo,Junio,Julio,Agosto,Septiembre,Octubre,Noviembre,Diciembre";
		String[] nombreMes = meses.split(",");
		mes=meses(mes);
		ano=anos(ano);
		dias=diasDeUnMes(mes,dias);
		boolean comprobacion = calculo(ano);
		if(mes==2) {
			if(comprobacion==true) {
				dias=29;
			}else if(comprobacion==false) {
				dias=28;
			}
		}
		for(int i=0; i<nombreMes.length; i++) {
			if(mes==i) {
				System.out.println("El mes " + nombreMes[mes-1] + " tiene " + dias + " dias");
			}
		}
	}
	private static int meses(int mes) {
		System.out.println("Inetroduzca el numero del mes");
		mes = teclado.nextInt();
		while(mes<0 || mes>12) {
			System.out.println("Debes introducir un de los doce meses del año, siendo enero el uno y diciembre el 12");
			mes = teclado.nextInt();
		}return mes;
	}
	
	private static int anos(int ano) {
		System.out.println("Inetroduzca el numero del año");
		ano = teclado.nextInt();
		while(ano<0) {
			System.out.println("Debes introducir un año en positivo");
			ano = teclado.nextInt();
		}return ano;
	}
	
	private static int diasDeUnMes(int mes, int dias) {
		for(int i=1; i<13; i++) {
			if(mes==i && i%2==0) {
				dias= 30;
				break;
			}else if(mes==i && i%2!=0) {
				dias=31;
				break;
			}
		}return dias;
	}
	
	private static boolean calculo(int ano) {
		boolean prueba= false;
		if(ano%4==0 || ano%100==0) {
			prueba = true;
			if(ano%400==0) {
				prueba=false;
			}
		}return prueba;
	}
}
