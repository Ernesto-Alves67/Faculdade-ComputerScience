package FrogRace;

public class ExecCorrida {
	final static int numSapos = 5;
	final static int DISTANCIA = 500;
	
	public static void main(String[] args) {
		/*for(int i=1; i<= numSapos; i++) {
			new Corrida_Sapo("frog_"+i, DISTANCIA).run();
		}*/
		
		Corrida_Sapo s1 = new Corrida_Sapo("frog_1", DISTANCIA);
		Corrida_Sapo s2 = new Corrida_Sapo("frog_2", DISTANCIA);
		Corrida_Sapo s3 = new Corrida_Sapo("frog_3", DISTANCIA);
		Corrida_Sapo s4 = new Corrida_Sapo("frog_4", DISTANCIA);
		Corrida_Sapo s5 = new Corrida_Sapo("frog_5", DISTANCIA);
		
		
		s1.setPriority(Thread.MIN_PRIORITY);
		s2.setPriority(Thread.MAX_PRIORITY);
		s3.setPriority(Thread.NORM_PRIORITY);
		s4.setPriority(Thread.MAX_PRIORITY);
		s5.setPriority(Thread.MAX_PRIORITY);
		
		s1.start();
		s2.start();
		s4.start();
		s4.start();
		s5.start();
	}
	
	
	
}
