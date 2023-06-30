package FrogRace;

public class Corrida_Sapo extends Thread {
	private String nome;
	private int distanciaCorrida = 0;
	private int distanciaTotalCorrida;
	private int pulo = 0;
	private int pulos = 0;
	static int colocacao;
	final static int PULO_MAX = 50;
	
	public Corrida_Sapo(String nome, int distanciaTotalCorrida) {
		super(nome);
		this.distanciaTotalCorrida = distanciaTotalCorrida;
		this.nome = nome;
		
	}
	
	public void sapoImprindoSituacao() {
		System.out.println("O "+nome+" pulou "+pulo+" cm\t"
							+ "e ja percorreu " + distanciaCorrida + "cm");
		
	}
	
	public void sapoPulando() {
		pulos++;
		pulo = (int) (Math.random()*PULO_MAX);
		distanciaCorrida += pulo;
		if(distanciaCorrida > distanciaTotalCorrida) {
			distanciaCorrida = distanciaTotalCorrida;
		}
	}
	
	public void sapoDescansando() {
		Thread.yield();
	}
	
	public void colocacaoSapo() {
		colocacao++;
		System.out.println(nome+" foi o "+colocacao+
				"° colocado com "+pulos+"pulos.");
	}
	
	public void run() {
		while(distanciaCorrida < distanciaTotalCorrida) {
			sapoPulando();
			sapoImprindoSituacao();
			sapoDescansando();
		}
		colocacaoSapo();
		
	}
}
