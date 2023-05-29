import exs_l4.Cachorro;
import exs_l4.Cavalo;
import exs_l4.Preguiça;
import exs_l4.Veterinario;
public class Main {
	public static void main(String[] args) {
		Cachorro dog = new Cachorro("dog", "4");
		Preguiça nada = new Preguiça("nada", "9");
		Cavalo bjack = new Cavalo("bjack", "5");
		Veterinario veth = new Veterinario(dog);
		Veterinario veth2= new Veterinario(nada);
		Veterinario veth3 = new Veterinario(bjack);
		
		System.out.println(bjack.emitirSom());
		System.out.println(dog.emitirSom());
		System.out.println(nada.emitirSom());
		
		System.out.println(bjack.move());
		System.out.println(dog.move());
		System.out.println(nada.sobeArvore());
		
		System.out.println(veth.getAcao());
		System.out.println(veth2.getAcao());
		System.out.println(veth3.getAcao());
	}
}
