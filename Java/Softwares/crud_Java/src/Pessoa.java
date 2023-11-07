


public class Pessoa 
{
	private int idade, frequenciaCardiaca;
	private String nome_p;

	public Pessoa(String nome, int age, int freq) {
    	this.idade = age;
    	this.frequenciaCardiaca = freq;
    	this.setNome_p(nome);
    	setNome_p(nome);
	}

	public String Calc_freq() 
	{
    	if(frequenciaCardiaca > (220 - idade)){
        	return "Acima da freq. max";
    	}else{
        	return "normal";
    	}
    }

	public String getNome_p() {
		return nome_p;
	}

	public void setNome_p(String nome_p) {
		this.nome_p = nome_p;
	}
	
	public int getFreq() {
		return frequenciaCardiaca;
	}
}