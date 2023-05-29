package exs_l4;

public class Animal {
	protected String nome;
	protected String idade;
	
	Animal(String nome, String idade){
		this.nome = nome;
		this.idade =idade;
	}
	
	public String emitirSom() {
		return "Som animal";
	}
	public String move() {
		return "movendo";
	}
}
