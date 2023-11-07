package crud_Java;

import java.util.InputMismatchException;
import java.util.Scanner;


//EX.7 soma definida: n+nn+nnn
public class Teste_1 {
	public static double somaDef(String a) {
		String n1 = a+a;
		String n2 = a+a+a;
		double resultado =0;
		
		resultado = Double.parseDouble(a)+Double.parseDouble(n1) + Double.parseDouble(n2);
		
		return resultado;
	}
	public static void main(String[] args) {
		
		Scanner ler = new Scanner(System.in);
		String num1;
		System.out.println("Soma definida: n+nn+nn");
		try {
			System.out.println("Entre com um numero");
			num1 = ler.nextLine();
			System.out.println(somaDef(num1)); 
		}
		catch(InputMismatchException e) {
			System.out.println("Numero invalido");
		}
		ler.close();
	}

}
