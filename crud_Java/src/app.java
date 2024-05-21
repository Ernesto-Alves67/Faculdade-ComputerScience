
import java.util.Scanner;
import java.util.InputMismatchException;

public class app{
	public static void main(String[] args){
		//Scanner ler = new Scanner(System.in);
		
		for(int i = 0; i <= 20; i++){
			if(i == 20){
				System.out.println(" "+i);
				break;
			}
			if((i%2) == 0) {
				System.out.println(" "+ i);
				i++;
			}else {
				i++;
				continue;
			}
		}
	}
}

