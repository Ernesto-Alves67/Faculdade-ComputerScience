package exs_l4;

public class Veterinario {
	protected String acao;
	
	public Veterinario(Animal ani) {
		ani.emitirSom();
		setAcao("Examinando "+ani.nome);
	}
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}

}
