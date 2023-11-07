package connect;

public class login{
	private String nome, email, senha;
	
	public login(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
	
	public void criarConta(String email, String senha, String nome) {
		this.email = email;
		this.senha = senha;
		this.nome = nome;
	}
	
	
	//getters and setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}