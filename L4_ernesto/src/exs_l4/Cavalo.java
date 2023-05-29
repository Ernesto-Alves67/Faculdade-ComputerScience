package exs_l4;

public class Cavalo extends Animal{
	
	public Cavalo(String nome, String idade){
		super(nome, idade);
	}
	
	public String emitirSom() {
		return "hi hi hih hi";
	}
	public String move() {
		return "trotando...";
	}

}
