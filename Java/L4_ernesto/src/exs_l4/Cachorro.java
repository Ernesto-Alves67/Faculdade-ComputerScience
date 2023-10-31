package exs_l4;

public class Cachorro extends Animal{
	
	public Cachorro(String nome, String idade){
		super(nome, idade);
		
	}
	
	public String emitirSom() {
		return "au au au";
	}
	public String move() {
		return "movendo...";
	}

}
